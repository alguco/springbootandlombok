package com.springboot.lombok.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lombok.exception.BookNotFoundException;
import com.springboot.lombok.model.Book;
import com.springboot.lombok.service.BookService;

import lombok.extern.slf4j.Slf4j;

// NOTE - We have left the implementation of HATEOAS principle for simplicity.

// Causes lombok to generate a logger field.
@Slf4j
@RestController
@RequestMapping(value = "/api/")
public class BookController {

    @Autowired
    BookService bookService;

    // URL - http://localhost:10093/api/books
    @GetMapping(value = "books")
    public ResponseEntity<List<Book>> getBooks() {
        log.info("Getting all books from the dB.");
        final Iterable<Book> bookIterable = bookService.getAllBooks();
        final List<Book> books = StreamSupport.stream(bookIterable.spliterator(), false).collect(Collectors.toList());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/book/id/1
    @GetMapping(value = "book/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") final int bookId) {
        log.info("Getting book with book-id= {} from the dB.", bookId);
        final Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id= " + bookId + "not found in the dB."));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/book/genre/Mythopoeia
    @GetMapping(value = "book/genre/{genre}")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable(name = "genre") final String genre) {
        log.info("Getting book(s) for genre= {} from the dB.", genre);
        final List<Book> books = bookService.getAllBooksByGenre(genre);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/book/quantity/52
    @GetMapping(value = "book/quantity/{quantity}")
    public ResponseEntity<List<Book>> getBooksByQuantityGreaterThanEqual(
            @PathVariable(name = "quantity") final int quantity) {
        log.info("Getting book(s) from the dB where quantity is greater-than or equal to= {}.", quantity);
        final List<Book> books = bookService.getAllBooksByQuantityGreaterThanEqual(quantity);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // URL - http://localhost:10093/api/book/save
    // Sample HTTP POST request body.
    /*
    {
        "author": "Vasdev Mohi",
        "genre": "Ghazals",
        "publisher": "Central Sahitya Akademi",
        "title": "Cheque book",
        "quantity": 1,
        "publishedOn": "2020-09-11T11:11:36Z"
    }
    */
    @PostMapping(value = "book/save")
    public ResponseEntity<Void> save(@RequestBody final Book book) {
        log.info("Saving book with details= {} in the dB.", book.toString());
        bookService.save(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
