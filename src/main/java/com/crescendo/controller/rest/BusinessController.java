package com.crescendo.controller.rest;

import com.crescendo.model.Business;
import com.crescendo.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/businesses")
public class BusinessController extends BaseController<Business> {

    private IBusinessService IBusinessService;

    @GetMapping
    protected ResponseEntity<List<Business>> findAll() {
        List<Business> businesses = IBusinessService.findAllBusiness();
        return new ResponseEntity<>(businesses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<Business> retrieveBusinessById(@PathVariable("id") Long id) {
        Optional<Business> business = IBusinessService.findBusinessById(id);
        if (business.isEmpty()) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(business.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<Business> deleteBusiness(@PathVariable("id") Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        boolean deleteBusiness = IBusinessService.deleteBusiness(id);
        if (!deleteBusiness) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @PostMapping
    protected ResponseEntity<Business> saveEntity(@RequestBody Business business) {
        if (business == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        boolean savedBusiness = IBusinessService.saveBusiness(business);
        if (!savedBusiness) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    @PutMapping("{id}")
    protected ResponseEntity<Business> updateBusiness(@PathVariable("id") Long id, @RequestBody Business business) {
        if (business == null || id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        business.setId(id);

        boolean savedBusiness = IBusinessService.saveBusiness(business);
        if (savedBusiness) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(business, HttpStatus.OK);
    }


    @Autowired
    public void setIBusinessService(IBusinessService IBusinessService) {
        this.IBusinessService = IBusinessService;
    }

}
