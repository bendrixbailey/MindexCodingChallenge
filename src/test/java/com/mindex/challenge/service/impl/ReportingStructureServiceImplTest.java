package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportWithIdUrl;

    @Autowired
    private ReportingStructureService reportingService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        reportWithIdUrl = "http://localhost:" + port + "/reportingStructure/{id}";

    }

    /**
     * The class below tests the creation, getters,
     * and updaters of the ReportingStructure Object.
     * It also tests the .toJson, .toString, and .equals 
     * methods as well.
     */
    @Test
    public void testCreateReadUpdate(){
        Employee emp = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");

        ReportingStructure actualReport = new ReportingStructure(emp, 4);
        ReportingStructure builtReport = restTemplate.getForEntity(reportWithIdUrl, ReportingStructure.class, emp.getEmployeeId()).getBody();

        //check to verify the rest api request gives us the right report
        assertEmployeeEquivalence(actualReport.getEmployee(), builtReport.getEmployee());
        assertEquals(actualReport.getNumberOfReports(), builtReport.getNumberOfReports());


        actualReport.updateNumberOfReports(5);
        assertEquals(actualReport.getNumberOfReports(), 5);
    }

    /**
     * Reused helper class from EmployeeServiceImplTest.java
     * @param expected expected employee
     * @param actual actual employee
     */
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
    
}
