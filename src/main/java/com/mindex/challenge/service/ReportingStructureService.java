package com.mindex.challenge.service;
import com.mindex.challenge.data.ReportingStructure;

//interface for ReportingStructureService. Requires one read function.
public interface ReportingStructureService {
    ReportingStructure read(String employeeId);
}
