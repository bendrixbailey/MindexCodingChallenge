package com.mindex.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This class tests the functionality of the Compensation workflow.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;
    private Employee tempEmp;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    //setup for tests
    @Before
    public void setup(){
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";

        tempEmp = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    /**
     * Test function to verify that the create and get methods both work.
     * Compares each returned object to the object it should look like.
     */
    @Test
    public void testCreateRead(){
        Compensation tempComp = new Compensation(tempEmp, 90543.21f, "2021-11-11");


        Compensation retComp = restTemplate.postForEntity(compensationUrl, 
                                                            tempComp, 
                                                            Compensation.class).getBody();
        assertNotNull(retComp);
        assertEquals(tempComp.toString(), retComp.toString());      //compare tostrings here because its returning a different instance of compensation
                                                                    //because it was updated with employee data

        Compensation readComp = restTemplate.getForEntity(compensationIdUrl, 
                                                            Compensation.class, 
                                                            tempComp.getEmployee().getEmployeeId()).getBody(); 
        assertNotNull(readComp);
        assertEquals(tempComp.toString(), readComp.toString());     //see above explanation for .tostring comparison
    }
}
