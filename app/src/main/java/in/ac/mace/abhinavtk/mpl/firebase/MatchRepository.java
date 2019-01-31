package in.ac.mace.abhinavtk.mpl.firebase;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MatchRepository {

    private FirebaseFirestore db;

    public MatchRepository(FirebaseFirestore db){
        this.db=db;
    }

    public void getLiveMatches(EventListener<QuerySnapshot> listener){
        db.collection("Matches")
                .whereEqualTo("live",true)
                .orderBy("order")
                .addSnapshotListener(listener);
    }

    public void getMatches(EventListener<QuerySnapshot> listener){
        db.collection("Matches")
                .whereEqualTo("live",false)
                .orderBy("order")
                .addSnapshotListener(listener);
    }

}
