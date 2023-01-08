import Department.Department;
import Department.DepartmentMenu;
import Personnel.Personnel;
import Personnel.Personnel.*;
import Personnel.PersonnelManagement;
import Personnel.PersonnelMenu;
import Project.Project;
import Project.ProjectMenu;

import java.util.Map;
import java.util.Scanner;

import static Department.DepartmentManagement.getDepartmentManagement;
import static Personnel.PersonnelManagement.getPersonnelManagement;
import static Project.ProjectManagement.getProjectManagement;

public class Main {
    Scanner input = new Scanner(System.in);
    Map<Integer, Department> listDepart = getDepartmentManagement().getListDepartment();

    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();

    Map<Integer, Project> listPro = getProjectManagement().getListProject();

    public static void main(String[] args) {
//        new DepartmentMenu().loadMenuDepartment();
//        new PersonnelMenu().loadMenuPersonnel();
//        new ProjectMenu().loadMenuProject();
//        mainMenu();
        new Main().login();
    }

    public static void mainMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Personnel.                                     *" + "\n" +
                        "*   2. Department.                                    *" + "\n" +
                        "*   3. Project.                                       *" + "\n" +
                        "*******************************************************" + "\n");
    }
    public static void managerMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Department.                                    *" + "\n" +
                        "*   2. Project.                                       *" + "\n" +
                        "*   0. Exit                                           *" + "\n" +
                        "*******************************************************" + "\n");
    }
    public static void staffMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Personnel.                                     *" + "\n" +
                        "*   2. Department.                                    *" + "\n" +
                        "*   3. Project.                                       *" + "\n" +
                        "*******************************************************" + "\n");
    }

    public void loadBossMenu(int id){
        int choice = -1;
        while (choice != 4) {
            mainMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 2:
                    new PersonnelMenu().loadMenuPersonnel();
                    break;
                case 1:
                    new DepartmentMenu().loadMenuDepartment();
                    break;
                case 3:
                    new ProjectMenu().loadMenuProject();
                    break;
                default:
                    break;
            }
        }
    }

    public void loadManagerMenu(int id){
        int choice = -1;
        while (choice != 4) {
            managerMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    new DepartmentMenu().loadMenuDepartment();
                    break;
                case 2:
                    new ProjectMenu().loadMenuProject();
                    break;
                case 3:
                    checkInDaily(id);
                    break;
                default:
                    break;
            }
        }
    }

    public void loadStaffMenu(int id){
        int choice = -1;
        while (choice != 4) {
            staffMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    new PersonnelMenu().loadMenuPersonnel();
                    break;
                case 2:
                    checkInDaily(id);
                    break;
                default:
                    break;
            }
        }
    }

    public void login(){
        System.out.println("Enter user name");
        String name = input.nextLine().toLowerCase();
        System.out.print("Enter your password (ID): ");
        int id = input.nextInt();
        input.nextLine();

        String position = listPerson.get(id).getPosition().toLowerCase();
        String nameId = listPerson.get(id).getName().toLowerCase();
        if (position.equals("boss") && nameId.equals(name)){
            loadBossMenu(id);
        } else if(position.equals("manager") && nameId.equals(name)){
            loadManagerMenu(id);
        } else if (nameId.equals(name)){
            loadStaffMenu(id);
        }
    }

    public void checkInDaily(int id) {
        System.out.println("Please input 'HAHA' to check-in");
        String text = input.nextLine().toLowerCase();
        while (!text.equals("haha")) {
            System.out.println("Please input again");
        }
        System.out.println("You is checked");
        listPerson.get(id).checkin();
        input.nextLine();
    }
}