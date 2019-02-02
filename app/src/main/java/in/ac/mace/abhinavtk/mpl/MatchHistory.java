package in.ac.mace.abhinavtk.mpl;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import in.ac.mace.abhinavtk.mpl.pojo.Match;

public class MatchHistory extends AppCompatActivity implements MatchAdapter.OnItemClickListener,AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchreference = db.collection("Matches");
    private MatchAdapter adapter;


    private SwitchDateTimeDialogFragment dateTimeDialogFragment;

    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";
    String time=null ;

    private String team1d="Club De Dinkan",team2d="Club De Dinkan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_match_history);
        setUpRecyclerView();
        Spinner team1 = findViewById(R.id.teamadderspinner1);
        Spinner team2 = findViewById(R.id.teamadderspinner2);

        Button add = findViewById(R.id.addmatchbutton);
        List<String> teams = new ArrayList<>();
        teams.add("Club De Dinkan");
        teams.add("Bellaries FC");
        teams.add("Real Manavalan FC");
        teams.add("Ponjikkara FC");
        teams.add("FC Marakkar");
        teams.add("Chekuthans FC");
        teams.add("Dashamoolam FC");
        teams.add("Karakkambi FC");
        ArrayAdapter<String> dataadapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,teams);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        team1.setAdapter(dataadapter);
        team2.setAdapter(dataadapter);
        team1.setOnItemSelectedListener(this);
        team2.setOnItemSelectedListener(this);

        dateTimeDialogFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if(dateTimeDialogFragment == null){
            dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
            );
        }

        final SimpleDateFormat mydateformat = new SimpleDateFormat("d MMM yyyy HH:mm",java.util.Locale.getDefault());
        dateTimeDialogFragment.set24HoursMode(false);
        dateTimeDialogFragment.setHighlightAMPMSelection(false);
        dateTimeDialogFragment.setMinimumDateTime(new GregorianCalendar(2018,Calendar.FEBRUARY,3).getTime());
        dateTimeDialogFragment.setMaximumDateTime(new GregorianCalendar(2018,Calendar.APRIL,1).getTime());
        try {
            dateTimeDialogFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd",Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {
            e.printStackTrace();
        }
        dateTimeDialogFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            @Override
            public void onNeutralButtonClick(Date date) {

            }

            @Override
            public void onPositiveButtonClick(Date date) {
                time = mydateformat.format(date);
                final Map<String,Object> tosend = new HashMap<>();
                tosend.put("live",false);
                tosend.put("over",false);
                tosend.put("team1",team1d);
                tosend.put("team2",team2d);
                tosend.put("datetime",time);
                tosend.put("team2goal",0);
                tosend.put("team1goal",0);
                new AlertDialog.Builder(MatchHistory.this).setTitle("Confirm")
                        .setMessage("Are you sure to add this match")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("Matches").add(tosend);
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
            }

            @Override
            public void onNegativeButtonClick(Date date) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dateTimeDialogFragment.startAtCalendarView();
                dateTimeDialogFragment.setDefaultDateTime(new GregorianCalendar(2018,Calendar.FEBRUARY,4,15,20).getTime());
                dateTimeDialogFragment.show(getSupportFragmentManager(),TAG_DATETIME_FRAGMENT);

            }
        });

    }



    private void setUpRecyclerView(){
        Query query =matchreference.orderBy("datetime");
        FirestoreRecyclerOptions<Match> options = new FirestoreRecyclerOptions.Builder<Match>()
                .setQuery(query,Match.class)
                .build();
        adapter = new MatchAdapter(options);
        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Log.d("firebase","setup");
        adapter.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(final DocumentSnapshot documentSnapshot, int position) {
        if(documentSnapshot.get("over")!=null) {
            if ((boolean) documentSnapshot.get("over")) {
                Intent intent = new Intent(MatchHistory.this, MatchDetails.class);
                intent.putExtra("snapshot", documentSnapshot.getId());
                intent.putExtra("team1",documentSnapshot.get("team1").toString());
                intent.putExtra("team2",documentSnapshot.get("team2").toString());
                startActivity(intent);
            }else {
                Toast.makeText(MatchHistory.this,"Match not started",Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(MatchHistory.this).setTitle("Confirm")
                        .setMessage("Are you sure to start this match")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("Matches").document(documentSnapshot.getId()).update("live",true);

                                db.collection("Matches").document(documentSnapshot.getId()).update("over",true);
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.teamadderspinner1:
                team1d = parent.getItemAtPosition(position).toString();
                break;
            case R.id.teamadderspinner2:
                team2d = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
