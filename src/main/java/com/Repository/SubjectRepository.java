package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

}
