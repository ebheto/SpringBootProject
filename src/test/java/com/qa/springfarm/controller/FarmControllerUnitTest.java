package com.qa.springfarm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springfarm.domain.Farm;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:farm-schema.sql", "classpath:farm-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class FarmControllerUnitTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper map;
	
	@Test
	void createTest() throws Exception {
//		create a farm
		Farm newF = new Farm("Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L);
//		 convert to JSON string
		String newFJSON = this.map.writeValueAsString(newF);
//		build mock request
		RequestBuilder mockRequest = post("/createFarm").contentType(MediaType.APPLICATION_JSON).content(newFJSON);
		
//		response
		Farm savedF = new Farm(2 ,"Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L);
		String savedFJSON = this.map.writeValueAsString(savedF);
		
//		testing method - to see if request and response matches
		ResultMatcher matchStatus = status().isCreated();
//		test response body
		ResultMatcher matchBody = content().json(savedFJSON);
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readTest() throws Exception {
		Farm readF = new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);
		List<Farm> allFarm = List.of(readF);
		String readFJSON = this.map.writeValueAsString(allFarm);

		RequestBuilder readReq = get("/getall");

		ResultMatcher status = status().isOk();
		ResultMatcher body = content().json(readFJSON);

		this.mock.perform(readReq).andExpect(status).andExpect(body);
	}
	
	@Test
	void readByIdTest () throws Exception {
		Farm readF = new Farm ("Candyland", "Mississippi", "djan@go.com", 88813885L);
		String readFJSON = this.map.writeValueAsString(readF);
		long readId = 3;
		
		RequestBuilder readIdReq = get("/getById/3" + readId).contentType(MediaType.APPLICATION_JSON).content(readFJSON);
		
		Farm readFById = new Farm (3, "Candyland", "Mississippi", "djan@go.com", 88813885L);
		String readFByIdJSON = this.map.writeValueAsString(readFById);
		
		ResultMatcher readIdStatus = status().isOk();
		ResultMatcher readIdBody = content().json(readFByIdJSON);
		
		this.mock.perform(readIdReq).andExpect(readIdStatus).andExpect(readIdBody);
	}
	
	@Test
	void updateTest() throws Exception {
		Farm updateF = new Farm("Cold Comfort Farm", "Howling, Sussex", "ccf@poste.com", 166542L);
		String updateFJSON = this.map.writeValueAsString(updateF);
	    long IDupdate = 1;

		RequestBuilder updateReq = put("/updateFarm/" + IDupdate).contentType(MediaType.APPLICATION_JSON)
				.content(updateFJSON);

		Farm retUpdatedF = new Farm(1, "Cold Comfort Farm", "Howling, Sussex", "ccf@poste.com", 166542L);
		String retUpdatedFJSON = this.map.writeValueAsString(retUpdatedF);

		ResultMatcher retStatus = status().isAccepted();
		ResultMatcher retBody = content().json(retUpdatedFJSON);

		this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
	}

	@Test
	void deleteTest() throws Exception {
		Farm deleteF = new Farm(1, "Old Macdonald", "EIEIO", "oldmac@moo.com", 43658709L);
		String deleteFJSON = this.map.writeValueAsString(deleteF);
		
		long remId = 1;
		RequestBuilder delRequest = delete("/deleteFarm/" + remId);
		ResultMatcher Status = status().isOk();
		ResultMatcher Body = content().json(deleteFJSON);

		this.mock.perform(delRequest).andExpect(Status).andExpect(Body);
	}
	
}
