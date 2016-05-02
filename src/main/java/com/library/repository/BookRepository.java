package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Book;

@Repository
@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {
	
}