package com.example.demoTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demoTest.dto.studentdto;
import com.example.demoTest.model.student;

@Repository
public interface StudentRepository extends JpaRepository<studentdto, Integer>{

	@Query(value="select * from test u where u.name like %?%",nativeQuery = true)
	List<studentdto> findByName(String name);
	@Query(value="select * from test u where u.NRC =?",nativeQuery=true)
	List<studentdto> findByNrc(String nrc);
	@Query(value="select * from test u where u.id=?",nativeQuery = true)
	List<studentdto> findById(int id);
	
	
}
