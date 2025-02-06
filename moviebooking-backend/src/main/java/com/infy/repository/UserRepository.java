package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

}
