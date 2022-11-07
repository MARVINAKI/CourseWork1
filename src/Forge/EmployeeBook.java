package Forge;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class EmployeeBook {
    private Employee[] employees;
    private static int size;


    public EmployeeBook() {
        this.employees = new Employee[10];
    }

    public void addEmployee(String name, String department, int salary) {
        if (size > employees.length) {
            System.out.println("Вакансий нет");
            return;
        }
        employees[size++] = new Employee(name, department, salary);
    }

    public void dismissEmployee(String name) {
        for (int c = 0; c < size; c++) {
            if (employees[c].getName().equals(name.trim())) {
                System.arraycopy(employees, c + 1, employees, c, size - c - 1);
                employees[size - 1] = null;
                size--;
            }
        }
    }


    public void printSizeArray() {
        System.out.println(size);
    }

    public void printArraysLength() {
        System.out.println(employees.length);
    }

    public void printArray() {
        for (Employee employee : employees) {
            if (employee == null) {
                continue;
            }
            System.out.println(employee);
        }
    }

    public void printCostsInMonth() {
        System.out.println(calculateCostsInMonth());
    }

    public void printEmployeeMinSalary() {
        findEmployeeMinSalary();
    }

    public void printEmployeeMaxSalary() {
        findEmployeeMaxSalary();
    }

    public void printAverageSalary() {
        System.out.println(calculateAverageSalary());
    }

    public void printAllEmployeeName() {
        for (Employee employee : employees) {
            if (employee == null) continue;
            System.out.println(employee.getName());
        }
    }

    public void printSeparator() {
        System.out.println("---------------------");
    }

    /*public void printDepInfo(String department) {
        System.out.print("Сотрудник с минимальной ЗП: ");
        System.out.println(findDepMinSalaryEmployee(department));
        System.out.print("Сотрудник с максимальной ЗП: ");
        System.out.println(findDepMaxSalaryEmployee(department));
        System.out.print("Затраты на ЗП в месяц: ");
        System.out.println(findDepCostsInMonth(department));
        System.out.print("Средняя ЗП по отделу: ");
        System.out.println(findDepAverageSalary(department));
    }*/

    public void actionOnDep(String department, int percent) {
        indexingDepSalary(department, percent);
        System.out.println("Проиндексируем ЗП на " + percent + "% и выведем обновленные данные по отделу " + department);
        printInfoOutOf(department);
        System.out.print("Сотрудник с минимальной ЗП: ");
        System.out.println(findDepMinSalaryEmployee(department));
        System.out.print("Сотрудник с максимальной ЗП: ");
        System.out.println(findDepMaxSalaryEmployee(department));
        System.out.print("Затраты на ЗП в месяц: ");
        System.out.println(findDepCostsInMonth(department));
        System.out.print("Средняя ЗП по отделу: ");
        System.out.println(findDepAverageSalary(department));
    }

    public void findEmpSalaryLower(int value) {
        for (Employee employee : employees) {
            if (employee==null) continue;
            if (employee.getSalary() < value) {
                System.out.println(employee.getName());
            }
        }
    }

    public void findEmpSalaryUp(int value) {
        for (Employee employee : employees) {
            if (employee==null) continue;
            if (employee.getSalary() >= value) {
                System.out.println(employee.getName());
            }
        }
    }




    /* Методы поиска информации по отделу */

    private void printInfoOutOf(String department) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment())) {
                System.out.print(employee.getId()+ " " + employee.getName() + " " + employee.getSalary() + "\n");
            }
        }
    }

    private void indexingDepSalary(String department, int percent) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment())) {
                employee.setSalary(employee.getSalary() + (employee.getSalary() / 100) * percent);
            }
        }
    }

    private int findDepAverageSalary(String department) {
        int count = 0;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment())) {
                count++;
            }
        }
        return findDepCostsInMonth(department) / count;
    }

    private int findDepCostsInMonth(String department) {
        int costs = 0;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment())) {
                costs += employee.getSalary();
            }
        }
        return costs;
    }

    private String findDepMaxSalaryEmployee(String department) {
        String employeeMinSalary = null;
        int minSalary = -1;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment()) && minSalary == -1) {
                minSalary = employee.getSalary();
                employeeMinSalary = employee.getName();
            }
            if (department.equals(employee.getDepartment()) && minSalary < employee.getSalary()) {
                minSalary = employee.getSalary();
                employeeMinSalary = employee.getName();
            }
        }
        return employeeMinSalary;
    }

    private String findDepMinSalaryEmployee(String department) {
        String employeeMinSalary = null;
        int minSalary = -1;
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (department.equals(employee.getDepartment()) && minSalary == -1) {
                minSalary = employee.getSalary();
                employeeMinSalary = employee.getName();
            }
            if (department.equals(employee.getDepartment()) && minSalary > employee.getSalary()) {
                minSalary = employee.getSalary();
                employeeMinSalary = employee.getName();
            }
        }
        return employeeMinSalary;
    }

    public void indexingSalaryTotal(int percent) {
        for (Employee employee : employees) {
            if (employee == null) continue;
            employee.setSalary(employee.getSalary() + (employee.getSalary() / 100) * percent);
        }
    }

    private int calculateAverageSalary() {
        return calculateCostsInMonth() / size;
    }

    private void findEmployeeMaxSalary() {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getSalary() == findMaxSalary()) {

                System.out.println(employee.getName());
            }
        }
    }

    private int findMaxSalary() {
        int minSalary = employees[0] == null ? 0 : employees[0].getSalary();
        for (Employee employee : employees) {
            if (employee == null) continue;
            minSalary = employee.getSalary() > minSalary ? employee.getSalary() : minSalary;
        }
        return minSalary;
    }

    private void findEmployeeMinSalary() {
        for (Employee employee : employees) {
            if (employee == null) continue;
            if (employee.getSalary() == findMinSalary()) {

                System.out.println(employee.getName());
            }
        }
    }

    private int findMinSalary() {
        int minSalary = employees[0] == null ? 0 : employees[0].getSalary();
        for (Employee employee : employees) {
            if (employee == null) continue;
            minSalary = employee.getSalary() < minSalary ? employee.getSalary() : minSalary;
        }
        return minSalary;
    }

    private int calculateCostsInMonth() {
        int sumCosts = 0;
        for (int c = 0; c < size; c++) {
            sumCosts += employees[c].getSalary();
        }
        return sumCosts;
    }


    @Override
    public String toString() {
        for (int c = 0; c < size; c++) {
            System.out.println("id: " + employees[c].getId() + " || " +
                    "ФИО: " + employees[c].getName() + " || " +
                    "Отдел: " + employees[c].getDepartment() + " || " +
                    "ЗП: " + employees[c].getSalary());
        }
        return "\n";
    }

    /*@Override
    public String toString() {
        return "EmployeeBook{" +
                "employees=" + Arrays.toString(employees) +
                '}';
    }*/
}
