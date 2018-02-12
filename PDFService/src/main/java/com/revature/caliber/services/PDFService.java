package com.revature.caliber.services;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.itextpdf.text.DocumentException;
import com.revature.caliber.pdf.RevaturePDF;

@Component
public class PDFService {

	/**
	 * Consumes HTML content and renders into a PDF format.
	 * 
	 * @param html
	 * @return Raw PDF as byte[]
	 * @throws DocumentException
	 * @throws IOException
	 */
	public byte[] generatePDF(String title, String html) throws DocumentException, IOException {
		//log.debug(html);
		RevaturePDF pdf = new RevaturePDF(title, html);
		return pdf.toByteArray();
	}
}
