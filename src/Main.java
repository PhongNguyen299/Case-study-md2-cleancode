import Department.DepartmentMenu;
import Personnel.Personnel;
import Personnel.PersonnelMenu;
import Project.ProjectMenu;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import static Personnel.PersonnelManagement.getPersonnelManagement;

public class Main {
    Scanner input = new Scanner(System.in);
    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();

    public static void main(String[] args) throws IOException {
        new Main().login();
    }

    public static void mainMenu() {
        System.out.println(
                """
                        *******************************************************
                        *                         MENU                        *
                        *   1. Personnel.                                     *
                        *   2. Department.                                    *
                        *   3. Project.                                       *
                        *   0. Return login.                                  *
                        *******************************************************
                        """);
    }
    public static void managerMenu() {
        System.out.println(
                """
                        *******************************************************
                        *                         MENU                        *
                        *   1. Department.                                    *
                        *   2. Project.                                       *
                        *   3. Check in                                       *
                        *   0. Return login.                                  *
                        *******************************************************
                        """);
    }
    public static void staffMenu() {
        System.out.println(
                """
                        *******************************************************
                        *                         MENU                        *
                        *   1. Check in                                       *
                        *   2. Show attendance                                *
                        *   3. Edit information                               *
                        *   0. Return login.                                  *
                        *******************************************************
                        """);
    }

    public void loadBossMenu(){
        int choice = -1;
        while (choice != 4) {
            mainMenu();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> new PersonnelMenu().loadMenuPersonnel();
                case 2 -> new DepartmentMenu().loadMenuDepartment();
                case 3 -> new ProjectMenu().loadMenuProject();
                case 0 -> login();
                default -> choice = -4;
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
                case 1 -> new DepartmentMenu().loadMenuDepartment();
                case 2 -> new ProjectMenu().loadMenuProject();
                case 3 -> checkInDaily(id);
                case 0 -> login();
                default -> choice = -4;
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
                case 1 -> checkInDaily(id);
                case 2 -> new PersonnelMenu().showAttendance(id);
                case 3 -> new PersonnelMenu().fixPer();
                case 0 -> login();
                default -> choice = 4;
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
        if (position.equals("boss") && nameId.equals(name) && status){
            loadBossMenu();
        } else if(position.equals("manager") && nameId.equals(name) && status){
            loadManagerMenu(id);
        } else if (nameId.equals(name) && status){
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