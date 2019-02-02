package in.ac.mace.abhinavtk.mpl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

import in.ac.mace.abhinavtk.mpl.pojo.Match;
import in.ac.mace.abhinavtk.mpl.pojo.MatchData;

public class MatchDetails extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        Bundle extras = getIntent().getExtras();
        docid = extras.getString("snapshot");
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
        Query query =matchreference.limit(100);
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
                                    case "Ponjikkara": t1=R.drawable.ponji; break;
                                    case "FC Marakkar":t1=R.drawable.mara; break;
                                    case "Chekuthans FC":t1=R.drawable.che;break;
                                    case "Dashamoolam FC":t1=R.drawable.dasha;break;
                                    case "Karakkambi FC":t1=R.drawable.kara;break;
                                }
                                switch (queryDocumentSnapshots.getString("team2")){
                                    case "Club De Dinkan":t2=R.drawable.dink; break;
                                    case "Bellaries FC" : t2=R.drawable.bell; break;
                                    case "Real Manavalan FC":t2=R.drawable.manav; break;
                                    case "Ponjikkara": t2=R.drawable.ponji; break;
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
}
