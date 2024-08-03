package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.PublisherRecordDto;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.repositories.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Flow;

@RestController
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @PostMapping("/publisher")
    public ResponseEntity<PublisherModel> savePublisher(@RequestBody PublisherRecordDto publisherRecordDto){

        // Instanciando um publisher model
        var publisherModel = new PublisherModel();
        // transferindo os dados de dto para Model
        BeanUtils.copyProperties(publisherRecordDto , publisherModel);
        // salvando o publisher
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherRepository.save(publisherModel));
    }

    @GetMapping("/publisher")
    public ResponseEntity<List<PublisherModel>> getAllPublisher(){

        return ResponseEntity.status(HttpStatus.OK).body(publisherRepository.findAll());
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<Object> getOnePublisher(@PathVariable(value = "id") UUID id){

        Optional<PublisherModel> publisher0 = publisherRepository.findById(id);

        if(publisher0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher is not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(publisher0.get());
    }

    @PutMapping("/publisher/{id}")
    public ResponseEntity<Object> updatePublisher(@PathVariable(value = "id")UUID id, @RequestBody PublisherRecordDto publisherRecordDto){

     Optional<PublisherModel> publisher0 = publisherRepository.findById(id);

     if(publisher0.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher is not found");
     }

        var publisherModel = publisher0.get();
        // transferindo os dados
        BeanUtils.copyProperties(publisherRecordDto , publisherModel);
        // salvando
        return ResponseEntity.status(HttpStatus.OK).body(publisherRepository.save(publisherModel));
    }

    @DeleteMapping("/publisher/{id}")
    public ResponseEntity<Object> deletePublisher(@PathVariable(value = "id")UUID id){

        Optional<PublisherModel> publisher0 = publisherRepository.findById(id);

        if(publisher0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher is not found");
        }
        publisherRepository.delete(publisher0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Publisher has been deleted");
    }
}
