package com.crescendo.controller.rest;

import org.springframework.http.ResponseEntity;


public abstract class BaseController<T> {

    abstract ResponseEntity<T> saveEntity(T t);

}