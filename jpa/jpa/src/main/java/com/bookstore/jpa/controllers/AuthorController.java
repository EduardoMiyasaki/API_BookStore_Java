package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.AuthorRecordDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import org.aspectj.weaver.patterns.HasMemberTypePatternForPerThisMatching;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/authors")
    public ResponseEntity<AuthorModel> saveAuthor(@RequestBody AuthorRecordDto authorRecordDto){

        //instanciando um Author Model
        var authorModel = new AuthorModel();
        // trasnferindo do dto para Model
        BeanUtils.copyProperties(authorRecordDto , authorModel);
        //salvando
        return ResponseEntity.status(HttpStatus.CREATED).body(authorRepository.save(authorModel));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorModel>> getAllAuthors(){
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.findAll());
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Object> getOneAuthor(@PathVariable(value = "id") UUID id){

        Optional<AuthorModel> author0 = authorRepository.findById(id);

        if(author0.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not Found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(author0.get());
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable(value = "id")UUID id , @RequestBody AuthorRecordDto authorRecordDto){

        Optional<AuthorModel> author0 = authorRepository.findById(id);

        if(author0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }

        var authorModel = author0.get();
        //tranferindo os dados
        BeanUtils.copyProperties(authorRecordDto ,  authorModel);
        return ResponseEntity.status(HttpStatus.OK).body(authorRepository.save(authorModel));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable(value = "id")UUID id){

        Optional<AuthorModel> author0 = authorRepository.findById(id);

        if(author0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        }
        authorRepository.delete(author0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Auhtor has been deleted");
    }
}
