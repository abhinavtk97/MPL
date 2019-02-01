package in.ac.mace.abhinavtk.mpl.pojo;

public class StatisticData {

    private String name;
    private int number;

    public StatisticData(){
        //empty
    }

    public StatisticData(String name, int number) {
        this.name = name;
        this.number = number;
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
}
