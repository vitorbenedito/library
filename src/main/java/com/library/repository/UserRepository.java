package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.model.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

}