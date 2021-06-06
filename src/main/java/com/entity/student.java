package com.entity;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.request.CreateStudentRequest;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="student")
public class student {
	
	@Column(name="Id")
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long Id;
	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="email")
	private String email;
	
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;
	
	@Transient
	private String fullname;
	
	@OneToMany(mappedBy="student")
	private List<Subject> learningSubjects;
	
	public student(CreateStudentRequest CreateStudentRequest) {
		this.firstname=CreateStudentRequest.getFirstName();
		this.lastname=CreateStudentRequest.getLastName();
		this.email=CreateStudentRequest.getEmail();
		this.fullname=CreateStudentRequest.getFirstName()+" "+CreateStudentRequest.getLastName();

		
	}
	

}
