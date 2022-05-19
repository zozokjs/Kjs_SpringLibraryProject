package com.kjs.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	
	@GetMapping("/auth/signin")
	public String signinPage() {
		return "auth/signin";
	}

}