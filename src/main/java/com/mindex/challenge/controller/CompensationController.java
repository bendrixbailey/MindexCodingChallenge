package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class serves as a REST endpoint controller. It defines
 * what endpoints exist to cover the compensation dataflow.
 */
@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired 
    private CompensationService compensationService;

    /**
     * Endpoint to search and return a compensation object for the given
     * employee ID.
     * 
     * @param id id of employee to search compensation objects for
     * @return compensation object belonging to employee id
     */
    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id){
        LOG.debug("Recieved compensation read request for employee id [{}]", id);
        return compensationService.read(id);
    }

    /**
     * Description of the creation is abstract, so Im assuming its similar to employee
     * creation, and that a compensation object will be passed to this endpoint.
     * 
     * If the endpoint required a json payload of just employee id, salary, and effectiveDate,
     * that was not specified.
     * 
     * @param compensation
     * @return
     */
    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation){
        LOG.debug("Recieved request to create new Compensation object [{}]", compensation);

        return compensationService.create(compensation);
    }
    
}
