package Personnel;

import java.util.*;

import Department.DepartmentManagement;

public final class PersonnelManagement  {
    private static volatile PersonnelManagement personnelManagement;
    private Map<Integer, Personnel> listPersonnel;


    private PersonnelManagement() {
        listPersonnel = new HashMap<>();
        listPersonnel.put(1, new Personnel(1,"Luong", "Male", "Manager", "Finance"));
        listPersonnel.put(2, new Personnel(2,"Hieu", "Male", "StaffWC", "Marketing"));
        listPersonnel.put(3, new Personnel(3,"Phong Xoan", "Male", "Design", "Accounting"));
        listPersonnel.put(4, new Personnel(4,"Hien", "Female", "Thief", "Marketing"));
        listPersonnel.put(5, new Personnel(5,"Vu", "Male", "Staff", "Marketing"));
        listPersonnel.put(6, new Personnel(6,"Tung", "Bisexual", "Dancer", "Accounting"));
        listPersonnel.put(7, new Personnel(7,"Minh", "Male", "Teacher", "Finance"));
        listPersonnel.put(8, new Personnel(8,"Tran", "Female", "QA", "Marketing"));
        listPersonnel.put(9, new Personnel(9,"Si Phong", "Male", "Boss", "Accounting"));
    }

    public static PersonnelManagement getPersonnelManagement() {
        if (personnelManagement == null) {
            synchronized (DepartmentManagement.class) {
                if (personnelManagement == null) {
                    personnelManagement = new PersonnelManagement();
                }
            }
        }
        return personnelManagement;
    }

    public Map<Integer, Personnel> getListPersonnel() {
        return listPersonnel;
    }

    public void add(int id, Personnel personnel){
        if (personnel != null){
            listPersonnel.put(id, personnel);
        }
    }

//    public void remove(int id){
//        Personnel person = searchById(id);
//        if(person != null){
//            listPersonnel.remove(id);
//        };
//    }

    public void remove(int id){
        Personnel person = searchById(id);
        if(person != null){
            listPersonnel.get(id).setStatus(false);
        };
    }

    public Personnel searchById(int id) {
        return listPersonnel.get(id);
    }

    public StringBuilder searchByName(String name){
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Personnel> entry : listPersonnel.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();

            if (name.equals(value.getName())) {
                text.append(listPersonnel.get(key).toString());
            }
        }
        return text;
    }

    public StringBuilder searchByGender(String gender){
        StringBuilder text = new StringBuilder("");

        for (Map.Entry<Integer, Personnel> entry : listPersonnel.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();

            if (gender.equals(value.getGender())) {
                text.append(listPersonnel.get(key).toString());
            }
        }
        return text;
    }

    public StringBuilder searchByDepartment(String department) {
        StringBuilder text = new StringBuilder("");

        for (Map.Entry<Integer, Personnel> entry : listPersonnel.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();

            if (department.equals(value.getBelongDepartment())) {
                text.append(listPersonnel.get(key).toString());
            }
        }
        return text;
    }

    public void fixName(Personnel obj, String name){
        obj.setName(name);
    }

    public void fixPosition(Personnel obj, String position){
        obj.setPosition(position);
    }

    public void fixDepartment(Personnel obj, String department){
        obj.setBelongDepartment(department);
    }

    public void displayPersonnelById(int id){
        this.searchById(id).toString();
    }


    public void display() {
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Personnel> entry : listPersonnel.entrySet()) {
            Integer key = entry.getKey();
            Personnel value = entry.getValue();
            text.append( key + "  || " + value.getName() + "|| Gender: " + value.getGender() + "|| Position: "
                    + value.getPosition() + "|| Department: "
                    + value.getBelongDepartment() + " || status: "+ value.isStatus()+ ".\n");
        }
        System.out.println(text);
    }
}
