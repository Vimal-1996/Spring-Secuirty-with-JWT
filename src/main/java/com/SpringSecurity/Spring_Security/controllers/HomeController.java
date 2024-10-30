package com.SpringSecurity.Spring_Security.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringSecurity.Spring_Security.model.Student;

import jakarta.servlet.http.HttpServletRequest;

@RestController

public class HomeController {
	
	private List<Student> students = new ArrayList<>();
	

	public HomeController() {
		students.add(new Student(1, "vimal", 60));
		students.add(new Student(2, "mathew", 70));
	}
	
	@GetMapping("/")
	public String greet(HttpServletRequest request) {
		return "Welcome to telusko" + request.getSession().getId();
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		return students;
	}
	
	/*
	 * methods like PUT, POST and DELETE requires CSRF token to complete tasks.
	 * In the absence of csrf token, the method returns 401 unauthorized error.
	 */
	@PostMapping("/students")
	public List<Student> addStudent() {
		students.add(new Student(3, "vishnu", 100));
		return students;
	}
	
	/**
	 * Method to get CSRF token,
	 * when cstf token is passed along with header and auth in PUT request, the request gets completed
	 */
	
	@GetMapping("/csrf-token")
	public CsrfToken csrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	}
}
