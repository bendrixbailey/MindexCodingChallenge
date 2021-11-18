package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

//interface for compensation service, must have create and read methods.
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String employeeId);
    
}
