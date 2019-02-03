package in.ac.mace.abhinavtk.mpl;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import in.ac.mace.abhinavtk.mpl.pojo.StatisticData;

public class StatisticsAdapter extends ArrayAdapter<StatisticData> {


    public StatisticsAdapter(Context context, List<StatisticData> resource) {
        super( context,0, resource);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.stat_layout,parent,false);
        }

        TextView rank = convertView.findViewById(R.id.rank);
        ImageView icon = convertView.findViewById(R.id.teamiconrank);
        TextView name = convertView.findViewById(R.id.playernamerank);
        TextView goals = convertView.findViewById(R.id.goalsrank);

        int t1=0;
        StatisticData statisticData = getItem(position);
        switch (statisticData.getTeam()){
            case "Club De Dinkan":t1=R.drawable.dink; break;
            case "Bellaries FC" : t1=R.drawable.bell; break;
            case "Real Manavalan FC":t1=R.drawable.manav; break;
            case "Ponjikkara FC": t1=R.drawable.ponji; break;
            case "FC Marakkar":t1=R.drawable.mara; break;
            case "Chekuthans FC":t1=R.drawable.che;break;
            case "Dashamoolam FC":t1=R.drawable.dasha;break;
            case "Karakkambi FC":t1=R.drawable.kara;break;
        }
        rank.setText(String.valueOf(position+1));
        icon.setImageResource(t1);
        name.setText(statisticData.getName());
        goals.setText(String.valueOf(statisticData.getNumber()));

        return convertView;
    }
}
