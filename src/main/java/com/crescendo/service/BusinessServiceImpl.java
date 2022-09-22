package com.crescendo.service;

import com.crescendo.model.Business;
import com.crescendo.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImpl implements IBusinessService {

    private BusinessRepository businessRepository;

    @Override
    public List<Business> findAllBusiness() {
        return businessRepository.findAll();
    }

    @Override
    public Optional<Business> findBusinessById(Long id) {
        return businessRepository.findById(id);
    }

    @Override
    public Boolean saveBusiness(Business business) {
        Business savedBusiness = businessRepository.save(business);
        return savedBusiness != null;
    }

    @Override
    public Boolean deleteBusiness(Long id) {
        businessRepository.deleteById(id);
        return businessRepository.existsById(id);
    }

    @Autowired
    public void setBusinessRepository(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
}
