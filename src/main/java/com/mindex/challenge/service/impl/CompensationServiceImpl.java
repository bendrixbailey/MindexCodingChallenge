package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class is the implementation of the CompensationService
 * It provides implementations of the read and create methods
 * required by the REST endpoint controller.
 */
@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private CompensationRepository compRepository;

    /**
     * This function implements the interface function to create a compensation
     * object in the database. It takes the given compensation object, grabs
     * the full employee data from the employee service, and adds it to the comp object,
     * and then adds it to the repository, and then returns the compensation object.
     * 
     * @param compensation compensation object passed into it
     * @return returns new comp object that has been added to the repo.
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation data for employee [{}]", compensation.getEmployee());

        Employee tempEmp = employeeService.read(compensation.getEmployee().getEmployeeId()); //grab actual employee from database
        if(tempEmp == null){
            LOG.error("No employee exists in database that matches employee in compensation data");
            compRepository.insert(compensation);    //still insert if no emp exists, might be created in future
            return compensation;
        }
        compensation.updateEmployee(tempEmp);       //update employee with someone with correct data
        compRepository.insert(compensation);

        return compensation;
    }

    /**
     * This function implements the interface function to read a compensation
     * object (search for and return) given an employee ID. It simply uses
     * the findByEmployeeId function to return the compensation object.
     * 
     * @param employeeId id to search comp repository for
     * @return return found compensation object.
     */
    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading compensation data for employee [{}]", employeeId);

        Employee tempEmp = employeeService.read(employeeId);

        Compensation compensation = compRepository.findByEmployee(tempEmp);

        if (compensation == null){
            LOG.error("No compensation object exists for given employee id: [{}]", employeeId);
        }

        return compensation;
    }
    
}
