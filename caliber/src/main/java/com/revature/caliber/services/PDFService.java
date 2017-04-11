package com.revature.caliber.services;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class PDFService {

	private static final Logger log = Logger.getLogger(PDFService.class);
	private static final String TEMP_FILE = "report.pdf";
	
	/**
	 * Consumes HTML content and renders into a PDF format.
	 * Saves the file in memory and returns the filename.
	 * 
	 * @param html
	 * @return 
	 * @throws IOException
	 * @throws DocumentException
	 */
	public File getPDF(String html) throws IOException, DocumentException{
		byte[] data = generatePDF(html);
		File file = generateFile();
		FileUtils.writeByteArrayToFile(file, data);
		return file;
	}
	
	/**
	 * Consumes HTML content and renders into a PDF format.
	 * @param html
	 * @return Raw PDF as byte[]
	 * @throws DocumentException
	 * @throws IOException
	 */
	protected byte[] generatePDF(String html) throws DocumentException, IOException{
		log.debug(html);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();
        
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(html));

        document.close();
        return byteArrayOutputStream.toByteArray();
	}

	/**
	 * Returns file with a default name and extension
	 * @return
	 * @throws IOException 
	 */
	protected File generateFile() throws IOException{
		return new File(TEMP_FILE);
	}
	
}
