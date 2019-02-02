package in.ac.mace.abhinavtk.mpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamDetails extends AppCompatActivity {

    String[] dinkroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] dinknames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] bellroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] bellnames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] manavroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] manavnames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] ponjiroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] ponjinames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] mararoles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] maranames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] cheroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] chenames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] dashroles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] dashnames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};

    String[] kararoles = {"FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW","FW"};
    String[] karanames = {"Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi","Abhi",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        String team = getIntent().getStringExtra("team");
        TextView teamname = findViewById(R.id.teamnamerole);
        teamname.setText(team);
        TextView role1 = findViewById(R.id.role1);
        TextView role2 = findViewById(R.id.role2);
        TextView role3 = findViewById(R.id.role3);
        TextView role4 = findViewById(R.id.role4);
        TextView role5 = findViewById(R.id.role5);
        TextView role6 = findViewById(R.id.role6);
        TextView role7 = findViewById(R.id.role7);
        TextView role8 = findViewById(R.id.role8);
        TextView role9 = findViewById(R.id.role9);
        TextView role10 = findViewById(R.id.role10);
        TextView role11 = findViewById(R.id.role11);
        TextView role12 = findViewById(R.id.role12);

        List<TextView> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roles.add(role4);
        roles.add(role5);
        roles.add(role6);
        roles.add(role7);
        roles.add(role8);
        roles.add(role9);
        roles.add(role10);
        roles.add(role11);
        roles.add(role12);

        TextView name1 = findViewById(R.id.name1);
        TextView name2 = findViewById(R.id.name2);
        TextView name3 = findViewById(R.id.name3);
        TextView name4 = findViewById(R.id.name4);
        TextView name5 = findViewById(R.id.name5);
        TextView name6 = findViewById(R.id.name6);
        TextView name7 = findViewById(R.id.name7);
        TextView name8 = findViewById(R.id.name8);
        TextView name9 = findViewById(R.id.name9);
        TextView name10 = findViewById(R.id.name10);
        TextView name11 = findViewById(R.id.name11);
        TextView name12 = findViewById(R.id.name12);

        List<TextView> names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);
        names.add(name8);
        names.add(name9);
        names.add(name10);
        names.add(name11);
        names.add(name12);

        String teamroles[]=null, teamnames[]=null;
        switch (team){
            case "Club De Dinkan":
                teamroles=dinkroles;
                teamnames=dinknames;
                break;
            case "Bellaries FC":
                teamroles=bellroles;
                teamnames=bellnames;
                break;
            case "Real Manavalan FC":
                teamroles=manavroles;
                teamnames=manavnames;
                break;
            case "Ponjikkara FC":
                teamroles=ponjiroles;
                teamnames=ponjinames;
                break;
            case "FC Marakkar":
                teamroles=manavroles;
                teamnames=manavnames;
                break;
            case "Chekuthans FC":
                teamroles=cheroles;
                teamnames=chenames;
                break;
            case "Dashamoolam FC":
                teamroles=dashroles;
                teamnames=dashnames;
                break;
            case "Karakkambi FC":
                teamroles=kararoles;
                teamnames=karanames;
                break;

        }
        if(teamnames!=null && teamroles!=null){
            for(int i=0;i<roles.size();i++){
                roles.get(i).setText(teamroles[i]);
            }
            for (int i=0;i<names.size();i++){
                names.get(i).setText(teamnames[i]);
            }
        }else{
            for(int i=0;i<roles.size();i++){
                roles.get(i).setText("");
            }
            for (int i=0;i<names.size();i++){
                names.get(i).setText("");
            }
        }

    }
}
