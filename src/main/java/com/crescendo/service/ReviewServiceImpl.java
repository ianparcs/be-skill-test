package com.crescendo.service;

import com.crescendo.model.Business;
import com.crescendo.model.Review;
import com.crescendo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements IReviewService {

    private ReviewRepository reviewRepository;
    private IBusinessService businessService;

    @Override
    public Review createReview(Long businessId, Review review) {
        Optional<Business> business = businessService.findBusinessById(businessId);
        business.ifPresent(value -> {
            review.setBusiness(value);
            reviewRepository.save(review);
        });
        return review;
    }

    @Override
    public List<Review> findAllReviewByBusinessId(Long id) {
        return reviewRepository.findReviewsByBusinessId(id);
    }

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Autowired
    public void setBusinessService(IBusinessService businessService) {
        this.businessService = businessService;
    }
}
