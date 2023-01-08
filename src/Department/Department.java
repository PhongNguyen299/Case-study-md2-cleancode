package Department;

import Personnel.Personnel;
import Project.Project;

import java.util.*;

public class Department {
    private int id;
    private String name;
    private int amount;

    private String function;

    private List <Integer> projectContain;
    private List <Integer> memberDepartment;

    Department(){};

    Department(String name){
        this.name = name;
        memberDepartment = new ArrayList<>();
        projectContain = new ArrayList<>();
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

    public List<Integer> getProjectContain() {
        return projectContain;
    }

    public List<Integer> getMemberDepartment() {
        return memberDepartment;
    }

    public void setMemberDepartment(List<Integer> memberDepartment) {
        this.memberDepartment = memberDepartment;
    }
}
