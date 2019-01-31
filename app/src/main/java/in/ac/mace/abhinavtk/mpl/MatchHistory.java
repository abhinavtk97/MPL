package in.ac.mace.abhinavtk.mpl;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import in.ac.mace.abhinavtk.mpl.firebase.MatchRepository;
import in.ac.mace.abhinavtk.mpl.pojo.Match;

public class MatchHistory extends Activity {

    private MatchRepository matchRepository;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchreference = db.collection("Matches");
    private MatchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match_history);
        matchRepository = new MatchRepository(FirebaseFirestore.getInstance());
        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        Query query =matchreference.limit(100);
        FirestoreRecyclerOptions<Match> options = new FirestoreRecyclerOptions.Builder<Match>()
                .setQuery(query,Match.class)
                .build();
        adapter = new MatchAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("firebase","setup");
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

}
