package com.qs.task.poc.service;

import com.qs.task.poc.model.QSUser;
import com.qs.task.poc.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements CategoryService{



	@Autowired
    Repo repo;
@Override
  public QSUser getUser(String username) {
    QSUser user= repo.findByuserId(username);
    return user;
  }

}



