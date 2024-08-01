package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.BookStoreRecordDto;
import com.bookstore.jpa.models.BookStoreModel;
import com.bookstore.jpa.services.BookStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books") // URI padrão
public class BookControlller {

    private final BookStoreService bookService;

    public BookControlller(BookStoreService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookStoreModel> saveBook(@RequestBody BookStoreRecordDto bookStoreRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookStoreRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<BookStoreModel>> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(value = "id") UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body("Book has been deleted");
    }
}
