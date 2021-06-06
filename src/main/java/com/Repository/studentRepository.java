package com.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.student;

@Repository
public interface studentRepository extends JpaRepository<student,Long>{
	List<student> findByFirstname(String firstname);
    student findByFirstnameAndLastname(String firstname,String lastname);
    List<student> findByFirstnameOrLastname(String firstname,String lastname);
    List<student> findByFirstnameIn(List<String> firstNames);
    List<student> findByFirstnameContains(String firstname);
    List<student> findByFirstnameStartsWith(String firstname);
    List<student> findByAddressCity(String city);
    @Query("From student where address.city = :city")
    List<student> getByAddressCity(String city);
    
}
