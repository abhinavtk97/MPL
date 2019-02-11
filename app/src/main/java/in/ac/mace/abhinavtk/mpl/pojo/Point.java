package in.ac.mace.abhinavtk.mpl.pojo;

public class Point {

    private String team;
    private String M;
    private String W;
    private String L;
    private String D;
    private String plus;
    private int GD;
    private int P;

    public Point(){
        //empty
    }

    public Point(String team, String m, String w, String l, String d, String plus, int GD, int p) {
        this.team = team;
        M = m;
        W = w;
        L = l;
        D = d;
        this.plus = plus;
        this.GD = GD;
        P = p;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W = w;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L = l;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public int getGD() {
        return GD;
    }

    public void setGD(int GD) {
        this.GD = GD;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    public String getTeam() {
        return team;
    }

}
