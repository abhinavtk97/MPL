package in.ac.mace.abhinavtk.mpl.pojo;

public class StatisticData {

    private String name;
    private int number;
    private String team;

    public StatisticData(){
        //empty
    }

    public StatisticData(String name, int number,String team) {
        this.name = name;
        this.number = number;
        this.team=team;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
