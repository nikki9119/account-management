package com.bank.accountmanagement.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.accountmanagement.Service.TransactionService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class TransactionControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	TransactionService service;
	
	@InjectMocks
	TransactionController controller;
		
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void DummyTest() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/")).
		 andExpect(status().isOk())
		 .andExpect(content().string("hello"));
	}
	@Test
	public void TransferTest() throws Exception{
		
		String jsonData = "{\r\n"
				+ "    \"senderId\":\"A001\",\r\n"
				+ "    \"amount\":200,\r\n"
				+ "    \"recieverId\":\"A002\"\r\n"
				+ "}";
		//when(service.Transfer("A001", 200, "A002")).thenReturn("Successfull transfer");
		 mockMvc.perform(post("/transfer")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(jsonData))
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.senderId", Matchers.is("A001")));
//		 .andExpect(jsonPath("$.recieverId", Matchers.is("A002")))
//		 .andExpect(content().string("Successfull transfer"));		 
	}
	
	@Test
	public void HistoryTest() throws Exception{
		
		String json = "{\r\n"
				+ "    \"accountNum\": \"A123123123\"\r\n"
				+ "}";
		
		mockMvc.perform(MockMvcRequestBuilders.get("/recent"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.accountNum").value("A123123123"))
		 .andExpect(content().string("Fetched history"));
	}
}
