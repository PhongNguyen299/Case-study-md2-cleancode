package Department;

import Personnel.Personnel;
import Project.Project;

import java.util.*;

public class Department {
    private int id;
    private String name;
    private int amount;

    private String function;

    private List <Project> projectContain;
    private List <Personnel> memberDepartment;

    Department(){};

    Department(int id,String name){
        this.id = id;
        this.name = name;
    }
    Department(int id,String name, int amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.projectContain = new LinkedList<>();
        this.memberDepartment = new LinkedList<>();
    }

    Scanner input = new Scanner(System.in);

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addProjectContain(Project project) {
        this.projectContain.add(project);
    }

    public List<Project> getProjectContain() {
        return projectContain;
    }

    public List<Personnel> getMemberDepartment() {
        return memberDepartment;
    }

    public void setMemberDepartment(List<Personnel> memberDepartment) {
        this.memberDepartment = memberDepartment;
    }
}
