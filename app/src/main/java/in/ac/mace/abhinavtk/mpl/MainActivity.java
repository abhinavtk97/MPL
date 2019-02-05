package in.ac.mace.abhinavtk.mpl;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import in.ac.mace.abhinavtk.mpl.cards.SliderAdapter;
import in.ac.mace.abhinavtk.mpl.pojo.Match;
import in.ac.mace.abhinavtk.mpl.pojo.StatisticData;
import in.ac.mace.abhinavtk.mpl.utils.DecodeBitmapTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String docid=null;
    private ProgressBar live ;
    private TextView vs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        live = findViewById(R.id.livehome1);
        vs= findViewById(R.id.vshome1);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PointsTable.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab1 = findViewById(R.id.fababout);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,About.class);
                startActivity(intent);
            }
        });

        ImageView im1 = findViewById(R.id.im1);
        Glide.with(MainActivity.this)
                .load(R.drawable.image8)
                .into(im1);

        ImageView im2 = findViewById(R.id.im2);
        Glide.with(MainActivity.this)
                .load(R.drawable.image3)
                .into(im2);
        ImageView im3 = findViewById(R.id.im3);
        Glide.with(MainActivity.this)
                .load(R.drawable.image4)
                .into(im3);
        ImageView im4 = findViewById(R.id.im4);
        Glide.with(MainActivity.this)
                .load(R.drawable.image6)
                .into(im4);

        new LongOp().execute();

    }

    private class LongOp extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {


            TextView t1 = findViewById(R.id.t1);
            t1.setTypeface(Typeface.createFromAsset(getAssets(),"open-sans-extrabold.ttf"));
            TextView t2 = findViewById(R.id.t2);
            t2.setTypeface(Typeface.createFromAsset(getAssets(),"open-sans-extrabold.ttf"));
            TextView t3 = findViewById(R.id.t3);
            t3.setTypeface(Typeface.createFromAsset(getAssets(),"open-sans-extrabold.ttf"));
            TextView t4 = findViewById(R.id.t4);
            t4.setTypeface(Typeface.createFromAsset(getAssets(),"open-sans-extrabold.ttf"));
            ImageView insta = findViewById(R.id.instagram);
            insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/mace_footballteam"));
                    i.setPackage("com.instagram.android");
                    try{
                        startActivity(i);
                    }catch (ActivityNotFoundException e){
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://instagram.com/mace_footballteam")));
                    }
                }
            });

            TextView appname = findViewById(R.id.appname);
            appname.setTypeface(Typeface.createFromAsset(getAssets(),"open-sans-extrabold.ttf"));

            CardView c1 = findViewById(R.id.card1);
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,MatchHistory.class));
                }
            });
            CardView c2 = findViewById(R.id.card2);
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Statistics.class));
                }
            });
            CardView c3 = findViewById(R.id.card3);
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Teams.class));
                }
            });
            CardView c4 = findViewById(R.id.card4);
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,Fixture.class));
                }
            });



            //getDocId();
            //getNextDocId();

            FirebaseMessaging.getInstance().subscribeToTopic("recieve")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Subscribed to Notifications";
                            if(!task.isSuccessful()){
                                msg = "Could not subscribe to notifiations";
                            }
                            Log.d("FCM",msg);
                            //Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();

                        }
                    });
            createNotifChannel();
            return null;
        }
    }

    private void createNotifChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Live Scores";
            String desc = "Live Scores of MSL@MACE";
            int importanceDefault = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("MSLNOTI",name,importanceDefault);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void getNextMatch1Data(){
        db.collection("Matches").document(ids.get(0))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.w("Firebase","Listen failed");
                            return;
                        }

                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                Match match = queryDocumentSnapshots.toObject(Match.class);
                                TextView datetime = findViewById(R.id.datetimehome2);
                                ImageView team1logo = findViewById(R.id.team1logohome2);
                                ImageView team2logo = findViewById(R.id.team2logohome2);
                                datetime.setText(mydateformat.format(match.getDatetime()));
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
                                    case "Chekuthans FC":t2=R.drawable.che;break;
                                    case "Dashamoolam FC":t2=R.drawable.dasha;break;
                                    case "Karakkambi FC":t2=R.drawable.kara;break;
                                }

                                TextView lv = findViewById(R.id.vshome2);
                                lv.setText("-");


                                TextView team1name = findViewById(R.id.team1namehome2);
                                TextView team2name = findViewById(R.id.team2namehome2);
                                team1logo.setImageResource(t1);
                                team2logo.setImageResource(t2);
                                team1name.setText(queryDocumentSnapshots.getString("team1").toUpperCase());
                                team2name.setText(queryDocumentSnapshots.getString("team2").toUpperCase());

                            }
                        }
                    }
                });
    }
    private void getNextMatch2Data(){
        db.collection("Matches").document(ids.get(1))
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.w("Firebase","Listen failed");
                            return;
                        }

                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                Match match = queryDocumentSnapshots.toObject(Match.class);
                                TextView datetime = findViewById(R.id.datetimehome3);
                                ImageView team1logo = findViewById(R.id.team1logohome3);
                                ImageView team2logo = findViewById(R.id.team2logohome3);
                                datetime.setText(mydateformat.format(match.getDatetime()));
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
                                    case "Chekuthans FC":t2=R.drawable.che;break;
                                    case "Dashamoolam FC":t2=R.drawable.dasha;break;
                                    case "Karakkambi FC":t2=R.drawable.kara;break;
                                }

                                TextView lv = findViewById(R.id.vshome3);
                                lv.setText("-");

                                TextView team1name = findViewById(R.id.team1namehome3);
                                TextView team2name = findViewById(R.id.team2namehome3);
                                team1logo.setImageResource(t1);
                                team2logo.setImageResource(t2);
                                team1name.setText(queryDocumentSnapshots.getString("team1").toUpperCase());
                                team2name.setText(queryDocumentSnapshots.getString("team2").toUpperCase());

                            }
                        }
                    }
                });
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

                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                Match match = queryDocumentSnapshots.toObject(Match.class);
                                TextView team1goal = findViewById(R.id.team1goalhome1);
                                TextView team2goal = findViewById(R.id.team2goalhome1);
                                TextView datetime = findViewById(R.id.datetimehome1);
                                ImageView team1logo = findViewById(R.id.team1logohome1);
                                ImageView team2logo = findViewById(R.id.team2logohome1);
                                team1goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team1goal").intValue()));
                                team2goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team2goal").intValue()));
                                datetime.setText(mydateformat.format(match.getDatetime()));
                                if(queryDocumentSnapshots.getBoolean("live")){
                                    live.setVisibility(View.VISIBLE);
                                    vs.setText("-");
                                }else {
                                    live.setVisibility(View.GONE);
                                    getDocId();
                                    vs.setText("Full Time");
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
                                    case "Chekuthans FC":t2=R.drawable.che;break;
                                    case "Dashamoolam FC":t2=R.drawable.dasha;break;
                                    case "Karakkambi FC":t2=R.drawable.kara;break;
                                }


                                TextView team1name = findViewById(R.id.team1namehome1);
                                TextView team2name = findViewById(R.id.team2namehome1);
                                team1logo.setImageResource(t1);
                                team2logo.setImageResource(t2);
                                team1name.setText(queryDocumentSnapshots.getString("team1").toUpperCase());
                                team2name.setText(queryDocumentSnapshots.getString("team2").toUpperCase());

                            }
                        }
                    }
                });
    }

    boolean started = false;

    private void getDocId(){
        db.collection("Matches").whereEqualTo("live",true)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.e("Firebase","getid failed");
                            return;
                        }
                        for (final QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                            boolean live = (boolean)documentSnapshot.get("live");
                            if(live){
                                docid =  documentSnapshot.getId();
                                CardView cardView = findViewById(R.id.matchcardhistoryhome1);
                                cardView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this,MatchDetails.class);
                                        intent.putExtra("snapshot",docid);
                                        intent.putExtra("team1",documentSnapshot.get("team1").toString());
                                        intent.putExtra("team2",documentSnapshot.get("team2").toString());
                                        startActivity(intent);
                                    }
                                });
                                if(!started)
                                    getMatchData();
                                return;
                            }
                        }
                    }
                });
    }

    List<String> ids = new ArrayList<>();
    SimpleDateFormat mydateformat = new SimpleDateFormat("d MMM yyyy",java.util.Locale.getDefault());

    private void getNextDocId(){
        db.collection("Matches")
                .orderBy("datetime")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.e("Firebase","getid failed");
                            return;
                        }
                        for (final QueryDocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                            boolean live = (boolean)documentSnapshot.get("live");
                            boolean over = (boolean)documentSnapshot.get("over");
                            if(!live&&!over){
                                ids.add(documentSnapshot.getId());
                            }
                        }
                        Log.e("Fire",String.valueOf(ids.size()));
                        if(ids.size()==1){
                            getNextMatch1Data();
                        }else if(ids.size()>=2){
                            getNextMatch1Data();
                            getNextMatch2Data();
                        }
                    }
                });
    }



}