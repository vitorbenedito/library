package com.library.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.library.model.BookLoan;

@Transactional
public interface BookLoanRepository extends CrudRepository<BookLoan, Long> {
	
	List<BookLoan> findByUser_IdAndReturnDateIsNull(Long userId);

}