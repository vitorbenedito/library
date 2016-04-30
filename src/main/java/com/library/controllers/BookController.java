package com.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.model.Book;
import com.library.repository.BookRepository;

@Controller
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Book create(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Book> list() {
		return (List<Book>) bookRepository.findAll();
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
	public Book load(@PathVariable("id") Long id) {
		return bookRepository.findOne(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public Book update(@RequestBody Book book, @PathVariable("id") Long id) {
		return bookRepository.save(book);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void remove(@PathVariable("id") Long id) {
		bookRepository.delete(id);
	}

}
