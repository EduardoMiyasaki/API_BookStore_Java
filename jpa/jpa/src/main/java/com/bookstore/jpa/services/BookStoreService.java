package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookStoreRecordDto;
import com.bookstore.jpa.models.BookStoreModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import com.bookstore.jpa.repositories.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookStoreService {

    // Ponto de injeção
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookStoreService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<BookStoreModel> getAllBooks(){
        return bookRepository.findAll();
    }

    // utiliza essa anotação para se algo der errado
    // ela apaga tudo na base de dados e ter que fazer de novo
    @Transactional
    public BookStoreModel saveBook(BookStoreRecordDto bookStoreRecordDto){
        BookStoreModel book = new BookStoreModel();
        book.setTitle(bookStoreRecordDto.title());
        book.setPublisher(publisherRepository.findById(bookStoreRecordDto.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(bookStoreRecordDto.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(bookStoreRecordDto.reviewComment());
        reviewModel.setBook(book);
        book.setReviewModel(reviewModel);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);

    }

}
