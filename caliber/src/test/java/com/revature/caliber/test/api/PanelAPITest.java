package com.revature.caliber.test.api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.beans.Panel;
import com.revature.caliber.beans.PanelFeedback;
import com.revature.caliber.data.PanelDAO;
import com.revature.caliber.data.PanelFeedbackDAO;

import io.restassured.http.ContentType;

/**
 * @author Nathan Koszuta
 * @author Connor Monson
 */

public class PanelAPITest extends AbstractAPITest {

	private static final Logger log = Logger.getLogger(PanelAPITest.class);
	@Autowired
	private PanelDAO panelDAO;
	@Autowired
	private PanelFeedbackDAO pfDAO;
	
	private String createPanel = "trainer/panel/create";
	private String deletePanel = "trainer/panel/delete/{panel}";
	private String updatePanel = "trainer/panel/update";

	/**
	 * Tests creation. Asserts that the status code 201 is returned meaning the
	 * creation was successful
	 */
	@Test
	public void testCreate201() {
		log.info("Creating a new Panel type");
		Panel panel = new Panel();
		given().spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).body(panel).when()
				.post(baseUrl + createPanel).then().assertThat().statusCode(201);
	}

	/**
	 * Tests updating an existing panel by changing the duration. Asserts
	 * whats returned is the same as what we sent in the request
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testUpdate() throws Exception {
		log.info("Updating an panel");
		Panel expected = new Panel();
		expected.setId(2057);
		expected.setDuration("100 hours");
		Panel actual = new ObjectMapper()
				.readValue(
						given().spec(requestSpec).header(AUTH, accessToken).contentType(ContentType.JSON).body(expected)
								.when().put(baseUrl + updatePanel).then().contentType(ContentType.JSON)
								.assertThat().statusCode(200).and().extract().response().asString(),
						new TypeReference<Panel>() {
						});
		assertEquals(expected.getDuration(), actual.getDuration());
	}

	/**
	 * Tests delete and asserts the appropriate status code is returned (204)
	 */
	@Test
	public void testDelete() {
		log.info("Deleting an panel");
		given().spec(requestSpec).header(AUTH, accessToken).when().delete(baseUrl + deletePanel, 2050).then()
				.assertThat().statusCode(204);
	}
}
