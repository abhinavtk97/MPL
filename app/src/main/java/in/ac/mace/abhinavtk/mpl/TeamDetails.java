package in.ac.mace.abhinavtk.mpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

public class TeamDetails extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String id=null;

    List<String> players= new ArrayList<>();
    List<String> allroles=new ArrayList<>();
    List<TextView> roles = new ArrayList<>();
    List<TextView> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        String team = getIntent().getStringExtra("team");
        TextView teamname = findViewById(R.id.teamnamerole);
        teamname.setText(team);
        ImageView imageView = findViewById(R.id.teamimg);
        int t1=0;
        switch (team){
            case "Club De Dinkan":t1=R.drawable.dink; break;
            case "Bellaries FC" : t1=R.drawable.bell; break;
            case "Real Manavalan FC":t1=R.drawable.manav; break;
            case "Ponjikkara FC": t1=R.drawable.ponji; break;
            case "FC Marakkar":t1=R.drawable.mara; break;
            case "Chekuthans FC":t1=R.drawable.che;break;
            case "Dashamoolam FC":t1=R.drawable.dasha;break;
            case "Karakkambi FC":t1=R.drawable.kara;break;
        }


        switch (team){
            case "Club De Dinkan":id="dink"; break;
            case "Bellaries FC" : id="bell"; break;
            case "Real Manavalan FC":id="mana"; break;
            case "Ponjikkara FC": id="ponj"; break;
            case "FC Marakkar":id="mara"; break;
            case "Chekuthans FC":id="chek";break;
            case "Dashamoolam FC":id="dash";break;
            case "Karakkambi FC":id="kara";break;
        }

        imageView.setImageResource(t1);
        TextView role1 = findViewById(R.id.role1);
        TextView role2 = findViewById(R.id.role2);
        TextView role3 = findViewById(R.id.role3);
        TextView role4 = findViewById(R.id.role4);
        TextView role5 = findViewById(R.id.role5);
        TextView role6 = findViewById(R.id.role6);
        TextView role7 = findViewById(R.id.role7);


        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roles.add(role4);
        roles.add(role5);
        roles.add(role6);
        roles.add(role7);


        TextView name1 = findViewById(R.id.name1);
        TextView name2 = findViewById(R.id.name2);
        TextView name3 = findViewById(R.id.name3);
        TextView name4 = findViewById(R.id.name4);
        TextView name5 = findViewById(R.id.name5);
        TextView name6 = findViewById(R.id.name6);
        TextView name7 = findViewById(R.id.name7);


        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);
        names.add(name6);
        names.add(name7);


        getPlayers();


    }

    private void getPlayers(){
        db.collection("Players").document(id).collection(id)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.e("Firebase","getid failed");
                            return;
                        }
                        List<DocumentSnapshot> docs =  queryDocumentSnapshots.getDocuments();
                        for(int i=0;i<docs.size();i++){
                            players.add(docs.get(i).get("name").toString());
                            allroles.add(docs.get(i).get("role").toString());
                            Log.e("doing",String.valueOf(docs.size()));
                        }
                        if(players!=null && allroles!=null){
                            for(int i=0;i<allroles.size();i++){
                                roles.get(i).setText(allroles.get(i));
                            }
                            for (int i=0;i<players.size();i++){
                                names.get(i).setText(players.get(i));
                            }
                        }else{
                            for(int i=0;i<allroles.size();i++){
                                roles.get(i).setText("");
                            }
                            for (int i=0;i<players.size();i++){
                                names.get(i).setText("");
                            }
                        }
                    }
                });
    }
}
