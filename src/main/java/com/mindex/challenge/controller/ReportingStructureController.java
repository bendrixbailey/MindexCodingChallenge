package com.mindex.challenge.controller;

import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.ReportingStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Rest API controller for the reporting structure endpoint
 */
@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService reportingService;

    //this is the route and parameter required for the get request.
    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure read(@PathVariable String id){
        LOG.debug("Recieved reporting structure create request for id [{}]", id);

        return reportingService.read(id);
    }

}
