package com.springboot.lombok.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Book;
import com.springboot.lombok.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void save(final Book book) {
        bookRepository.save(book);
    }

    public long getBooksCount() {
        return bookRepository.count();
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(final int bookId) {
        return bookRepository.findById(bookId);
    }

    public List<Book> getAllBooksByName(final String genre) {
        return bookRepository.findBookByName(genre);
    }

    public List<Book> getAllBooksByPhoneGreaterThanEqual(final int quantity) {
        return bookRepository.findBookByQuantityGreaterThanEqual(quantity);
    }

	public List<Book> getAllBooksByGenre(String genre) {
		return bookRepository.findBookByGenre(genre);
	}

	public List<Book> getAllBooksByQuantityGreaterThanEqual(final int quantity) {
		return bookRepository.findBookByQuantityGreaterThanEqual(quantity);
		
	}

	
}
