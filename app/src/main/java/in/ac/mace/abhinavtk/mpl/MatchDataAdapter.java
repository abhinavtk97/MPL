package in.ac.mace.abhinavtk.mpl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import in.ac.mace.abhinavtk.mpl.pojo.MatchData;

public class MatchDataAdapter extends FirestoreRecyclerAdapter<MatchData,MatchDataAdapter.MatchHolder> {


    public MatchDataAdapter(@NonNull FirestoreRecyclerOptions<MatchData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MatchHolder holder, int position, @NonNull MatchData model) {

        if(model.getDescription().equals("")){
            holder.team1data.setText(model.getTeam1Message());
            holder.team2data.setText(model.getTeam2Message());
            int t1=0,t2=0;

            switch (model.getTeam1()){
                case "Club De Dinkan":t1=R.drawable.dink; break;
                case "Bellaries FC" : t1=R.drawable.bell; break;
                case "Real Manavalan FC":t1=R.drawable.manav; break;
                case "Ponjikkara": t1=R.drawable.ponji; break;
                case "FC Marakkar":t1=R.drawable.mara; break;
            }
            switch (model.getTeam2()){
                case "Club De Dinkan":t2=R.drawable.dink; break;
                case "Bellaries FC" : t2=R.drawable.bell; break;
                case "Real Manavalan FC":t2=R.drawable.manav; break;
                case "Ponjikkara": t2=R.drawable.ponji; break;
                case "FC Marakkar":t2=R.drawable.mara; break;
            }

            holder.team1logodata.setImageResource(t1);
            holder.team2logodata.setImageResource(t2);
            holder.description.setText("");
        }else{
            holder.team1data.setText("");
            holder.team2data.setText("");
            holder.description.setText(model.getDescription());
        }



    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new MatchHolder(v);
    }

    class MatchHolder extends RecyclerView.ViewHolder {

        TextView team1data;
        TextView team2data;
        TextView description;
        ImageView team1logodata;
        ImageView team2logodata;


        public MatchHolder(View itemView){
            super(itemView);
            team1data = itemView.findViewById(R.id.team1data);
            team2data = itemView.findViewById(R.id.team2data);
            description = itemView.findViewById(R.id.description);
            team1logodata = itemView.findViewById(R.id.team1logodata);
            team2logodata = itemView.findViewById(R.id.team2logodata);
            Log.d("fir","start");

        }
    }

}
