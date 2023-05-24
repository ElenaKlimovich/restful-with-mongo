package com.spring.mongo.service;

import com.spring.mongo.model.Movie;
import com.spring.mongo.model.Review;
import com.spring.mongo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate template;

    public Review addReview(String body, String imdbId) {
        Review review = new Review(body, LocalDateTime.now(), LocalDateTime.now());
        repository.insert(review);

        template.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;
    }
}