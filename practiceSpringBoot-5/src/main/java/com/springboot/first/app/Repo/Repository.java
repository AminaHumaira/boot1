package com.springboot.first.app.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.first.app.Pojo.Pojo;

public interface Repository extends JpaRepository<Pojo,Integer>
{
	

}
