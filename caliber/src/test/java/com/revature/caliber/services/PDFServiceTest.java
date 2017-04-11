package com.revature.caliber.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itextpdf.text.DocumentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class PDFServiceTest {

	@Autowired
	private PDFService pdfService;
	private Logger log = Logger.getLogger(PDFServiceTest.class);
	
	@Test
	public void go(){
		try {
			byte[] bytes = pdfService.generatePDF("<html><body><h1>Hello World</h1></body></html>");
			File file = new File("report.pdf");
			log.trace("Creating test PDF file");
			FileOutputStream out = new FileOutputStream(file);
			out.write(bytes);
			out.close();
			assertTrue(file.exists());
			log.trace("Test PDF file exists!");
			file.delete();
			log.trace("Cleaning up test file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		} catch (DocumentException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
