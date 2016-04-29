package com.library.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.library.model.BookLoan;

@Transactional
public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {

}