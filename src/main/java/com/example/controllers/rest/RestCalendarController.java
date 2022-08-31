package com.example.controllers.rest;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CalendarRecord;
import com.example.model.User;
import com.example.service.PostRecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


	@RestController
	@RequestMapping("/events")
	public class RestCalendarController{
		
		private final PostRecordService postRecordService;
		
		public RestCalendarController(PostRecordService postRecordService) {
			this.postRecordService = postRecordService;
		}
		
		@GetMapping(value = "/all",produces="application/json;charset=UTF-8")
	    public String getCalendarRecord(@AuthenticationPrincipal User details) throws JsonProcessingException{
	        String jsonMsg = null;
	        List<CalendarRecord> records = postRecordService.findAllCalendarRecords(details.getUsername());
	        
	        ObjectMapper mapper = new ObjectMapper();
	        jsonMsg =  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);
	        
	        return jsonMsg;
		}

}

