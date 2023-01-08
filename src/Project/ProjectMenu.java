package Project;

import Department.Department;
import Personnel.Personnel;

import java.util.Map;
import java.util.Scanner;

import static Department.DepartmentManagement.getDepartmentManagement;
import static Project.ProjectManagement.getProjectManagement;
import static Personnel.PersonnelManagement.getPersonnelManagement;

public class ProjectMenu {
    Scanner input = new Scanner(System.in);
    Map<Integer, Department> listDepart = getDepartmentManagement().getListDepartment();

    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();

    Map<Integer, Project> listPro = getProjectManagement().getListProject();

    public static void showMenuProject() {
        System.out.println(
                "*******************************************************" + "\n" +
                        "*                         MENU                        *" + "\n" +
                        "*   1. Add new project.                               *" + "\n" +
                        "*   2. Remove project.                                *" + "\n" +
                        "*   3. Change project.                                *" + "\n" +
                        "*   4. Search information project.                    *" + "\n" +
                        "*   5. Display all project.                           *" + "\n" +
                        "*   6. Display member of project.                     *" + "\n" +
                        "*   7. Update members each project.                   *" + "\n" +
                        "*   8. Return main Menu.                              *" + "\n" +
                        "*******************************************************" + "\n");
    }

    public void loadMenuProject(){
        int choice = -1;
        while (choice != 8) {
            showMenuProject();
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    removeProject();
                    break;
                case 3:
                    fixProject();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    getProjectManagement().display();
                    break;
                case 6:
                    displayDepartmentOfProject();
                    break;
                case 7:
                    updateProject();
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
    public String inputDescription() {
        System.out.print("Enter Description: ");
        String description = input.nextLine();
        return description;
    }
    public String inputStartTime() {
        System.out.print("Enter Start Time: ");
        String timeStart = input.nextLine();
        return timeStart;
    }

    public String inputProject() {
        System.out.print("Enter Project: ");
        String project = input.nextLine();
        return project;
    }

    public int chooseId(){
        System.out.print("Choose department responsive: ");
        getDepartmentManagement().display();
        int id = input.nextInt();
        input.nextLine();
        return id;
    }

    public void addProject() {
        System.out.println("Please enter information new member");
        int id = listPro.size()+1;
        String name = inputName();
        String description = inputDescription();
        String startTime = inputStartTime();

        int idDepart = chooseId();
        Project obj = new Project(id,name,description,startTime,idDepart);
        getProjectManagement().add(id,obj);
    }

    public boolean removeProject() {
        int id = inputID();
        System.out.println("Are you sure? (Y/N)");
        String sure = input.nextLine().trim().toLowerCase();

        if (sure.equals("y")) {
            getProjectManagement().remove(id);
            return true;
        }
        return false;
    }

    public void search() {
        int choice = -1;
        while (choice != 0) {
            System.out.println(
                    "What's information you want to search?" + "\n" +
                            "1. Id" + "\n" +
                            "2. Name" + "\n" +
                            "0. Cancel");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    int id = inputID();
                    System.out.println(getProjectManagement().searchById(id));
                    break;
                case 2:
                    String name = inputName();
                    getProjectManagement().searchByName(name);
                    break;
                default: loadMenuProject();
            }
        }
    }

    public void fixProject() {
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

    public void displayDepartmentOfProject(){
        getProjectManagement().display();
        int idProject = inputID();
        System.out.println(listPro.get(idProject).toString());
    }

    public void updateProject(){
        for (Map.Entry<Integer, Department> depart : listDepart.entrySet()) {
            Integer keyDepart = depart.getKey();
            Department valueDepart = depart.getValue();
            valueDepart.getProjectContain().clear();

            for (Map.Entry<Integer, Project> project : listPro.entrySet()) {
                Project valueProject = project.getValue();
                if (valueProject.getIdDepartmentResponsible() == valueDepart.getId()) {
                    valueDepart.getProjectContain().add(keyDepart);
                }
            }
        }
    }
}
