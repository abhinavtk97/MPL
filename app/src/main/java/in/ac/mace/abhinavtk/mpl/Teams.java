package in.ac.mace.abhinavtk.mpl;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class Teams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        CardView dink = findViewById(R.id.dinkcard);
        CardView bell = findViewById(R.id.bellcard);
        CardView manav = findViewById(R.id.manavcard);
        CardView ponji = findViewById(R.id.ponjicard);
        CardView mara = findViewById(R.id.maracard);
        CardView che = findViewById(R.id.checard);
        CardView dash = findViewById(R.id.dashacard);
        CardView kara = findViewById(R.id.karacard);
        final Intent intent = new Intent(Teams.this,TeamDetails.class);
        dink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Club De Dinkan");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.dinkcard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Bellaries FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.bellcard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        manav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Real Manavalan FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.manavcard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        ponji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Ponjikkara FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.ponjicard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        mara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","FC Marakkar");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.maracard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Chekuthans FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.checard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Dashamoolam FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.dashacard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });

        kara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Karakkambi FC");
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
                    final CardView karaimg = findViewById(R.id.karacard);
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(Teams.this,(View)karaimg,"iconteam");
                    startActivity(intent,optionsCompat.toBundle());
                }else
                    startActivity(intent);
            }
        });
    }
}
