package in.ac.mace.abhinavtk.mpl.pojo;

import com.google.firebase.Timestamp;

public class MatchData {

    private String team;
    private String heading;
    private String message;
    private String description;
    private Timestamp timestamp;

    public MatchData(){
        //empty
    }

    public MatchData(String team, String heading, String message, String description,Timestamp timestamp) {
        this.team = team;
        this.heading = heading;
        this.message = message;
        this.description = description;
        this.timestamp = timestamp;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
