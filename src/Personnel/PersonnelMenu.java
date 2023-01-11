package Personnel;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import static Personnel.PersonnelManagement.*;

public class PersonnelMenu {
    Scanner input = new Scanner(System.in);
    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();

    public static void showMenuPersonnel() {
        System.out.println(
                """
                        *******************************************************
                        *                         MENU                        *
                        *   1. Add new personnel.                             *
                        *   2. Remove personnel.                              *
                        *   3. Change personnel.                              *
                        *   4. Search information personnel.                  *
                        *   5. Display all personnel.                         *
                        *   6. Display salary.                                *
                        *   7. Return main Menu.                              *
                        *******************************************************
                        """);
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
        return input.nextLine();
    }

    public String inputPosition() {
        System.out.print("Enter position: ");
        return input.nextLine();
    }

    public String inputDepartment() {
        System.out.print("Enter Department: ");
        return input.nextLine();
    }

    public String inputGender() {
        System.out.print("Enter gender: ");
        return input.nextLine();
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

    public void removePer() {

        int id = inputID();
        System.out.println("Are you sure? (Y/N)");
        String sure = input.nextLine().trim().toLowerCase();

        if (sure.equals("y")) {
            getPersonnelManagement().remove(id);
        } else {
            System.out.println("Remove fail");
        }
    }

    public void search() {
        int choice = -1;
        while (choice != 0) {
            System.out.println(
                    """
                            What's information you want to search?
                            1. Id.
                            2. Name.
                            3. Gender.
                            4. Department.
                            0. Cancel.""");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> {
                    int id = inputID();
                    System.out.println(getPersonnelManagement().searchById(id));
                }
                case 2 -> {
                    String name = inputName();
                    getPersonnelManagement().searchByName(name);
                }
                case 3 -> {
                    String gender = inputGender();
                    getPersonnelManagement().searchByGender(gender);
                }
                case 4 -> {
                    String department = inputDepartment();
                    getPersonnelManagement().searchByDepartment(department);
                }
            }
        }
    }

    public void fixPer() {
        int id = inputID();
        Personnel person = listPerson.get(id);
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
                case 1 -> {
                    String name = inputName();
                    getPersonnelManagement().fixName(person,name);
                }
                case 2 -> {
                    String position = inputPosition();
                    getPersonnelManagement().fixPosition(person,position);
                }
                case 3 -> {
                    String department = inputDepartment();
                    getPersonnelManagement().fixDepartment(person,department);

                }
            }
        }
    }

    public void showAttendance(int id){
        for (Date date: listPerson.get(id).getAttendance()) {
            System.out.println(date + "\n") ;
        }
    }

    public void displaySalary() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Integer, Personnel> entry : listPerson.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();
            String positionS = value.getPosition().toLowerCase();

            switch (positionS) {
                case "boss" -> {
                    final double BOSS_SALARY = 30.000;
                    value.setSalary(BOSS_SALARY - value.getAttendance().size() * 1.000);
                    text.append(key).append(" ").append(value.getName()).append(" || Position: ").append(value.getPosition()).append(" || salary : ").append(value.getSalary()).append(" per/month").append(".\n");
                }
                case "manager" -> {
                    final double MANAGER_SALARY = 24.000;
                    value.setSalary(MANAGER_SALARY - value.getAttendance().size() * 1.000);
                    text.append(key).append(" ").append(value.getName()).append(" || Position: ").append(value.getPosition()).append(" || salary : ").append(value.getSalary()).append(" per/month").append(".\n");
                }
                default -> {
                    final double STAFF_SALARY = 15.000;
                    value.setSalary(STAFF_SALARY - value.getAttendance().size() * 1.000);
                    text.append(key).append(" ").append(value.getName()).append(" || Position: ").append(value.getPosition()).append(" || salary : ").append(value.getSalary()).append(" per/month").append(".\n");
                }
            }
        }
        System.out.println(text);
    }
}
