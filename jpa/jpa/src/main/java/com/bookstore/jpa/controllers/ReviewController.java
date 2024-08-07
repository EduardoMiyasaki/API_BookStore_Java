package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.ReviewRecordDto;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;


    @PostMapping("/review")
    public ResponseEntity<ReviewModel> saveReview(@RequestBody ReviewRecordDto reviewRecordDto){

        //criando uma model
        var reviewModel = new ReviewModel();
        //transferindo os dados
        BeanUtils.copyProperties(reviewRecordDto , reviewModel);
        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.save(reviewModel));

    }
}
