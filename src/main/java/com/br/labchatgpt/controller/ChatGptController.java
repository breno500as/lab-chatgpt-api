package com.br.labchatgpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.labchatgpt.service.ChatGptService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/bot")
public class ChatGptController {

	@Autowired
	private ChatGptService service;

 
	@GetMapping("/chat")
	public String chat(@RequestParam("prompt") String prompt) throws JsonProcessingException {
		return service.chat(prompt);
	}

}
