package Department;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public final class DepartmentManagement {
    private static volatile DepartmentManagement departmentManagement;
    private List<Department> listDepartment;

    private Scanner input = new Scanner(System.in);

    private DepartmentManagement(){
        listDepartment = new LinkedList<>();
        listDepartment.add( new Department(1,"Marketing",10));
        listDepartment.add( new Department(2,"Accounting",7));
        listDepartment.add( new Department(3,"Finance",9));
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

    public List<Department> getListDepartment() {
        return listDepartment;
    }
}
