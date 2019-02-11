package in.ac.mace.abhinavtk.mpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import in.ac.mace.abhinavtk.mpl.pojo.Point;

public class PointsTable extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchreference = db.collection("Points");
    private PointAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_table);
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        Query query =matchreference.orderBy("P",Query.Direction.DESCENDING).orderBy("GD",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Point> options = new FirestoreRecyclerOptions.Builder<Point>()
                .setQuery(query,Point.class)
                .build();
        adapter = new PointAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.pointrecycler);
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
