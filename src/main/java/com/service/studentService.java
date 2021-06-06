package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Repository.AddressRepository;
import com.Repository.SubjectRepository;
import com.Repository.studentRepository;
import com.entity.Address;
import com.entity.Subject;
import com.entity.student;
import com.request.CreateStudentRequest;
import com.request.CreateSubjectRequest;
import com.request.UpdateStudentRequest;

@Service
public class studentService {
	
	@Autowired
	studentRepository studrepo;
	
	@Autowired
	AddressRepository addrepo;
	
	@Autowired
	SubjectRepository subrepo;
	
	public List<student> getAllStudents()
	{
		return studrepo.findAll();
	}
	
	public student createStudent(CreateStudentRequest createStudentRequest)
	{
		student s=new student(createStudentRequest);
		Address a=new Address();
		a.setCity(createStudentRequest.getCity());
		a.setStreet(createStudentRequest.getStreet());
		a=addrepo.save(a);
		s.setAddress(a);
		s=studrepo.save(s);
		List<Subject> subjectsList=new ArrayList<Subject>();
		if(createStudentRequest.getSubjectsLearning() != null)
		{
			for(CreateSubjectRequest CreateSubjectRequest:createStudentRequest.getSubjectsLearning())
			{
				Subject subject=new Subject();
				subject.setSubjectName(CreateSubjectRequest.getSubjectName());
				subject.setMarksObtained(CreateSubjectRequest.getMarksObtained());
				subject.setStudent(s);
				subjectsList.add(subject);
			}
			subrepo.saveAll(subjectsList);
		}
		s.setLearningSubjects(subjectsList);
		return s;
	}
	public student UpdateStudent(UpdateStudentRequest updateStudentRequest)
	{
		student s =studrepo.findById(updateStudentRequest.getId()).get();
		if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty())
		{
			s.setFirstname(updateStudentRequest.getFirstName());
		}
		 s=studrepo.save(s);
		return s;
	}
	public String DeleteStudent(long id)
	{
		studrepo.deleteById(id);
		return "Student has been deleted succesfully.";
	}
	
	public List<student> getByFirstName(String firstName)
	{
		return studrepo.findByFirstname(firstName);
		
	}
	public student getByFirstNameAndLastName(String firstname,String lastname)
	{
		return studrepo.findByFirstnameAndLastname(firstname, lastname);
		
	}
	public List<student> getByFirstNameOrLastName(String firstname,String lastname)
	{
		return studrepo.findByFirstnameOrLastname(firstname, lastname);
		
	}
	public List<student> getByFirstNameIn(com.request.InQueryRequest inQueryRequest)
	{
		return studrepo.findByFirstnameIn(inQueryRequest.getFirstNames());
	}
	public List<student> getAllStudentsWithPagination(int pageNo,int pageSize)
	{
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return studrepo.findAll(pageable).getContent();
	}
	public List<student> getAllStudentsWithSorting()
	{
		Sort sort=Sort.by(Sort.Direction.ASC, "Firstname");
		return studrepo.findAll(sort);
	}
	public List<student> like(String firstName)
	{
	
		return studrepo.findByFirstnameContains(firstName);
	}
	public List<student> startsWith(String firstName)
	{
		return studrepo.findByFirstnameStartsWith(firstName);
	}
	public List<student> getByCity(String city)
	{
		return studrepo.getByAddressCity(city);
	}
	
	
}
