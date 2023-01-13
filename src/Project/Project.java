package Project;

public class Project {
    int id;
    private String name;

    private String description;

    private String process;

    private String startTime;

    private String finishTime;
    private int idDepartmentResponsible;


    public Project(){}

    public Project(int id, String name,String description,String startTime){
        this.id = id;
        this.name = name;
        this.description = description;
        this.process = "doing";
        this.startTime = startTime;
        this.finishTime = "Not yet";
    }

    public Project(int id, String name,String description,String startTime,int idDepartmentResponsible){
        this.id = id;
        this.name = name;
        this.description = description;
        this.process = "doing";
        this.startTime = startTime;
        this.finishTime = "Not yet";
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

    public int getIdDepartmentResponsible() {
        return idDepartmentResponsible;
    }

    public void setIdDepartmentResponsible(int idDepartmentResponsible) {
        this.idDepartmentResponsible = idDepartmentResponsible;
    }

    public String toFile(){
        return id + "," + name + "," + description  + "," + startTime + ","  + idDepartmentResponsible;
    }

    @Override
    public String toString() {
        return  "|| id: " + id + "\n"+
                "|| name: " + name + '\n' +
                "|| description: " + description + '\n' +
                "|| process: " + process + '\n' +
                "|| startTime: " + startTime + '\n' +
                "|| finishTime: " + finishTime + '\n' +
                "|| idDepartmentResponsible: " + idDepartmentResponsible;
    }
}
