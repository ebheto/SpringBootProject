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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springfarm.domain.Farm;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:farm-schema.sql", "classpath:farm-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")


public class FarmControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	public void testCreate() throws Exception {
		Farm newF = new Farm("Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L); 
		String newFJSON = this.mapper.writeValueAsString(newF);
		RequestBuilder mockRequest = post("/Farm/createFarm").contentType(MediaType.APPLICATION_JSON).content(newFJSON);
		
		Farm savedF = new Farm(2, "Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L);
		String savedFJSON = this.mapper.writeValueAsString(savedF);
		
		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedFJSON);
		
		this.mvc.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	@Test
	void readTest() throws Exception {
		Farm testF = new Farm(1, "Candyland", "Mississippi", "djan@go.com", 88813885L);
		List<Farm> allFarms = List.of(testF);
		String testFJSON = this.mapper.writeValueAsString(allFarms);

		RequestBuilder mockRequest = get("/Farm/getall");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testFJSON);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void readByIdTest() throws Exception {
		Farm readF = new Farm("Candyland", "Mississippi", "djan@go.com", 88813885L);
		String readFJSON = this.mapper.writeValueAsString(readF);
		
		RequestBuilder mockRequest = get("/Farm/getById/1").contentType(MediaType.APPLICATION_JSON).content(readFJSON);
		
		Farm readFById = new Farm(1, "Candyland", "Mississippi", "djan@go.com", 88813885L);
		String readFByIdJSON = this.mapper.writeValueAsString(readFById);
		
		ResultMatcher matchStatus = status().isOk();
		ResultMatcher matchBody = content().json(readFByIdJSON);
		
		this.mvc.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void updateTest() throws Exception {
		Farm newF = new Farm("Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L);
		String newFJSON = this.mapper.writeValueAsString(newF);
	
		RequestBuilder mockRequest = put("/Farm/updateFarm/1").contentType(MediaType.APPLICATION_JSON).content(newFJSON);
	    
		Farm savedF = new Farm(1, "Manor farm", "Manor road", "oldmajor@animal.farm", 0123154L);
		String savedFJSON = this.mapper.writeValueAsString(savedF);
		
		ResultMatcher matchStatus = status().isAccepted();
		ResultMatcher matchBody = content().json(savedFJSON);
		
		this.mvc.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
    }
	
	@Test
	void deleteTest() throws Exception {
		RequestBuilder mockRequest = delete("/Farm/deleteFarm/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().string("true");

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
}
