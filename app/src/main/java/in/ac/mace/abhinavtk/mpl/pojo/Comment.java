package in.ac.mace.abhinavtk.mpl.pojo;


public class Comment {
    private Integer commentPersonPictureRes;
    private String commentPersonName;
    private String commentText;
    private String commentDate;
    private int teamno;

    public Comment(Integer commentPersonPictureRes, String commentText, String commentPersonName, String commentDate,int teamno) {
        this.commentPersonPictureRes = commentPersonPictureRes;
        this.commentPersonName = commentPersonName;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.teamno = teamno;
    }

    public Integer getCommentPersonPictureRes() {
        return commentPersonPictureRes;
    }

    public void setCommentPersonPictureRes(Integer commentPersonPictureRes) {
        this.commentPersonPictureRes = commentPersonPictureRes;
    }

    public String getCommentPersonName() {
        return commentPersonName;
    }

    public void setCommentPersonName(String commentPersonName) {
        this.commentPersonName = commentPersonName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getTeamno() {
        return teamno;
    }

    public void setTeamno(int teamno) {
        this.teamno = teamno;
    }
}
