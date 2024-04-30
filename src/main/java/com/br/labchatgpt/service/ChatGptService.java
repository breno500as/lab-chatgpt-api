package com.br.labchatgpt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.labchatgpt.dto.ChatGptRequest;
import com.br.labchatgpt.dto.ChatGptResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatGptService {

	private Logger logger = LoggerFactory.getLogger(ChatGptService.class);

	@Value("${openai.api.model}")
	private String model;

	@Value("${openai.api.url}")
	private String url;

	@Autowired
	private RestTemplate template;

	public String chat(String prompt) throws JsonProcessingException {

		logger.info("Starting Prompt");

		ChatGptRequest request = new ChatGptRequest(model, prompt);
		
         String json = new ObjectMapper().writeValueAsString(request);
        
          logger.info(json);

		logger.info("Processing Prompt");
		ChatGptResponse response = template.postForObject(url, request, ChatGptResponse.class);

		return response.getChoices().get(0).getMessage().getContent();
	}

}
