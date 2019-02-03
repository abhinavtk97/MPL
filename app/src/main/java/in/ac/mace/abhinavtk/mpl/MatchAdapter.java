package in.ac.mace.abhinavtk.mpl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import java.text.SimpleDateFormat;

import in.ac.mace.abhinavtk.mpl.pojo.Match;

public class MatchAdapter extends FirestoreRecyclerAdapter<Match,MatchAdapter.MatchHolder> {


    private OnItemClickListener listener;

    public MatchAdapter(@NonNull FirestoreRecyclerOptions<Match> options) {
        super(options);
    }
    SimpleDateFormat mydateformat = new SimpleDateFormat("d MMM yyyy",java.util.Locale.getDefault());

    @Override
    protected void onBindViewHolder(@NonNull MatchHolder holder, int position, @NonNull Match model) {

        if(model.isOver()){
            holder.team1goal.setText(String.valueOf(model.getTeam1goal()));
            holder.team2goal.setText(String.valueOf(model.getTeam2goal()));
            holder.team1stat.setText(model.getTeam1stat());
            holder.team2stat.setText(model.getTeam2stat());
        }

        holder.datetime.setText(mydateformat.format(model.getDatetime()));

        if(model.isLive()){
            holder.live.setVisibility(View.VISIBLE);
        }else {
            holder.live.setVisibility(View.GONE);
        }
        int t1=0,t2=0;

        switch (model.getTeam1()){
            case "Club De Dinkan":t1=R.drawable.dink; break;
            case "Bellaries FC" : t1=R.drawable.bell; break;
            case "Real Manavalan FC":t1=R.drawable.manav; break;
            case "Ponjikkara FC": t1=R.drawable.ponji; break;
            case "FC Marakkar":t1=R.drawable.mara; break;
            case "Chekuthans FC":t1=R.drawable.che;break;
            case "Dashamoolam FC":t1=R.drawable.dasha;break;
            case "Karakkambi FC":t1=R.drawable.kara;break;
        }
        switch (model.getTeam2()){
            case "Club De Dinkan":t2=R.drawable.dink; break;
            case "Bellaries FC" : t2=R.drawable.bell; break;
            case "Real Manavalan FC":t2=R.drawable.manav; break;
            case "Ponjikkara FC": t2=R.drawable.ponji; break;
            case "FC Marakkar":t2=R.drawable.mara; break;
            case "Chekuthans FC":t2=R.drawable.che;break;
            case "Dashamoolam FC":t2=R.drawable.dasha;break;
            case "Karakkambi FC":t2=R.drawable.kara;break;
        }

        holder.team1logo.setImageResource(t1);
        holder.team2logo.setImageResource(t2);
        holder.team1name.setText(model.getTeam1().toUpperCase());
        holder.team2name.setText(model.getTeam2().toUpperCase());
    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_match,viewGroup,false);
        return new MatchHolder(v);
    }

    class MatchHolder extends RecyclerView.ViewHolder {

        TextView team1goal;
        TextView team2goal;
        TextView datetime;
        TextView team1stat;
        TextView team2stat;
        ProgressBar live;
        ImageView team1logo;
        ImageView team2logo;
        TextView team1name;
        TextView team2name;

        public MatchHolder(View itemView){
            super(itemView);
            team1goal = itemView.findViewById(R.id.team1goal);
            team2goal = itemView.findViewById(R.id.team2goal);
            datetime = itemView.findViewById(R.id.datetime);
            team1stat = itemView.findViewById(R.id.team1stat);
            team2stat = itemView.findViewById(R.id.team2stat);
            live = itemView.findViewById(R.id.live);
            team1logo = itemView.findViewById(R.id.team1logo);
            team2logo = itemView.findViewById(R.id.team2logo);
            team1name = itemView.findViewById(R.id.team1name);
            team2name = itemView.findViewById(R.id.team2name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

}
