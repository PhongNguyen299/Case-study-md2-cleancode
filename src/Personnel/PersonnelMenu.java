package Personnel;


import Department.Department;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Personnel.PersonnelManagement.*;
import static Department.DepartmentManagement.*;


public class PersonnelMenu {
    Scanner input = new Scanner(System.in);
    Map<Integer, Department> listDepart = getDepartmentManagement().getListDepartment();
    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();

    public static void showMenuPersonnel() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Add new personnel.                             *" + "\n" +
                        "*   2. Remove personnel.                              *" + "\n" +
                        "*   3. Change personnel.                              *" + "\n" +
                        "*   4. Search information personnel.                  *" + "\n" +
                        "*   5. Display all personnel.                         *" + "\n" +
                        "*   6. Display salary.                                *" + "\n" +
                        "*   7. Return main Menu.                              *" + "\n" +
                        "*******************************************************" + "\n");
    }


    public void loadMenuPersonnel(){
        int choice = -1;
        while (choice != 7) {
            showMenuPersonnel();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    addPer();
                    break;
                case 2:
                    removePer();
                    break;
                case 3:
                    fixPer();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    getPersonnelManagement().display();
                    break;
                case 6:
                    displaySalary();
                    break;
                case 7:
                    break;
            }
        }
    }
    public int inputID() {
        System.out.print("Enter Id: ");
        int id = input.nextInt();
        input.nextLine();
        return id;
    }

    public String inputName() {
        System.out.print("Enter name: ");
        String name = input.nextLine();
        return name;
    }

    public String inputPosition() {
        System.out.print("Enter position: ");
        String position = input.nextLine();
        return position;
    }

    public String inputDepartment() {
        System.out.print("Enter Department: ");
        String department = input.nextLine();
        return department;
    }

    public String inputGender() {
        System.out.print("Enter gender: ");
        String gender = input.nextLine();
        return gender;
    }

    public void addPer() {
        System.out.println("Please enter information new member");
        int id = listPerson.size()+1;

        String name = inputName();
        String gender = inputGender();
        String position = inputPosition();
        String department = inputDepartment();
        Personnel obj = new Personnel(id,name, gender, position, department);

        getPersonnelManagement().add(id, obj);
    }

    public boolean removePer() {
        int id = inputID();
        System.out.println("Are you sure? (Y/N)");
        String sure = input.nextLine().trim().toLowerCase();

        if (sure.equals("y")) {
            getPersonnelManagement().remove(id);
            return true;
        }
        return false;
    }

    public void search() {
        int choice = -1;
        while (choice != 0) {
            System.out.println(
                    "what's information you want to search?" + "\n" +
                            "1. Id" + "\n" +
                            "2. Name" + "\n" +
                            "3. Gender" + "\n" +
                            "4. Department" + "\n" +
                            "0. Cancel");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    int id = inputID();
                    System.out.println(getPersonnelManagement().searchById(id));
                    break;
                case 2:
                    String name = inputName();
                    getPersonnelManagement().searchByName(name);
                    break;
                case 3:
                    String gender = inputGender();
                    getPersonnelManagement().searchByGender(gender);
                    break;
                case 4:
                    String department = inputDepartment();
                    getPersonnelManagement().searchByDepartment(department);
                    break;
            }
        }
    }

    public void fixPer() {
        int id = inputID();
        System.out.println(listPerson.get(id).getName());

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Fix name: ");
            System.out.println("2. Fix position: ");
            System.out.println("3. Fix department: ");
            System.out.println("0. Cancel: ");

            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    String name = inputName();
                    listPerson.get(id).setName(name);
                    break;
                case 2:
                    String position = inputPosition();
                    listPerson.get(id).setPosition(position);
                    break;
                case 3:
                    String department = inputDepartment();
                    listPerson.get(id).setBelongDepartment(department);
                    break;
            }
        }
    }


    public void displaySalary() {
        String text = "";
        for (Map.Entry<Integer, Personnel> entry : listPerson.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();
            String positionS = value.getPosition().toLowerCase();
            switch (positionS) {
                case "boss":
                    final double BOSS_SALARY = 30.000;
                    value.setSalary(BOSS_SALARY - value.getAttendance().size() * 1.000);
                    text += key + " " + value.getName() + " || Position: " + value.getPosition()
                            + " || salary : " + value.getSalary() + " per/month" + ".\n";
                    break;
                case "manager":
                    final double MANAGER_SALARY = 24.000;
                    value.setSalary(MANAGER_SALARY - value.getAttendance().size() * 1.000);
                    text += key + " " + value.getName() + " || Position: " + value.getPosition()
                            + " || salary : " + value.getSalary() + " per/month" + ".\n";
                    break;
                default:
                    final double STAFF_SALARY = 15.000;
                    value.setSalary(STAFF_SALARY - value.getAttendance().size() * 1.000);
                    text += key + " " + value.getName() + " || Position: " + value.getPosition()
                            + " || Salary : " + value.getSalary() + " per/month" + ".\n";
                    break;
            }
        }
        System.out.println(text);
    }
}
