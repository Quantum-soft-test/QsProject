package com.qs.task.poc.repository;

import com.qs.task.poc.model.QSUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<QSUser, String>{

  QSUser findByuserId(String username);

}
