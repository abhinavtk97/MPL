package in.ac.mace.abhinavtk.mpl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                startActivity(intent);
            }
        });
        bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Bellaries FC");
                startActivity(intent);
            }
        });
        manav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Real Manavalan FC");
                startActivity(intent);
            }
        });
        ponji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Ponjikkara FC");
                startActivity(intent);
            }
        });
        mara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","FC Marakkar");
                startActivity(intent);
            }
        });
        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Chekuthans FC");
                startActivity(intent);
            }
        });
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Dashamoolam FC");
                startActivity(intent);
            }
        });
        kara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("team","Karakkambi FC");
                startActivity(intent);
            }
        });
    }
}
