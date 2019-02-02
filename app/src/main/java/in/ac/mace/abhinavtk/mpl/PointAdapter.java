package in.ac.mace.abhinavtk.mpl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import in.ac.mace.abhinavtk.mpl.pojo.Point;

public class PointAdapter extends FirestoreRecyclerAdapter<Point,PointAdapter.MatchHolder> {


    public PointAdapter(@NonNull FirestoreRecyclerOptions<Point> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MatchHolder holder, int position, @NonNull Point model) {

        holder.number.setText(String.valueOf(position+1));
        holder.name.setText(model.getTeam());
        int t1=0;

        switch (model.getTeam()){
            case "Club De Dinkan":t1=R.drawable.dink; break;
            case "Bellaries FC" : t1=R.drawable.bell; break;
            case "Real Manavalan FC":t1=R.drawable.manav; break;
            case "Ponjikkara": t1=R.drawable.ponji; break;
            case "FC Marakkar":t1=R.drawable.mara; break;
            case "Chekuthans FC":t1=R.drawable.che;break;
            case "Dashamoolam FC":t1=R.drawable.dasha;break;
            case "Karakkambi FC":t1=R.drawable.kara;break;
        }
        holder.icon.setImageResource(t1);
        holder.M.setText(String.valueOf(model.getM()));
        holder.W.setText(String.valueOf(model.getW()));
        holder.L.setText(String.valueOf(model.getL()));
        holder.D.setText(String.valueOf(model.getD()));
        holder.plus.setText(String.valueOf(model.getPlus()));
        holder.GD.setText(String.valueOf(model.getGD()));
        holder.P.setText(String.valueOf(model.getP()));

    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.point_layout,viewGroup,false);
        return new MatchHolder(v);
    }

    class MatchHolder extends RecyclerView.ViewHolder{

        TextView number;
        TextView name;
        TextView M;
        TextView W;
        TextView L;
        TextView D;
        TextView plus;
        TextView GD;
        TextView P;
        ImageView icon;


        public MatchHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.pointnumber);
            name = itemView.findViewById(R.id.pointname);
            M = itemView.findViewById(R.id.pointM);
            W = itemView.findViewById(R.id.pointW);
            L = itemView.findViewById(R.id.pointL);
            D = itemView.findViewById(R.id.pointD);
            plus = itemView.findViewById(R.id.pointplus);
            GD = itemView.findViewById(R.id.pointGD);
            P = itemView.findViewById(R.id.pointP);
            icon = itemView.findViewById(R.id.pointicon);

        }
    }
}
