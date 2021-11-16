package com.mindex.challenge.data;

import java.util.Objects;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee(){
        return this.employee;
    }

    public int getNumberOfReports(){
        return this.numberOfReports;
    }

    public void updateEmployee(Employee employee){
        this.employee = employee;
    }

    public void updateNumberOfReports(int newNumberOfReports){
        this.numberOfReports = newNumberOfReports;
    }

    public ReportingStructure searchWithEmployee(Employee employee){
        this.employee = employee;
        return this;
    }

    public ReportingStructure searchWithNumberOfReports(int numberofReports){
        this.numberOfReports = numberofReports;
        return this;
    }

    public String getJson(){
        return "{employee='" + this.employee + "', numberOfReports='" + this.numberOfReports + "'}";
    }

    // Default java object function implementations


    public int hashCode(){
        return Objects.hash(employee, numberOfReports);
    }

    public String toString(){
        return "employee: " + this.employee + ", numberOfReports: " + this.numberOfReports; 
    }

    public boolean equals(Object o){
        if(o instanceof ReportingStructure){
            return true;
        }else{
            return false;
        }
    }

}
