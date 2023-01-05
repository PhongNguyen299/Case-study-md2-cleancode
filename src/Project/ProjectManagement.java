package Project;
import java.util.LinkedList;
import java.util.List;

public class ProjectManagement {
    private volatile static ProjectManagement projectManagement;

    private List<Project> listProject;
    ProjectManagement (){
        listProject = new LinkedList<Project>();
        listProject.add(new Project(1,"autoFix","auto find and fix bug","1/1/2023"));
        listProject.add(new Project(2,"skyCar","creat Car can fly","9/9/2023"));
    };

    public static ProjectManagement getProjectManagement(){
        if(projectManagement == null){
            synchronized (ProjectManagement.class){
                if (projectManagement == null){
                    projectManagement = new ProjectManagement();
                }
            }
        }
        return projectManagement;
    }

    public List<Project> getListProject() {
        return listProject;
    }
}
