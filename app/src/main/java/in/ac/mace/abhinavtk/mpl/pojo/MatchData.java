package in.ac.mace.abhinavtk.mpl.pojo;

public class MatchData {

    private String team1;
    private String team2;
    private String team1Message;
    private String team2Message;
    private String description;

    public MatchData(){
        //empty
    }

    public MatchData(String team1, String team2, String team1Message, String team2Message, String description) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Message = team1Message;
        this.team2Message = team2Message;
        this.description = description;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1Message() {
        return team1Message;
    }

    public void setTeam1Message(String team1Message) {
        this.team1Message = team1Message;
    }

    public String getTeam2Message() {
        return team2Message;
    }

    public void setTeam2Message(String team2Message) {
        this.team2Message = team2Message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
