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

            holder.message.setText(model.getMessage());

            int t1=0,t2=0;

            switch (model.getTeam()){
                case "Club De Dinkan":t1=R.drawable.dink; break;
                case "Bellaries FC" : t1=R.drawable.bell; break;
                case "Real Manavalan FC":t1=R.drawable.manav; break;
                case "Ponjikkara FC": t1=R.drawable.ponji; break;
                case "FC Marakkar":t1=R.drawable.mara; break;
                case "Chekuthans FC":t1=R.drawable.che;break;
                case "Dashamoolam FC":t1=R.drawable.dasha;break;
                case "Karakkambi FC":t1=R.drawable.kara;break;
            }
            switch (model.getHeading()){
                case "Red Card":t2=R.drawable.red; break;
                case "Yellow Card" : t2=R.drawable.yellow; break;
                case "Goal":t2=R.drawable.goal; break;
            }
            holder.logodata.setImageResource(t1);
        holder.infodata.setImageResource(t2);
        holder.heading.setText(model.getHeading());
        holder.description.setText(model.getDescription());




    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new MatchHolder(v);
    }

    class MatchHolder extends RecyclerView.ViewHolder {

        TextView heading;
        TextView description;
        TextView message;
        ImageView logodata;
        ImageView infodata;


        public MatchHolder(View itemView){
            super(itemView);
            message = itemView.findViewById(R.id.team1data);
            heading = itemView.findViewById(R.id.heading);
            description = itemView.findViewById(R.id.description);
            logodata = itemView.findViewById(R.id.team1logodata);
            infodata = itemView.findViewById(R.id.team1infodata);
            Log.d("fir","start");

        }
    }

}
