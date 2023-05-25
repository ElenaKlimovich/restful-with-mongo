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

/**
 * @author elenaklimovich
 * @since 25.05.2023
 */
@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate template;

    /**
     * Create new review and update related {@link Movie} by adding to the attribute 'reviews' new object
     *
     * @param body   - text description of the reviewed movie
     * @param imdbId - identifier of the movie in <a href="https://www.imdb.com">IMDB</a>
     * @return created object {@link Review}
     */
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