package com.revature.caliber.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;
import com.revature.caliber.pdf.RevaturePDF;

@Service
public class PDFService {

	private static final Logger log = Logger.getLogger(PDFService.class);
	private final InputStream header = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/header.png");
	private final InputStream footer = RevaturePDF.class.getClassLoader().getResourceAsStream("pdf/footer.png");
	private final InputStream htmlTemplate = RevaturePDF.class.getClassLoader()
			.getResourceAsStream("pdf/template.html");
	private final InputStream cssStyles = RevaturePDF.class.getClassLoader().getResourceAsStream("css/style.css");

	private ByteArrayOutputStream byteArrayOutputStream;

	// Encloses whatever HTML provided to the method
	private String openHtml;
	private String closeHtml = "</body></html>";

	private String title;

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
	 * Consumes HTML content and renders into a PDF format.
	 * 
	 * @param html
	 * @return Raw PDF as byte[]
	 * @throws DocumentException
	 * @throws IOException
	 */
	public byte[] NEWgeneratePDF(String title, String html) throws IOException {
		log.trace(html);
		html = formatHtml(html);
		Document dom = null;
		try {
			dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(html);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dom.getDocumentElement().getNodeName().getBytes();
	}

	private String formatHtml(String html) throws IOException {
		// PDF generator needs 'properly' closed <img> tags
		String formattedHtml = html.replaceAll("(<img[^>]*[^/]>)(?!\\s*</img>)", "$1</img>");

		// Replace check, minus, and remove glyphicons with readable values
		formattedHtml = formattedHtml.replaceAll(" class=\"glyphicon glyphicon-ok ng-scope\">", ">Pass");
		formattedHtml = formattedHtml.replaceAll(" class=\"glyphicon glyphicon-remove ng-scope\">", ">Fail");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-check fa-2x panel-glyph\">", ">Pass");
		formattedHtml = formattedHtml.replaceAll(" class=\"glyphicon glyphicon-remove fa-2x panel-glyph\">", ">Fail");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-minus fa-2x panel-glyph\">", ">N/A");

		// Replace smiley icons with readable values
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-star fa-2x pick mouse-over\">", ">Superstar");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-smile-o fa-2x pick mouse-over\">", ">Good");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-meh-o fa-2x pick mouse-over\">", ">Average");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-frown-o fa-2x pick mouse-over\">", ">Poor");
		formattedHtml = formattedHtml.replaceAll(" class=\"fa fa-question-circle fa-2x mouse-over\">", ">N/A");

		// initialize document state
		BufferedReader open = new BufferedReader(
				new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("pdf/open.html")));
		StringBuilder openText = new StringBuilder();
		String tags;
		while ((tags = open.readLine()) != null) {
			openText.append(tags);
		}
		this.openHtml = openText.toString();

		// hold template contents
		StringBuilder page = new StringBuilder();
		page.append(openHtml);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(htmlTemplate))) {
			String temp;
			while ((temp = reader.readLine()) != null) {
				page.append(temp);
			}
		}
		// find the body of document & add generated contents
		String marker = "<!-- CALIBER-REPORT -->";
		int bodyLocation = page.indexOf(marker);
		page.replace(bodyLocation, bodyLocation + marker.length(), formattedHtml);
		page.append(closeHtml);
		return page.toString();
	}

}
