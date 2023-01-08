package Project;
import Project.Project;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class ProjectManagement {
    private volatile static ProjectManagement projectManagement;

    private Map<Integer,Project> listProject;
    ProjectManagement (){
        listProject = new HashMap<Integer, Project>();
        listProject.put(1,new Project(1,"autoFix","auto find and fix bug","1/1/2023"));
        listProject.put(2,new Project(2,"skyCar","creat Car can fly","9/9/2023"));
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

    public Map<Integer,Project> getListProject() {
        return listProject;
    }

    public void add(int id, Project project){
        if (project != null){
            listProject.put(id, project);
        }
    }

    public void remove(int id){
        listProject.remove(id);
    }

    public Project searchById(int id) {
        return listProject.get(id);
    }

    public StringBuilder searchByName(String name){
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Project> entry : listProject.entrySet()) {
            Integer key = entry.getKey();
            Project value = entry.getValue();

            if (name.equals(value.getName())) {
                text.append(listProject.get(key).toString());
            }
        }
        return text;
    }


    public void fixName(Project obj, String name){
        obj.setName(name);
    }

    public void displayById(int id){
        this.searchById(id).toString();
    }

    public void display() {
        StringBuilder text = new StringBuilder("");
        for (Map.Entry<Integer, Project> entry : listProject.entrySet()) {
            Integer key = entry.getKey();
            Project value = entry.getValue();
            text.append("|| Id: " + key + "  || Name:  " + value.getName() + "\n" );
        }
        System.out.println(text);
    }

}
