package com.crescendo.service;

import com.crescendo.model.Business;

import java.util.List;
import java.util.Optional;

public interface IBusinessService {

    List<Business> findAllBusiness();

    Optional<Business> findBusinessById(Long id);

    Boolean saveBusiness(Business business);

    Boolean deleteBusiness(Long id);


}
