package com.mindex.challenge.data;

import java.util.Objects;

/**
 * This class represents the structure of an employee's reporting statistics. A
 * report means someone who reports either directly to the employee, or 
 * an employee who reports to someone who reports to this employee. This can go
 * in a tree for as long as necessary. 
 * 
 * This class provides methods to create this structure, udpate information, and
 * get the json representation of the data within this object.
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    /**
     * Creates a reporting structure object
     * @param employee employee who others report to
     * @param numberOfReports number of employees who directly/indirectly report
     */
    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    //return employee of this reporting structure
    public Employee getEmployee(){
        return this.employee;
    }

    //return number of reports for this reporting structure
    public int getNumberOfReports(){
        return this.numberOfReports;
    }

    //update employee (this does not recalculate numberOfReports)
    public void updateEmployee(Employee employee){
        this.employee = employee;
    }

    //update number of reports for the employee
    public void updateNumberOfReports(int newNumberOfReports){
        this.numberOfReports = newNumberOfReports;
    }

    //updates employee, and returns updated structure
    public ReportingStructure updateEmployeeAndReturn(Employee employee){
        this.employee = employee;
        return this;
    }

    //updates numberofreports and returns updated structure
    public ReportingStructure updateNumberOfReportsAndReturn(int numberofReports){
        this.numberOfReports = numberofReports;
        return this;
    }

    //returns string of object in json compatible format
    public String getJson(){
        return "{employee='" + this.employee + "', numberOfReports='" + this.numberOfReports + "'}";
    }

    //=====================Default java object function implementations.==================

    //overrides hashcode function to use employee and numberOfReports
    @Override
    public int hashCode(){
        return Objects.hash(employee, numberOfReports);
    }

    //overrides toString to print employee and number of reports
    @Override
    public String toString(){
        return "employee: " + this.employee + ", numberOfReports: " + this.numberOfReports; 
    }

    //overrides equals to check object type, and compare employee and number of reports
    @Override
    public boolean equals(Object o){
        if(o instanceof ReportingStructure){
            ReportingStructure instance = (ReportingStructure) o;
            return (instance.employee == this.employee) && (instance.numberOfReports == this.numberOfReports);
        }else{
            return false;
        }
    }

}
