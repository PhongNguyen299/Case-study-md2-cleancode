package Department;

import Department.Department;

import java.util.*;


public final class DepartmentManagement {
    private static volatile DepartmentManagement departmentManagement;
    private Map <Integer,Department> listDepartment;



    private DepartmentManagement(){
        listDepartment = new HashMap<Integer, Department>();
        listDepartment.put(1,new Department("Marketing"));
        listDepartment.put(2,new Department("Accounting"));
        listDepartment.put(3,new Department("Finance"));
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
    }

    public void remove(int id){
            listDepartment.remove(id);
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


    public void fixName(Department obj, String name){
        obj.setName(name);
    }

    public void displayById(int id){
        this.searchById(id).toString();
    }

    public void display() {
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Department> entry : listDepartment.entrySet()) {
            Integer key = entry.getKey();
            Department value = entry.getValue();
            text.append( key + "  || " + value.getName() + " amount of department's member: "
                    + listDepartment.get(key).getMemberDepartment().size()+"\n" );
        }
        System.out.println(text);
    }


}
