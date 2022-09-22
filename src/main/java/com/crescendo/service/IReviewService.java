package com.crescendo.service;

import com.crescendo.model.Review;

import java.util.List;

public interface IReviewService {

    Review createReview(Long id, Review review);

    List<Review> findAllReviewByBusinessId(Long id);
}
