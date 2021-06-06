package com.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {
	
	@JsonProperty("first_name")
	@NotBlank(message="first name is required")
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String street;

	private String city;
	
	private List<CreateSubjectRequest> SubjectsLearning;


}
