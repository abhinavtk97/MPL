package in.ac.mace.abhinavtk.mpl;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import in.ac.mace.abhinavtk.mpl.pojo.MatchData;
import in.ac.mace.abhinavtk.mpl.pojo.StatisticData;

public class Statistics extends AppCompatActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference goalref = db.collection("Statistics")
            .document("wGa0zcyyLbo8qf5XzHXL")
            .collection("TopGoals");
    private CollectionReference assistref = db.collection("Statistics")
            .document("wGa0zcyyLbo8qf5XzHXL")
            .collection("MostAssists");
    private CollectionReference redref = db.collection("Statistics")
            .document("wGa0zcyyLbo8qf5XzHXL")
            .collection("RedCards");
    private CollectionReference yellowref = db.collection("Statistics")
            .document("wGa0zcyyLbo8qf5XzHXL")
            .collection("YellowCards");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        goalref.limit(4).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<StatisticData> statisticData = new ArrayList<>();
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        StatisticData statisticData1 = documentSnapshot.toObject(StatisticData.class);
                        statisticData.add(statisticData1);
                    }
                    ListView goalListview = findViewById(R.id.goallistview);
                    StatisticsAdapter statisticsAdapter = new StatisticsAdapter(Statistics.this,statisticData);
                    goalListview.setAdapter(statisticsAdapter);
                    ProgressBar progressBar = findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.GONE);
                }else {
                    Log.d("Statistics","Error getting docs",task.getException());
                }
            }
        });

        assistref.limit(4).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<StatisticData> statisticData = new ArrayList<>();
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        StatisticData statisticData1 = documentSnapshot.toObject(StatisticData.class);
                        statisticData.add(statisticData1);
                    }
                    ListView assistListView = findViewById(R.id.assistlistview);
                    StatisticsAdapter statisticsAdapter = new StatisticsAdapter(Statistics.this,statisticData);
                    assistListView.setAdapter(statisticsAdapter);
                    ProgressBar progressBar = findViewById(R.id.progressBar2);
                    progressBar.setVisibility(View.GONE);
                }else {
                    Log.d("Statistics","Error getting docs",task.getException());
                }
            }
        });

        redref.limit(4).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<StatisticData> statisticData = new ArrayList<>();
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        StatisticData statisticData1 = documentSnapshot.toObject(StatisticData.class);
                        statisticData.add(statisticData1);
                    }
                    ListView redListView = findViewById(R.id.redlistview);
                    StatisticsAdapter statisticsAdapter = new StatisticsAdapter(Statistics.this,statisticData);
                    redListView.setAdapter(statisticsAdapter);
                    ProgressBar progressBar = findViewById(R.id.progressBar3);
                    progressBar.setVisibility(View.GONE);
                }else {
                    Log.d("Statistics","Error getting docs",task.getException());
                }
            }
        });

        yellowref.limit(4).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<StatisticData> statisticData = new ArrayList<>();
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){
                        StatisticData statisticData1 = documentSnapshot.toObject(StatisticData.class);
                        statisticData.add(statisticData1);
                    }
                    ListView yellowListView = findViewById(R.id.yellowlistview);
                    StatisticsAdapter statisticsAdapter = new StatisticsAdapter(Statistics.this,statisticData);
                    yellowListView.setAdapter(statisticsAdapter);
                    ProgressBar progressBar = findViewById(R.id.progressBar4);
                    progressBar.setVisibility(View.GONE);
                }else {
                    Log.d("Statistics","Error getting docs",task.getException());
                }
            }
        });
    }

}
