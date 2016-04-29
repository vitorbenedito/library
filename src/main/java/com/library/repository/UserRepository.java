package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.library.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

}