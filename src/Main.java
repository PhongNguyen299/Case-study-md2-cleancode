import Department.Department;
import Department.DepartmentMenu;
import Personnel.Personnel;

import Personnel.PersonnelMenu;
import Project.Project;
import Project.ProjectMenu;

import java.io.IOException;
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

    public static void main(String[] args) throws IOException {
//        new DepartmentMenu().loadMenuDepartment();
//        new PersonnelMenu().loadMenuPersonnel();
//        new ProjectMenu().loadMenuProject();
//        mainMenu();
//        getPersonnelManagement().write();
//        getPersonnelManagement().read();
//        getDepartmentManagement().write();
//        getDepartmentManagement().read();
        new Main().login();
    }

    public static void mainMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Personnel.                                     *" + "\n" +
                        "*   2. Department.                                    *" + "\n" +
                        "*   3. Project.                                       *" + "\n" +
                        "*   0. Return login.                                  *" + "\n" +
                        "*******************************************************" + "\n");
    }
    public static void managerMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Department.                                    *" + "\n" +
                        "*   2. Project.                                       *" + "\n" +
                        "*   3. Check in                                       *" + "\n" +
                        "*   0. Return login.                                  *" + "\n" +
                        "*******************************************************" + "\n");
    }
    public static void staffMenu() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Check in                                       *" + "\n" +
                        "*   2. Edit information                               *" + "\n" +
                        "*   0. Return login.                                  *" + "\n" +
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
                case 1:
                    new PersonnelMenu().loadMenuPersonnel();
                    break;
                case 2:
                    new DepartmentMenu().loadMenuDepartment();
                    break;
                case 3:
                    new ProjectMenu().loadMenuProject();
                    break;
                case 0:
                    login();
                    break;
                default:
                    choice = -4;
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
                case 0:
                    login();
                    break;
                default:
                    choice = -4;
                    break;
            }
        }
    }

    public void loadStaffMenu(int id){
        int choice = -1;
        while (choice != 2) {
            staffMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    checkInDaily(id);
                    break;
                case 2:
                    new PersonnelMenu().fixPer();
                case 0:
                    login();
                    break;
                default:
                    choice = -2;
                    break;
            }
        }
    }

    public void login(){
        System.out.print("Enter user name: ");
        String name = input.nextLine().toLowerCase();
        System.out.print("Enter your password (ID): ");
        int id = input.nextInt();
        input.nextLine();

        String position = listPerson.get(id).getPosition().toLowerCase();
        String nameId = listPerson.get(id).getName().toLowerCase();
        boolean status = listPerson.get(id).isStatus();
        if (position.equals("boss") && nameId.equals(name) && status == true){
            loadBossMenu(id);
        } else if(position.equals("manager") && nameId.equals(name) && status == true){
            loadManagerMenu(id);
        } else if (nameId.equals(name) && status == true){
            loadStaffMenu(id);
        } else {
            System.out.println("Wrong information login or Your id is not exist, Please try again!!");
            login();
        }
    }

    public void checkInDaily(int id) {
        System.out.println("Please input 'HAHA' to check-in");
        String text = input.nextLine().toLowerCase();
        while (!text.equals("haha")) {
            System.out.println("Please input again, Enter 'HAHA' to check in");
            text = input.nextLine().toLowerCase();
        }
        System.out.println("You is checked");
        listPerson.get(id).checkin();
    }
}