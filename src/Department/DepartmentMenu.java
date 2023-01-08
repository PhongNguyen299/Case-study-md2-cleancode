package Department;
import java.util.Map;
import java.util.Scanner;
import Department.Department;
import Personnel.Personnel;

import static Department.DepartmentManagement.getDepartmentManagement;
import static Department.DepartmentManagement.getDepartmentManagement;
import static Personnel.PersonnelManagement.getPersonnelManagement;

public class DepartmentMenu {
    Scanner input = new Scanner(System.in);
    Map<Integer, Department> listDepart = getDepartmentManagement().getListDepartment();

    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();


    public static void showMenuDepartment() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Add new department.                            *" + "\n" +
                        "*   2. Remove department.                             *" + "\n" +
                        "*   3. Change department.                             *" + "\n" +
                        "*   4. Search information department.                 *" + "\n" +
                        "*   5. Display all department.                        *" + "\n" +
                        "*   6. Display member of department.                  *" + "\n" +
                        "*   7. Update members each department.                 *" + "\n" +
                        "*   8. Return main Menu.                              *" + "\n" +
                        "*******************************************************" + "\n");
    }

    public void loadMenuDepartment(){
        int choice = -1;
        while (choice != 8) {
            showMenuDepartment();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    removeDepartment();
                    break;
                case 3:
                    fixDepartment();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    getDepartmentManagement().display();
                    break;
                case 6:
                    displayMemberOfDepartment();
                    break;
                case 7:
                    updateDepartmentMember();
                    break;
                default:
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


    public String inputDepartment() {
        System.out.print("Enter Department: ");
        String department = input.nextLine();
        return department;
    }

    public void addDepartment() {
        System.out.println("Please enter information new member");
        int id = listDepart.size()+1;

        String name = inputName();
        String department = inputDepartment();
        Department obj = new Department(name);

        getDepartmentManagement().add(id,obj);
    }

    public boolean removeDepartment() {
        int id = inputID();
        System.out.println("Are you sure? (Y/N)");
        String sure = input.nextLine().trim().toLowerCase();

        if (sure.equals("y")) {
            getDepartmentManagement().remove(id);
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
                            "0. Cancel");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    int id = inputID();
                    System.out.println(getDepartmentManagement().searchById(id));
                    break;
                case 2:
                    String name = inputName();
                    getDepartmentManagement().searchByName(name);
                    break;
            }
        }
    }

    public void fixDepartment() {
        int id = inputID();
        System.out.println(listDepart.get(id).getName());

        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Fix name: ");
            System.out.println("0. Cancel: ");

            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    String name = inputName();
                    listDepart.get(id).setName(name);
                    break;
            }
        }
    }

    public void displayMemberOfDepartment(){
        StringBuilder text = new StringBuilder("");
        getDepartmentManagement().display();
        int idDepart = inputID();
        for (int id: listDepart.get(idDepart).getMemberDepartment()) {
            text.append(listPerson.get(id).toString());
        };
        System.out.println(text);
    }

    public void updateDepartmentMember(){
        for (Map.Entry<Integer, Department> department : listDepart.entrySet()) {
            Department valueDepart = department.getValue();
            valueDepart.getMemberDepartment().clear();

            for (Map.Entry<Integer, Personnel> person : listPerson.entrySet()) {
                Integer keyPer = person.getKey();
                Personnel valuePer = person.getValue();

                if (valuePer.getBelongDepartment() == valueDepart.getName()) {
                    valueDepart.getMemberDepartment().add(keyPer);
                }
            }
        }
    }
}
