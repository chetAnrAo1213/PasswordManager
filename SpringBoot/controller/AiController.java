package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.boot.api.models.AiModel;
import com.boot.api.service.AbusiveService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/AiModel/Chat/")
public class AiController {

    @Autowired
    AiModel aim;

    @Autowired
    AbusiveService abs;
    
    @PostMapping("/UserMessage")
    public String userRequest(@RequestParam("usermsg") String msg, Model model, HttpSession session) {
        try {
            aim.setUserMessage(msg);
            ResponseEntity<String> responseEntity = processChatMessage(msg);
            String responseContent = aim.getModelMessage();

            // Retrieve the conversation history from the session or create a new list
            List<Map<String, String>> conversationHistory = (List<Map<String, String>>) session.getAttribute("history");
            if (conversationHistory == null) {
                conversationHistory = new ArrayList<>();
            }

            // Add the current message and response to the history
            Map<String, String> conversation = new HashMap<>();
            conversation.put("user", msg);
            conversation.put("bot", responseContent);
            conversationHistory.add(conversation);

            // Update the session attribute
            session.setAttribute("history", conversationHistory);

            // Pass the conversation history to the view
            model.addAttribute("history", conversationHistory);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
        }
        return "/ChatBot";
    }

    private ResponseEntity<String> processChatMessage(String userMessage1) {
        try {
        	
            String userMessage = aim.getUserMessage();
            if (userMessage == null || userMessage.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User message is required.");
            }

            String apiUrl = aim.hostUrl;

            Map<String, Object> input = new HashMap<>();
            input.put("model", aim.aiModel);

            Map<String, String> inputMsg = new HashMap<>();
            inputMsg.put("role", "user");
            inputMsg.put("content", userMessage);

            input.put("messages", new Object[]{inputMsg});

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(aim.apiKey);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(input, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            String content = rootNode.path("choices").get(0).path("message").path("content").asText();

            aim.setModelMessage(content);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing request: " + e.getMessage());
        }
    }
}
