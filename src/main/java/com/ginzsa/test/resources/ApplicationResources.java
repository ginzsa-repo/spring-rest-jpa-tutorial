package com.ginzsa.test.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by santiago.ginzburg on 2/2/16.
 */
@RestController
public class ApplicationResources {

    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity(HttpStatus.OK);
    }
}