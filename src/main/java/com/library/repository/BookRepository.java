package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.library.model.Book;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

}