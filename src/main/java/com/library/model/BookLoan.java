package com.library.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOK_LOAN")
public class BookLoan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private Book book;
    
	@JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @Temporal(value=TemporalType.DATE)
	private Date loadDate;
	
    @Temporal(value=TemporalType.DATE)
	private Date returnDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
}
