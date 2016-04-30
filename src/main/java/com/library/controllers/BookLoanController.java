package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.BookLoan;
import com.library.repository.BookLoanRepository;

@Controller
@RequestMapping("/api/bookloans")
public class BookLoanController {
	
	@Autowired
	private BookLoanRepository bookLoanRepository;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public BookLoan create(@RequestBody BookLoan bookLoan) {
		return bookLoanRepository.save(bookLoan);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<BookLoan> list() {
		return (List<BookLoan>) bookLoanRepository.findAll();
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
	public BookLoan load(@PathVariable("id") Long id) {
		return bookLoanRepository.findOne(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public BookLoan update(@RequestBody BookLoan bookLoan, @PathVariable("id") Long id) {
		return bookLoanRepository.save(bookLoan);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void remove(@PathVariable("id") Long id) {
		bookLoanRepository.delete(id);
	}
	
}
