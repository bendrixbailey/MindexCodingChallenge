package com.mindex.challenge.service.impl;

import java.util.List;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Recursive helper function to go through each employees reports
     * and search those lists for other direct reports. Calls
     * itself on each employee in the direct reports list, and only returns
     * once an empty or null list is found.
     * 
     * @param reports list of reports to an employee
     * @return returns length of current list plus call to next iteration
     */
    private int calculateReports(List<Employee> reports){
        int total = 0;
        if(reports == null){                //if reports is null, 0 reports exist
            return 0;
        }else if(reports.size() == 0){      //if reports size is 0, 0 reports exist
            return 0;
        }else{                  //else, add list to total, and for each employee in list, call function again and add to total
            total += reports.size();
            for(Employee emp : reports){
                total += calculateReports(emp.getDirectReports());
            }
            return total;
        }
    }

    /**
     * This function implements the interface function read.
     * It gets the employee object from the employeeService,
     * and then calls the above recursive function to find the
     * count of reports for that employee, given the employee
     * direct reports list.
     * 
     * Returns a ReportingStructure with the proper employee and numberofreports.
     */
    @Override
    public ReportingStructure read(String employeeId) {

        LOG.debug("Creating new Reporting structure from employee [{}]", employeeId);
        
        Employee employee = employeeService.read(employeeId);

        List<Employee> reports = employee.getDirectReports();

        int numberOfReports = 1 + calculateReports(reports);

        return new ReportingStructure(employee, numberOfReports);
    }
}
