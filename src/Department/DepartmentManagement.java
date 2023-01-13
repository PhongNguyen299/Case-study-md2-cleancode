package Department;

import Personnel.Personnel;

import java.io.*;
import java.util.*;

import static Personnel.PersonnelManagement.getPersonnelManagement;


public final class DepartmentManagement {
    private static volatile DepartmentManagement departmentManagement;
    private Map <Integer,Department> listDepartment;
    Map<Integer, Personnel> listPerson = getPersonnelManagement().getListPersonnel();


    private DepartmentManagement(){
        listDepartment = new HashMap<Integer, Department>();
//        listDepartment.put(1, new Department(1,"Marketing"));
//        listDepartment.put(2,new Department(2,"Accounting"));
//        listDepartment.put(3,new Department(3,"Finance"));
        read();
        updateAmount();
    };

    public static DepartmentManagement getDepartmentManagement(){
        if(departmentManagement == null){
            synchronized (DepartmentManagement.class){
                if (departmentManagement == null){
                    departmentManagement = new DepartmentManagement();
                }
            }
        }
        return departmentManagement;
    }

    public Map<Integer,Department> getListDepartment() {
        return listDepartment;
    }

    public void add(int id, Department department){
        if (department != null){
            listDepartment.put(id, department);
        }
        write();
    }

    public void remove(int id){
        listDepartment.remove(id);
        write();
    }

    public Department searchById(int id) {
        return listDepartment.get(id);
    }

    public StringBuilder searchByName(String name){
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Department> entry : listDepartment.entrySet()) {
            Integer key = entry.getKey();
            Department value = entry.getValue();

            if (name.equals(value.getName())) {
                text.append(listDepartment.get(key).toString());
            }
        }
        return text;
    }


    public void fixName(int id, String name){
        listDepartment.get(id).setName(name);
        write();
    }

    public void displayById(int id){
        this.searchById(id).toString();
    }

    public void display() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Integer, Department> entry : listDepartment.entrySet()) {
            Integer key = entry.getKey();
            Department value = entry.getValue();
            text.append(key).append("  || ").append(value.getName()).append(" amount of department's member: ").append(listDepartment.get(key).getAmount()).append("\n");
        }
        System.out.println(text);
    }

    public void updateAmount(){
        for (Department department : listDepartment.values()) {
            int count = 0;
            for (Personnel person: listPerson.values()){
                if (department.getName().equals(person.getBelongDepartment()) && person.isStatus()){
                    count++;
                }
                department.setAmount(count);
            }
        }
    }

    public void read(){
        listDepartment.clear();
        try {
            File inFile = new File("src/FileText/Department.txt");
            FileReader fileReader = new FileReader(inFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                Department department = handleLine(line.trim());
                add(department.getId(), department);
            }
            reader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(){
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/FileText/Department.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Map.Entry<Integer, Department> entry : listDepartment.entrySet()) {
                Department value = entry.getValue();
                bufferedWriter.write(value.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Department handleLine(String line){
        String[] strings = line.split(",");
        return new Department(Integer.parseInt(strings[0]),strings[1]);
    }
}
