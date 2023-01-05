package Personnel;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Personnel {
    private int id;
    private String name;
    private String gender;
    private String belongDepartment;
    private String position;
    private double salary;
    private List<Date> attendance;

    private Personnel(){};

    public Personnel(int id,String name, String gender, String position,String belongDepartment){
        this.name = name;
        this.gender = gender;
        this.position = position;
        this.belongDepartment = belongDepartment;
        this.attendance = new LinkedList<>();
    }

    public Personnel(String name, String gender, String position,String belongDepartment){
        this.name = name;
        this.gender = gender;
        this.position = position;
        this.belongDepartment = belongDepartment;
        this.attendance = new LinkedList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBelongDepartment() {
        return belongDepartment;
    }

    public void setBelongDepartment(String belongDepartment) {
        this.belongDepartment = belongDepartment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Date> getAttendance() {
        return attendance;
    }

    public void checkin(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        this.attendance.add(new Date());
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", belongDepartment='" + belongDepartment + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
