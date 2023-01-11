package Project;
import Personnel.Personnel;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public final class ProjectManagement {
    private volatile static ProjectManagement projectManagement;

    private Map<Integer,Project> listProject;
    ProjectManagement (){
        listProject = new HashMap<Integer, Project>();
        listProject.put(1,new Project(1,"autoFix","auto find and fix bug","1/1/2023",1));
        listProject.put(2,new Project(2,"skyCar","creat Car can fly","9/9/2023",3));
        listProject.put(3,new Project(3,"moto","new moto","9/9/2023",2));
        listProject.put(4,new Project(4,"plane","new plane","9/9/2023",3));
        listProject.put(5,new Project(5,"keyboard","new keyboard","9/9/2023",1));
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

    public void searchByName(String name){
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Integer, Project> entry : listProject.entrySet()) {
            Integer key = entry.getKey();
            Project value = entry.getValue();

            if (name.equals(value.getName())) {
                text.append(listProject.get(key).toString());
            }
        }
        System.out.println(text);
    }


    public void fixName(Project obj, String name){
        obj.setName(name);
    }

    public void displayById(int id){
        this.searchById(id).toString();
    }

    public void read(){
        listProject.clear();
        try {
            File inFile = new File("src/FileText/Personnel.txt");
            FileReader fileReader = new FileReader(inFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                Project project = handleLine(line.trim());
                add(project.getId(), project);
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
            fileWriter = new FileWriter("src/FileText/Personnel.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Map.Entry<Integer, Project> entry : listProject.entrySet()) {
                Project value = entry.getValue();

                bufferedWriter.write(value.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Project handleLine(String line){
        String[] strings = line.split(",");
        return new Project(Integer.parseInt(strings[0]),strings[1],strings[2],strings[3]);
    }

    public void display() {
        StringBuilder text = new StringBuilder();
        for (Map.Entry<Integer, Project> entry : listProject.entrySet()) {
            Integer key = entry.getKey();
            Project value = entry.getValue();
            text.append("|| Id: ").append(key).append("  || Name:  ").append(value.getName()).append("\n");
        }
        System.out.println(text);
    }
}
