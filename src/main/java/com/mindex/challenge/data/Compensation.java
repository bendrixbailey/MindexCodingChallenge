package com.mindex.challenge.data;

import java.util.Objects;

/**
 * This class represents a compensation data type.
 * The description and requirements were quite open ended, so its assumed
 * that the effective date is always passed as a string to get around any
 * date/time formatting issues/assumptions.
 * 
 * Contains methods and constructors to build datatype, update information,
 * and get information. Also includes implementations of java object methods.
 */
public class Compensation {
    private Employee employee;
    private float salary = 0.00f;
    private String effectiveDate;


    //Constructor for when every field is known
    public Compensation(Employee employee, float salary, String effectiveDate){
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    //constructor in case only the employee is known
    public Compensation(){
    }

    //getter for employee of this object
    public Employee getEmployee(){
        return this.employee;
    }

    //getter for salary field
    public float getSalary(){
        return this.salary;
    }

    //getter for effectivedate field
    public String getEffectiveDate(){
        return this.effectiveDate;
    }

    //updates employee with new employee
    public void updateEmployee(Employee employee){
        this.employee = employee;
    }

    //updates salary with new salary
    public void updateSalary(float salary){
        this.salary = salary;
    }

    //updates effective date with new date
    public void updateEffectiveDate(String date){
        this.effectiveDate = date;
    }

    //returns json format of this object
    public String getJson(){
        return "{employee : '" + this.employee 
            + "', salary : '" + this.salary 
            + "', effectiveDate : '" + this.effectiveDate + "'}";
    }

    //=====================Default java object function implementations.==================

    //overrides hashcode function to use employee, salary, and effectivedate
    @Override
    public int hashCode(){
        return Objects.hash(employee, salary, effectiveDate);
    }

    //overrides tostring to print out major values of this object
    @Override
    public String toString(){
        return "employee=" + this.employee.getEmployeeId() 
            + " salary=" + this.salary 
            + " effectiveDate=" + this.effectiveDate ;
    }

    //overrides equals to compare 3 attributes of this object
    @Override
    public boolean equals(Object o){
        if(o instanceof Compensation){
            if(o == this){
                return true;
            }else{
                Compensation tempComp = (Compensation) o;
                return (tempComp.getEmployee().getEmployeeId() == this.employee.getEmployeeId()) 
                    && (tempComp.getSalary() == this.salary) 
                    && (tempComp.getEffectiveDate() == this.effectiveDate);
            }
        }else{
            return false;
        }
    }
}
