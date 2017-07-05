package com.revature.caliber.pdf;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

/**
 * Encapsulate the data pertaining to the PDF file. Initialize the PDF
 * document in Revature format.
 * 
 * @author Patrick Walsh
 *
 */
public class RevaturePDF {

	private static final Logger log = Logger.getLogger(RevaturePDF.class);
	private final InputStream header = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/header.png");
	private final InputStream footer = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/footer.png");
	private final InputStream htmlTemplate = RevaturePDF.class.getClassLoader()
			.getResourceAsStream("pdf/template.html");
	private final InputStream cssStyles = RevaturePDF.class.getClassLoader().getResourceAsStream("css/style.css");

	private Document document;
	private PdfWriter writer;
	private float marginLeft = 30;
	private float marginRight = 30;
	private float marginTop = 100;
	private float marginBottom = 100;
	private ByteArrayOutputStream byteArrayOutputStream;

	// Encloses whatever HTML provided to the method
	private String openHtml;
	private String closeHtml = "</body></html>";

	private String title;

	/**
	 * Configures iText PDF workers and formats the page
	 * 
	 * @param html
	 * @throws DocumentException
	 * @throws IOException
	 */
	public RevaturePDF(String title, String html) throws DocumentException, IOException {
		// PDF generator needs 'properly' closed <img> tags
		String formattedHtml = html.replaceAll("(<img[^>]*[^/]>)(?!\\s*</img>)", "$1</img>");
		log.trace(formattedHtml);
		
		// initialize document state
		this.document = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
		this.title = title;
		BufferedReader open = new BufferedReader(
				new InputStreamReader(RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/open.html")));
		StringBuilder openText = new StringBuilder();
		String tags;
		while ((tags = open.readLine()) != null) {
			openText.append(tags);
		}
		this.openHtml = openText.toString();

		// set writer
		this.byteArrayOutputStream = new ByteArrayOutputStream();
		this.writer = PdfWriter.getInstance(document, byteArrayOutputStream);

		// configure header/footer
		this.writer.setPageEvent(new RevaturePDFPageEventHandler(header, footer, this.title));

		// begin content
		this.document.open();

		// hold template contents
		StringBuilder page = new StringBuilder();
		page.append(openHtml);
		BufferedReader reader = new BufferedReader(new InputStreamReader(htmlTemplate));
		String temp;
		while ((temp = reader.readLine()) != null) {
			page.append(temp);
		}

		// find the body of document & add generated contents
		String marker = "<!-- CALIBER-REPORT -->";
		int bodyLocation = page.indexOf(marker);
		page.replace(bodyLocation, bodyLocation + marker.length(), formattedHtml);
		page.append(closeHtml);

		// set title
		document.addTitle("Revature | " + this.title);

		// CSS
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile = XMLWorkerHelper.getCSS(cssStyles);
		cssResolver.addCss(cssFile);

		// HTML
		HtmlPipelineContext htmlPipelineContext = new HtmlPipelineContext(null);
		htmlPipelineContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		htmlPipelineContext.setImageProvider(new ChartjsImageProvider());
		// XML Worker with Pipelines
		new XMLParser(new XMLWorker(new CssResolverPipeline(cssResolver,
				new HtmlPipeline(htmlPipelineContext, new PdfWriterPipeline(document, writer))), true))
						.parse(new ByteArrayInputStream(page.toString().getBytes()));

		// close resources gracefully
		this.document.close();
		reader.close();
	}

	/**
	 * Provides the PDF file as a byte array
	 * 
	 * @return
	 */
	public byte[] toByteArray() {
		return byteArrayOutputStream.toByteArray();
	}

}
