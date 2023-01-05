package Project;

import Department.Department;

import java.util.LinkedList;
import java.util.List;

public class Project {
    int id;
    String name;

    String description;

    String process;

    String startTime;

    String finishTime;

    List <Department> departmentResponsible = new LinkedList();

    public Project(){}

    public Project(int id, String name,String description,String startTime){
        this.id = id;
        this.name = name;
        this.description = description;
        this.process = "doing";
        this.startTime = startTime;
        this.finishTime = "Not yet";
    }

    public Project(int id, String name,String description,String startTime,Department department){
        this.id = id;
        this.name = name;
        this.description = description;
        this.process = "doing";
        this.startTime = startTime;
        this.finishTime = "Not yet";
        this.departmentResponsible.add(department);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public List<Department> getDepartmentResponsible() {
        return departmentResponsible;
    }

    public void addDepartmentResponsible(Department department){
        this.departmentResponsible.add(department);
    }
}
