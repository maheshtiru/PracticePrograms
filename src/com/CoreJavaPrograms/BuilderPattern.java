package com.CoreJavaPrograms;

public class BuilderPattern {
    public static void main(String[] args) {
        Employee emp = new Employee.EmployeeBuilder("Mahesh", "Tiru")
                .email("mahesh@gmail.com")
                .phoneNumber("123")
                //.address("abc")
                .build();
        System.out.println(emp);
    }
}

class Employee {
    private String firstName;
    private String lastName;
    private String phoneNumber; //optional
    private String email;       //optional
    private String address;     //optional

    private Employee(EmployeeBuilder employeeBuilder){
        this.firstName   = employeeBuilder.firstName;
        this.lastName    = employeeBuilder.lastName;
        this.phoneNumber = employeeBuilder.phoneNumber;
        this.email       = employeeBuilder.phoneNumber;
        this.address     = employeeBuilder.address;
    }

    // only getters to provide immutability
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Builder static class
    public static class EmployeeBuilder{
        private final String firstName;
        private final String lastName;
        private String phoneNumber; //optional
        private String email;       //optional
        private String address;     //optional

        public EmployeeBuilder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public EmployeeBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;     // return object
        }

        public EmployeeBuilder email(String email) {
            this.email = email;
            return this;     // return object
        }

        public EmployeeBuilder address(String address) {
            this.address = address;
            return this;     // return object
        }

        //main builder method
        public Employee build() {
            return new Employee(this);
        }
    }
}