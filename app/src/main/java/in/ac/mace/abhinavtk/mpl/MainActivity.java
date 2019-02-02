package in.ac.mace.abhinavtk.mpl;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import in.ac.mace.abhinavtk.mpl.cards.SliderAdapter;
import in.ac.mace.abhinavtk.mpl.pojo.StatisticData;
import in.ac.mace.abhinavtk.mpl.utils.DecodeBitmapTask;

import java.util.Random;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private final int[] pics = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};
    private final int[] descriptions = {R.string.text1, R.string.text2, R.string.text3, R.string.text4, R.string.text5};
    private final String[] countries = {"MATCH HISTORY", "STATISTICS", "TEAMS", "FIXTURE"};
    private final String[] places = {"The Louvre", "Gwanghwamun", "Tower Bridge", "Temple of Heaven", "Aegeana Sea"};
    private final String[] temperatures = {"", "", "", "", ""};
    private final String[] times = {"Aug 1 - Dec 15    7:00-18:00", "Sep 5 - Nov 10    8:00-16:00", "Mar 8 - May 21    7:00-18:00"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(pics, 4, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;
    private TextSwitcher temperatureSwitcher;
    private TextSwitcher placeSwitcher;
    private TextSwitcher clockSwitcher;
    private TextSwitcher descriptionsSwitcher;
    private TextView country1TextView;
    private TextView country2TextView;
    private int countryOffset1;
    private int countryOffset2;
    private long countryAnimDuration;
    private int currentPosition;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference matchreference;
    private String docid=null;
    private ProgressBar live ;
    private TextView vs ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        live = findViewById(R.id.livehome1);
        vs= findViewById(R.id.vshome1);
        initRecyclerView();
        initCountryText();
        initSwitchers();
        getDocId();
        getNextMatch1Data();
        getNextMatch2Data();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PointsTable.class);
                startActivity(intent);
            }
        });




    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();

        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }



    private void initSwitchers() {
        temperatureSwitcher = (TextSwitcher) findViewById(R.id.ts_temperature);
        temperatureSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));
        temperatureSwitcher.setCurrentText(temperatures[0]);

    }

    private void initCountryText() {
        countryAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        countryOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        countryOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        country1TextView = (TextView) findViewById(R.id.tv_country_1);
        country2TextView = (TextView) findViewById(R.id.tv_country_2);

        country1TextView.setX(countryOffset1);
        country2TextView.setX(countryOffset2);
        country1TextView.setText(countries[0]);
        country2TextView.setAlpha(0f);

        country1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        country2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }



    private void setCountryText(String text, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (country1TextView.getAlpha() > country2TextView.getAlpha()) {
            visibleText = country1TextView;
            invisibleText = country2TextView;
        } else {
            visibleText = country2TextView;
            invisibleText = country1TextView;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = countryOffset2;
        } else {
            invisibleText.setX(countryOffset2);
            vOffset = 0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", countryOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(countryAnimDuration);
        animSet.start();
    }

    private void onActiveCardChange() {
        Log.d("tag","here");
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);

    }

    private void onActiveCardChange(int pos) {
        int animH[] = new int[] {R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[] {R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        currentPosition = pos;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }
        Log.d("tag",pos+"");

        setCountryText(countries[pos], left2right);

        temperatureSwitcher.setInAnimation(MainActivity.this, animH[0]);
        temperatureSwitcher.setOutAnimation(MainActivity.this, animH[1]);
        temperatureSwitcher.setText(temperatures[pos % temperatures.length]);

    }



    private class TextViewFactory implements  ViewSwitcher.ViewFactory {

        @StyleRes final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(MainActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(MainActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }

    private class ImageViewFactory implements ViewSwitcher.ViewFactory {
        @Override
        public View makeView() {
            final ImageView imageView = new ImageView(MainActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final LayoutParams lp = new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);

            return imageView;
        }
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            Intent intent = null;
            if (clickedPosition == activeCardPosition) {
                if(clickedPosition == 0) {
                    intent = new Intent(MainActivity.this, MatchHistory.class);
                    startActivity(intent);
                }else if(clickedPosition == 1){
                    intent = new Intent(MainActivity.this, Statistics.class);
                    startActivity(intent);
                }else if(clickedPosition == 2){
                    intent = new Intent(MainActivity.this, Teams.class);
                    startActivity(intent);
                }else if(clickedPosition == 3){
                    intent = new Intent(MainActivity.this, Fixture.class);
                    startActivity(intent);
                }

            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }


    private void getNextMatch1Data(){
        db.collection("NextMathches").document("Match1")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.w("Firebase","Listen failed");
                            return;
                        }

                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                TextView datetime = findViewById(R.id.datetimehome2);
                                ImageView team1logo = findViewById(R.id.team1logohome2);
                                ImageView team2logo = findViewById(R.id.team2logohome2);
                                datetime.setText(queryDocumentSnapshots.getString("datetime"));
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
        db.collection("NextMathches").document("Match2")
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e!=null){
                            Log.w("Firebase","Listen failed");
                            return;
                        }

                        if(queryDocumentSnapshots!=null&&queryDocumentSnapshots.exists()){
                            {
                                TextView datetime = findViewById(R.id.datetimehome3);
                                ImageView team1logo = findViewById(R.id.team1logohome3);
                                ImageView team2logo = findViewById(R.id.team2logohome3);
                                datetime.setText(queryDocumentSnapshots.getString("datetime"));
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
                                TextView team1goal = findViewById(R.id.team1goalhome1);
                                TextView team2goal = findViewById(R.id.team2goalhome1);
                                TextView datetime = findViewById(R.id.datetimehome1);
                                ImageView team1logo = findViewById(R.id.team1logohome1);
                                ImageView team2logo = findViewById(R.id.team2logohome1);
                                team1goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team1goal").intValue()));
                                team2goal.setText(String.valueOf(queryDocumentSnapshots.getDouble("team2goal").intValue()));
                                datetime.setText(queryDocumentSnapshots.getString("datetime"));
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

}