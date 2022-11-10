package Forge;

import java.util.Objects;

public class Employee {
    public String name;
    public String department;
    public int salary;
    public int id;
    public static int idCounter;

    public Employee(String name, String department, int salary) {
        this.name = name.trim();
        this.department = department.trim();
        this.salary = salary;
        this.id = ++idCounter;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDepartment() {
        return this.department;
    }

    public int getSalary() {
        return this.salary;
    }

    public void setSalary(int salary) {
        if (salary < 0) {
            throw new RuntimeException("Значение не может быть отрицательным");
        }
        this.salary = salary;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
