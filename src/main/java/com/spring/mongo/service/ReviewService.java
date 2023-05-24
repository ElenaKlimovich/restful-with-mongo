package com.spring.mongo.service;

import com.spring.mongo.model.Review;
import com.spring.mongo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    public Review addReview(String body) {
        Review review = new Review(body, LocalDateTime.now(), LocalDateTime.now());
        return repository.insert(review);
    }
}
