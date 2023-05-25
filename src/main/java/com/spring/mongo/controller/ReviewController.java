package com.spring.mongo.controller;

import com.spring.mongo.model.Review;
import com.spring.mongo.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    @Operation(summary = "Create new Review and add to the Movie by imdbId",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Review added",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Bad request",
                            content = @Content()),
                    @ApiResponse(responseCode = "500", description = "Server error",
                            content = @Content()),
            })
    public ResponseEntity<Review> addReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(service.addReview(payload.get("body"), payload.get("imdbId")), HttpStatus.OK);
    }
}
