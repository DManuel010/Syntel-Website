package com.syntinel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController{
	
	private static final String PATH = "/error";
	
	@RequestMapping(value=PATH, method = RequestMethod.GET)
	public String error(HttpServletRequest httpRequest) {
		
		int httpErrorCode = getErrorCode(httpRequest);
		
		if(httpErrorCode==404) {
			return "error404page";
		}
		
		else {
			return "errorpage";
		}
		
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	 private int getErrorCode(HttpServletRequest httpRequest) {
	        return (Integer) httpRequest
	          .getAttribute("javax.servlet.error.status_code");
	    }

}
