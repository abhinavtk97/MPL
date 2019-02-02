package in.ac.mace.abhinavtk.mpl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import in.ac.mace.abhinavtk.mpl.pojo.Match;
import in.ac.mace.abhinavtk.mpl.pojo.MatchData;

public class MatchDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchreference;
    private String docid;
    TextView team1goal;
    TextView team2goal;
    TextView datetime;
    TextView team1stat;
    TextView team2stat;
    ProgressBar live;
    ImageView team1logo;
    ImageView team2logo;
    TextView team1name;
    TextView team2name;
    MatchDataAdapter adapter;
    String itemdata,teamname;
    List<String> teamss;

    int size=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Bundle extras = getIntent().getExtras();
        docid = extras.getString("snapshot");
        final String t1 = extras.getString("team1");
        final String t2 = extras.getString("team2");
        team1goal = findViewById(R.id.team1goaldetails);
        team2goal = findViewById(R.id.team2goaldetails);
        datetime = findViewById(R.id.datetimedetails);
        team1stat = findViewById(R.id.team1statdetails);
        team2stat = findViewById(R.id.team2statdetails);
        live = findViewById(R.id.livedetails);
        team1logo = findViewById(R.id.team1logodetails);
        team2logo = findViewById(R.id.team2logodetails);
        team1name = findViewById(R.id.team1namedetails);
        team2name = findViewById(R.id.team2namedetails);
        //documentSnapshot = (DocumentSnapshot) extras.getSerializable("snapshot");
        Log.d("firebase",docid);
        getMatchData();
        matchreference= db.collection("Matches").document(docid).collection("matchdata");
        setUpRecyclerView();


        final EditText editText = findViewById(R.id.addmatch);
        final EditText desc = findViewById(R.id.adddescmatch);
        Button send = findViewById(R.id.sendbutton);
        Spinner spinner = findViewById(R.id.matchaddspinner);
        Spinner teamspinner = findViewById(R.id.teamspinner);
        List<String> categories = new ArrayList<>();
        categories.add("Commentery");
        categories.add("Goal");
        categories.add("Yellow");
        categories.add("Red");
        itemdata="Commentery";
        teamss = new ArrayList<>();
        teamss.add(t1);
        teamss.add(t2);
        teamname=t1;
        ArrayAdapter<String> dataadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);
        ArrayAdapter<String> data1adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,teamss);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        data1adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataadapter);
        teamspinner.setAdapter(data1adapter);
        spinner.setOnItemSelectedListener(this);
        teamspinner.setOnItemSelectedListener(this);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = String.valueOf(editText.getText());
                final Map<String,Object> tosend = new HashMap<>();
                tosend.put("description",message);
                tosend.put("heading",itemdata);
                tosend.put("message",String.valueOf(desc.getText()));
                tosend.put("team",teamname);
                tosend.put("timestamp",Timestamp.now());
                new AlertDialog.Builder(MatchDetails.this).setTitle("Confirm")
                        .setMessage("Are you sure to send this message")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("Matches").document(docid).collection("matchdata").add(tosend);
                                if(itemdata.equals("Goal")){
                                    if(t2.equals(teamname)){
                                        db.collection("Matches").document(docid).update("team2goal",(Integer.parseInt(team2goal.getText().toString())+1));
                                        db.collection("Matches").document(docid).update("team2stat",team2stat.getText().toString()+"\n"+message);
                                    }else {
                                        db.collection("Matches").document(docid).update("team1goal",(Integer.parseInt(team1goal.getText().toString())+1));
                                        db.collection("Matches").document(docid).update("team1stat",team1stat.getText().toString()+"\n"+message);

                                    }
                                }
                            }
                        }).setNegativeButton(android.R.string.no,null).show();

            }
        });

        CardView card = findViewById(R.id.maincardloy);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MatchDetails.this).setTitle("Confirm")
                        .setMessage("Are you sure to end this match")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                new AlertDialog.Builder(MatchDetails.this).setTitle("Orappanallo alle")
                                        .setMessage("An ended match cannot be restarted.. Eni ariyand aayathaan paranj entadth varall")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                db.collection("Matches").document(docid).update("live",false);

                                            }
                                        }).setNegativeButton(android.R.string.no,null).show();
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
        Log.d("firebase","start listen");
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
        Log.d("firebase","stop listen");
    }

    private void setUpRecyclerView(){
        Query query =matchreference.orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<MatchData> options = new FirestoreRecyclerOptions.Builder<MatchData>()
                .setQuery(query,MatchData.class)
                .build();
        adapter = new MatchDataAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("firebase","setting up");
    }

    private void getMatchData(){
        db.collection("Matches").document(docid)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.w("Firebase","Listen failed");
                            return;
                        }

                        Log.d("Firebase","Listenong");
                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                team1goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team1goal").intValue()));
                                team2goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team2goal").intValue()));
                                datetime.setText(queryDocumentSnapshots.getString("datetime"));
                                team1stat.setText(queryDocumentSnapshots.getString("team1stat"));
                                team2stat.setText(queryDocumentSnapshots.getString("team2stat"));
                                if(queryDocumentSnapshots.getBoolean("live")){
                                    live.setVisibility(View.VISIBLE);
                                }else {
                                    live.setVisibility(View.GONE);
                                }
                                int t1=0,t2=0;

                                switch (queryDocumentSnapshots.getString("team1")){
                                    case "Club De Dinkan":t1=R.drawable.dink; break;
                                    case "Bellaries FC" : t1=R.drawable.bell; break;
                                    case "Real Manavalan FC":t1=R.drawable.manav; break;
                                    case "Ponjikkara FC": t1=R.drawable.ponji; break;
                                    case "FC Marakkar":t1=R.drawable.mara; break;
                                    case "Chekuthans FC":t1=R.drawable.che;break;
                                    case "Dashamoolam FC":t1=R.drawable.dasha;break;
                                    case "Karakkambi FC":t1=R.drawable.kara;break;
                                }
                                switch (queryDocumentSnapshots.getString("team2")){
                                    case "Club De Dinkan":t2=R.drawable.dink; break;
                                    case "Bellaries FC" : t2=R.drawable.bell; break;
                                    case "Real Manavalan FC":t2=R.drawable.manav; break;
                                    case "Ponjikkara FC": t2=R.drawable.ponji; break;
                                    case "FC Marakkar":t2=R.drawable.mara; break;
                                    case "Chekuthans FC":t1=R.drawable.che;break;
                                    case "Dashamoolam FC":t1=R.drawable.dasha;break;
                                    case "Karakkambi FC":t1=R.drawable.kara;break;
                                }

                                team1logo.setImageResource(t1);
                                team2logo.setImageResource(t2);
                                team1name.setText(queryDocumentSnapshots.getString("team1").toUpperCase());
                                team2name.setText(queryDocumentSnapshots.getString("team2").toUpperCase());

                            }
                        }
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.teamspinner:
                teamname = parent.getItemAtPosition(position).toString();
                break;
            case R.id.matchaddspinner:
                itemdata = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}