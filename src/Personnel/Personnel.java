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
    private List<String> attendance;
    private boolean isStatus;

    private Personnel(){};

    public Personnel(int id,String name, String gender, String position,String belongDepartment){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.position = position;
        this.belongDepartment = belongDepartment;
        this.attendance = new LinkedList<>();
        this.isStatus = true;
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

    public List<String> getAttendance() {
        return attendance;
    }

    public void checkin(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        String dateformat = df.format(new Date());
        this.attendance.add(dateformat);
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        this.isStatus = status;
    }
    public String toFile(){
        return id + "," + name + "," + gender  + "," + position + ","  + belongDepartment+ ","+ isStatus;
    }



    @Override
    public String toString() {
        return  "|| ID: " + id +
                " || Name: " + name  +
                " || Gender: " + gender  +
                " || Position: " + position +
                " || Department: " + belongDepartment  +
                " || Status: "+ isStatus +"\n";
    }
}
