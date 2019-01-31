package in.ac.mace.abhinavtk.mpl.pojo;

import com.ramotion.expandingcollection.ECCardData;

import java.util.List;

public class CardData implements ECCardData<Comment> {

    private String team1name;
    private String team2name;
    private Integer headBackgroundResource;
    private Integer mainBackgroundResource;
    private Integer goal1;
    private Integer goal2;
    private Integer personPictureResource;
    private Integer team1PictureResource;
    private Integer team2PictureResource;
    private String time;
    private String team1Goal;
    private String team2Goal;
    private List<Comment> listItems;

    public CardData() {

    }

    public CardData(String team1name, String team2name, Integer headBackgroundResource, Integer mainBackgroundResource, Integer goal1, Integer goal2, Integer personPictureResource, Integer team1PictureResource, Integer team2PictureResource, String time, String team1Goal, String team2Goal, List<Comment> listItems) {
        this.team1name = team1name;
        this.team2name = team2name;
        this.headBackgroundResource = headBackgroundResource;
        this.mainBackgroundResource = mainBackgroundResource;
        this.goal1 = goal1;
        this.goal2 = goal2;
        this.personPictureResource = personPictureResource;
        this.team1PictureResource = team1PictureResource;
        this.team2PictureResource = team2PictureResource;
        this.time = time;
        this.team1Goal = team1Goal;
        this.team2Goal = team2Goal;
        this.listItems = listItems;
    }

    public String getTeam1name() {
        return team1name;
    }

    public void setTeam1name(String team1name) {
        this.team1name = team1name;
    }

    public Integer getHeadBackgroundResource() {
        return headBackgroundResource;
    }

    public void setHeadBackgroundResource(Integer headBackgroundResource) {
        this.headBackgroundResource = headBackgroundResource;
    }

    public Integer getMainBackgroundResource() {
        return mainBackgroundResource;
    }

    public void setMainBackgroundResource(Integer mainBackgroundResource) {
        this.mainBackgroundResource = mainBackgroundResource;
    }

    public Integer getPersonPictureResource() {
        return personPictureResource;
    }

    public void setPersonPictureResource(Integer personPictureResource) {
        this.personPictureResource = personPictureResource;
    }

    public String getTeam1Goal() {
        return team1Goal;
    }

    public void setTeam1Goal(String team1Goal) {
        this.team1Goal = team1Goal;
    }

    public String getTeam2Goal() {
        return team2Goal;
    }

    public void setTeam2Goal(String team2Goal) {
        this.team2Goal = team2Goal;
    }



    @Override
    public List<Comment> getListItems() {
        return listItems;
    }

    public void setListItems(List<Comment> listItems) {
        this.listItems = listItems;
    }

    public Integer getTeam1PictureResource() {
        return team1PictureResource;
    }

    public void setTeam1PictureResource(Integer team1PictureResource) {
        this.team1PictureResource = team1PictureResource;
    }

    public Integer getTeam2PictureResource() {
        return team2PictureResource;
    }

    public void setTeam2PictureResource(Integer team2PictureResource) {
        this.team2PictureResource = team2PictureResource;
    }

    public String getTeam2name() {
        return team2name;
    }

    public void setTeam2name(String team2name) {
        this.team2name = team2name;
    }

    public Integer getGoal1() {
        return goal1;
    }

    public void setGoal1(Integer goal1) {
        this.goal1 = goal1;
    }

    public Integer getGoal2() {
        return goal2;
    }

    public void setGoal2(Integer goal2) {
        this.goal2 = goal2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}