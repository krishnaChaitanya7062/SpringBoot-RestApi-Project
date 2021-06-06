package com.response;

import java.util.ArrayList;
import java.util.List;

import com.entity.Subject;
import com.entity.student;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentResponse {
	

	private long id;
	@JsonProperty("first_name")
	private String firstName;
	private String lastName;
	private String email;
	private String fullname;
	private String street;
	private String city;
	
	private List<SubjectResponse> learningSubjects;


	
	public StudentResponse(student stud )
	{
		this.id=stud.getId();
		this.firstName=stud.getFirstname();
		this.lastName=stud.getLastname();
		this.email=stud.getEmail();
		this.fullname=stud.getFirstname() + " " +stud.getLastname();
		this.street=stud.getAddress().getStreet();
		this.city=stud.getAddress().getCity();
		
		if(stud.getLearningSubjects() != null)
		{
			learningSubjects=new ArrayList<SubjectResponse>();
			for(Subject subject:stud.getLearningSubjects())
			{
				learningSubjects.add(new SubjectResponse(subject));
			}
		}


		
	}

}
