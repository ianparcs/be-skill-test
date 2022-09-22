package com.crescendo.controller.rest;

import com.crescendo.model.Review;
import com.crescendo.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/businesses")
public class ReviewController extends BaseController<Review> {

    private IReviewService IReviewService;

    @GetMapping("/{id}/reviews")
    protected ResponseEntity<List<Review>> findAll(@PathVariable("id") Long id) {
        List<Review> reviews = IReviewService.findAllReviewByBusinessId(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("{id}/reviews")
    protected ResponseEntity<Review> saveReview(@PathVariable("id") Long id, @RequestBody Review review) {
        if (review == null || id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Review savedReview = IReviewService.createReview(id, review);
        if (savedReview == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }

    @Autowired
    public void setIReviewService(IReviewService IReviewService) {
        this.IReviewService = IReviewService;
    }

    @Override
    ResponseEntity<Review> saveEntity(Review review) {
        return null;
    }
}
