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
import com.library.model.User;
import com.library.repository.BookLoanRepository;
import com.library.repository.UserRepository;

@Controller
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookLoanRepository bookLoanRepository;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public User create(@RequestBody User user) {
		return userRepository.save(user);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<User> list() {
		return (List<User>) userRepository.findAll();
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = "application/json")
	public User load(@PathVariable("id") Long id) {
		return userRepository.findOne(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public User update(@RequestBody User user, @PathVariable("id") Long id) {
		return userRepository.save(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public void remove(@PathVariable("id") Long id) {
		userRepository.delete(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/bookloans", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoan> findBookLoans(@PathVariable("id") Long id) {
		return bookLoanRepository.findByUser_IdAndReturnDateIsNull(id);
	}
	
}
