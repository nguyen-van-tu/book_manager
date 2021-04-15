package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.book.IBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin("*")

public class BookController {
    @Autowired
    private IBookService bookService;

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> list = bookService.findALl();
        return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<Book> create(@RequestBody Book book) {
        bookService.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

}
