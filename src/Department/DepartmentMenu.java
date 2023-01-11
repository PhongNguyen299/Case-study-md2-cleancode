package Department;
import java.util.Map;
import java.util.Scanner;
import Personnel.Personnel;
import Project.Project;

import static Department.DepartmentManagement.getDepartmentManagement;
import static Personnel.PersonnelManagement.getPersonnelManagement;
import static Project.ProjectManagement.getProjectManagement;

public class DepartmentMenu {
    Scanner input = new Scanner(System.in);
    Map<Integer, Department> listDepart = getDepartmentManagement().getListDepartment();

    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();
    Map<Integer, Project> listProject = getProjectManagement().getListProject();


    public static void showMenuDepartment() {
        System.out.println(
                """
                        *******************************************************
                        *                         MENU                        *
                        *   1. Add new department.                            *
                        *   2. Remove department.                             *
                        *   3. Change department.                             *
                        *   4. Search information department.                 *
                        *   5. Display all department.                        *
                        *   6. Display members of department.                 *
                        *   7. Display projects of department.                *
                        *   8. Update members each department.                *
                        *   9. Return main Menu.                              *
                        *******************************************************
                        """);
    }

    public void loadMenuDepartment(){
        updateDepartmentMember();
        int choice = -1;
        while (choice != 8) {
            showMenuDepartment();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> addDepartment();
                case 2 -> removeDepartment();
                case 3 -> fixDepartment();
                case 4 -> search();
                case 5 -> {
                    updateDepartmentMember();
                    getDepartmentManagement().display();
                }
                case 6 -> displayMemberOfDepartment();
                case 7 -> displayProjectODepartment();
                case 8 -> updateDepartmentMember();
                default -> {
                }
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


    public String inputDepartment() {
        System.out.print("Enter Department: ");
        return input.nextLine();
    }

    public void addDepartment() {
        System.out.println("Please enter information new department");
        int id = listDepart.size()+1;
        String department = inputDepartment();
        Department obj = new Department(department);

        getDepartmentManagement().add(id,obj);
    }

    public void removeDepartment() {
        int id = inputID();
        System.out.println("Are you sure? (Y/N)");
        String sure = input.nextLine().trim().toLowerCase();

        if (sure.equals("y")) {
            for (int idPer: listDepart.get(id).getMemberDepartment()) {
              getPersonnelManagement().remove(idPer);
            };
            getDepartmentManagement().remove(id);
        }
    }


    public void search() {
        int choice = -1;
        while (choice != 0) {
            System.out.println(
                    """
                            what's information you want to search?
                            1. Id
                            2. Name
                            0. Cancel""");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> {
                    int id = inputID();
                    System.out.println(getDepartmentManagement().searchById(id));
                }
                case 2 -> {
                    String name = inputName();
                    getDepartmentManagement().searchByName(name);
                }
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
        updateDepartmentMember();
        StringBuilder text = new StringBuilder();
        getDepartmentManagement().display();
        int idDepart = inputID();
        for (int id: listDepart.get(idDepart).getMemberDepartment()) {
            text.append(listPerson.get(id).toString());
        };
        System.out.println(text);
    }
    public void displayProjectODepartment(){
        updateProjectContain();
        StringBuilder text = new StringBuilder();
        getDepartmentManagement().display();
        int idDepart = inputID();
        for (int id: listDepart.get(idDepart).getProjectContain()) {
            text.append(listProject.get(id).toString());
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

                if (valuePer.getBelongDepartment().equals(valueDepart.getName())) {
                    valueDepart.getMemberDepartment().add(keyPer);
                }
            }
        }
    }

    public void updateProjectContain(){
        for (Map.Entry<Integer, Department> department : listDepart.entrySet()) {
            Department valueDepart = department.getValue();
            valueDepart.getMemberDepartment().clear();

            for (Map.Entry<Integer, Project> person : listProject.entrySet()) {
                Integer keyPer = person.getKey();
                Project valuePer = person.getValue();

                if (valuePer.getIdDepartmentResponsible() == valueDepart.getId()) {
                    valueDepart.getMemberDepartment().add(keyPer);
                }
            }
        }
    }
}
