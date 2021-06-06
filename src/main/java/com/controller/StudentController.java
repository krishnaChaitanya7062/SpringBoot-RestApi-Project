package com.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.student;
import com.request.CreateStudentRequest;
import com.request.InQueryRequest;
import com.request.UpdateStudentRequest;
import com.response.StudentResponse;
import com.service.studentService;


@RestController
@RequestMapping("/api/student/")
public class StudentController {
	
	@Autowired
	studentService stuservice;
	
	Logger logger=LoggerFactory.getLogger(StudentController.class);
	
	@GetMapping("getall")
	public List<StudentResponse> getAllStudents() {
		
		logger.error("Inside error");
		logger.warn("Inside warn");
		logger.info("Inside info ");
		logger.debug("Inside debug");
		logger.trace("Inside trace");
		
		
		List<student> studentList= stuservice.getAllStudents();
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		
	}
	
	@PostMapping("create")
	public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest createStudentRequest)
	{
		student s = stuservice.createStudent(createStudentRequest);
		return new StudentResponse(s);
	}
	
	@PutMapping("update")
	public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest)
	{
		student s = stuservice.UpdateStudent(updateStudentRequest);
		return new StudentResponse(s);
	}
	
	@DeleteMapping("delete/{Id}")
	public String DeleteStudent(@PathVariable Long Id)
	{
		return stuservice.DeleteStudent(Id);
	}
	
	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName(@PathVariable String firstName)
	{
		List<student> studentlist=stuservice.getByFirstName(firstName);
     List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		
	}
	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByfirstNameAndLastName(@PathVariable String firstName,@PathVariable String lastName)
	{
		return new StudentResponse(stuservice.getByFirstNameAndLastName(firstName, lastName));
	}
  
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByfirstNameOrLastName(@PathVariable String firstName,@PathVariable String lastName)
	{
		List<student> studentlist=stuservice.getByFirstNameOrLastName(firstName,lastName);
		
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn(@RequestBody InQueryRequest inQueryRequest )
	{  
		logger.info("inQueryMessage = " +inQueryRequest);
		List<student> studentlist=stuservice.getByFirstNameIn(inQueryRequest);
		
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		 logger.info("studentResponseList = " +studentResponseList);
		return studentResponseList;
	}
	
	@GetMapping("getAllWithPagination")
	public List<StudentResponse> getAllStudentsWithPagination(@RequestParam int pageNo,@RequestParam  int pageSize)
	{
		List<student> studentlist=stuservice.getAllStudentsWithPagination(pageNo,pageSize);
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("getAllWithSorting")
	public List<StudentResponse> getAllStudentsWithSorting()
	{
		List<student> studentlist=stuservice.getAllStudentsWithSorting();
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
		
	}
	@GetMapping("like/{firstName}")
	public List<StudentResponse> like(@PathVariable String firstName)
	{
		List<student> studentlist=stuservice.like(firstName);
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	@GetMapping("startsWith/{firstName}")
	public List<StudentResponse> startsWith(@PathVariable String firstName)
	{
		List<student> studentlist=stuservice.startsWith(firstName);
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}
	
	@GetMapping("/getByCity/{city}")
	public List<StudentResponse>  getByCity(@PathVariable String city)
	{
		List<student> studentlist=stuservice.getByCity(city);
		List<StudentResponse> studentResponseList =new ArrayList<StudentResponse>();
		
		 studentlist.stream().forEach(student ->{
			
			studentResponseList.add(new StudentResponse(student));
		});
		return studentResponseList;
	}

	

}
