package com.revature.caliber.services;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import com.revature.caliber.exceptions.PDFGenerationException;

@Service
public class PDFService {

	private static final Logger log = Logger.getLogger(PDFService.class);

	/**
	 * Consumes HTML content and renders into a PDF format.
	 * 
	 * @param html
	 * @return Raw PDF as byte[]
	 * @throws DocumentException
	 * @throws IOException
	 */
	public byte[] generatePDF(String title, String html) throws DocumentException, IOException {
		log.debug(html);
		RevaturePDF pdf = new RevaturePDF(title, html);
		return pdf.toByteArray();
	}

	/**
	 * Encapsulate the data pertaining to the PDF file. Initialize the PDF
	 * document in Revature format.
	 * 
	 * @author Patrick Walsh
	 *
	 */
	private class RevaturePDF {

		private final InputStream HEADER = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/header.png");
		private final InputStream FOOTER = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/footer.png");
		private final InputStream HTML_TEMPLATE = RevaturePDF.class.getClassLoader()
				.getResourceAsStream("pdf/template.html");
		private final InputStream CSS_STYLES = RevaturePDF.class.getClassLoader().getResourceAsStream("css/style.css");

		private Document document;
		private PdfWriter writer;
		private float marginLeft = 30, marginRight = 30, marginTop = 100, marginBottom = 50;
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
			html = html.replaceAll("(<img[^>]*[^/]>)(?!\\s*</img>)", "$1</img>");
			
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
			this.writer.setPageEvent(new RevaturePDFPageEventHandler());

			// begin content
			this.document.open();

			// hold template contents
			StringBuilder page = new StringBuilder();
			page.append(openHtml);
			BufferedReader reader = new BufferedReader(new InputStreamReader(HTML_TEMPLATE));
			String temp;
			while ((temp = reader.readLine()) != null) {
				page.append(temp);
			}

			// find the body of document & add generated contents
			String marker = "<!-- CALIBER-REPORT -->";
			int bodyLocation = page.indexOf(marker);
			page.replace(bodyLocation, (bodyLocation + marker.length()), html);
			page.append(closeHtml);

			// set title
			document.addTitle("Revature | " + this.title);

			// CSS
			CSSResolver cssResolver = new StyleAttrCSSResolver();
			CssFile cssFile = XMLWorkerHelper.getCSS(CSS_STYLES);
			cssResolver.addCss(cssFile);
			
			// HTML
			HtmlPipelineContext htmlPipelineContext = new HtmlPipelineContext(null);
			htmlPipelineContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			htmlPipelineContext.setImageProvider(new AbstractImageProvider() {
				// capability to parse Base64 from <img src=..> to create Image
				@Override
				public Image retrieve(String src) {
					int pos = src.indexOf("base64,");
					try {
						if (src.startsWith("data") && pos > 0) {
							byte[] img = Base64.decode(src.substring(pos + 7));
							return Image.getInstance(img);
						} else {
							return Image.getInstance(src);
						}
					} catch (BadElementException ex) {
						throw new PDFGenerationException();
					} catch (IOException ex) {
						throw new PDFGenerationException();
					}
				}

				@Override
				public String getImageRootPath() {
					throw new PDFGenerationException();
				}
			});
			// XML Worker with Pipelines
			new XMLParser(
					new XMLWorker(
							new CssResolverPipeline(cssResolver,
									new HtmlPipeline(htmlPipelineContext, new PdfWriterPipeline(document, writer))),
							true)).parse(new ByteArrayInputStream(page.toString().getBytes()));
			
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

		/**
		 * Event Handler that applies header and footer images at the start of
		 * every page.
		 * 
		 * @author Patrick Walsh
		 *
		 */
		class RevaturePDFPageEventHandler extends PdfPageEventHelper {

			byte[] foot;
			byte[] head;

			public RevaturePDFPageEventHandler() {
				try {
					this.head = IOUtils.toByteArray(HEADER);
					this.foot = IOUtils.toByteArray(FOOTER);
				} catch (IOException e) {
					log.error("Error reading header/footer image: " + e.getClass() + " --> " + e.getMessage());
					throw new PDFGenerationException();
				}
			}

			@Override
			public void onStartPage(PdfWriter writer, Document document) {
				// set header
				Image header;
				try {
					header = Image.getInstance(head);
					header.setAbsolutePosition(0, document.getPageSize().getHeight() - header.getScaledHeight());
					header.setSpacingAfter(20);
					writer.getDirectContent().addImage(header);
					String reportTitle = "<html><head><style> #report-title { color: #FFFFFF; font-size: 14pt; font-family: Arial; margin-bottom: 50px; }</style></head><body><div><h1 id='report-title'>"
							+ title + "</h1></div><br/></body></html>";
					XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(reportTitle));

				} catch (BadElementException | IOException e) {
					log.error("Error creating PDF file: " + e.getClass() + " --> " + e.getMessage());
					throw new PDFGenerationException();
				} catch (DocumentException e) {
					log.error("Error creating PDF file: " + e.getClass() + " --> " + e.getMessage());
					throw new PDFGenerationException();
				}

				// set footer
				Image footer;
				try {
					footer = Image.getInstance(foot);
					footer.setAbsolutePosition(0, 0);
					footer.setSpacingBefore(20);
					writer.getDirectContent().addImage(footer);
				} catch (BadElementException | IOException e) {
					log.error("Error creating PDF file: " + e.getClass() + " --> " + e.getMessage());
					throw new PDFGenerationException();
				} catch (DocumentException e) {
					log.error("Error creating PDF file: " + e.getClass() + " --> " + e.getMessage());
					throw new PDFGenerationException();
				}

			}
		}

	}

}