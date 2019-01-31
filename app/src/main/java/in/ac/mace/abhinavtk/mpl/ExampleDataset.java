package in.ac.mace.abhinavtk.mpl;


import com.ramotion.expandingcollection.ECCardData;
import in.ac.mace.abhinavtk.mpl.pojo.CardData;
import in.ac.mace.abhinavtk.mpl.pojo.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExampleDataset {

    private List<ECCardData> dataset;

    public ExampleDataset() {
        dataset = new ArrayList<>(5);

        CardData item5 = new CardData();
        item5.setMainBackgroundResource(R.drawable.footb1);
        item5.setHeadBackgroundResource(R.drawable.footb1);
        item5.setTeam1name("Club De Dinkan");
        item5.setTeam2name("Real Manavalan");
        item5.setGoal1(1);
        item5.setGoal2(0);
        item5.setTime("Mon 21");
        item5.setTeam2Goal("Ahi G\nAbhi G");
        item5.setTeam1Goal("Y   Abhinav TK \nG  Abhinav TK");
        item5.setPersonPictureResource(R.drawable.footb1);
        item5.setTeam1PictureResource(R.drawable.dink);
        item5.setTeam2PictureResource(R.drawable.manav);
        item5.setListItems(prepareCommentsArray());
        dataset.add(item5);

        CardData item1 = new CardData();
        item1.setMainBackgroundResource(R.drawable.footb2);
        item1.setHeadBackgroundResource(R.drawable.footb2);
        item1.setTeam1name("FC Marakkar");
        item1.setTeam2name("Bellaries FC");
        item1.setGoal1(8);
        item1.setGoal2(9);
        item1.setTime("Mon 22");
        item1.setTeam2Goal("'1  Ahi\n'2 Abhi");
        item1.setTeam1Goal("Abhinav TK  '1\nAbhinav TK  '2");
        item1.setPersonPictureResource(R.drawable.footb2);
        item1.setTeam1PictureResource(R.drawable.mara);
        item1.setTeam2PictureResource(R.drawable.bell);
        item1.setListItems(prepareCommentsArray());
        dataset.add(item1);

    }

    public List<ECCardData> getDataset() {
        return dataset;
    }

    private List<Comment> prepareCommentsArray() {
        Random random = new Random();
        List<Comment> comments = new ArrayList<>();
        comments.addAll(Arrays.asList(
                new Comment(R.drawable.dink, "Club De Dinakan", "Goal", "1:04",0),
                new Comment(R.drawable.manav, "Real Manavalan", "Goal", "1:05",1),
                new Comment(R.drawable.dink, "Club De Dinakan", "Goal", "1:08",0),
                new Comment(R.drawable.dink, "Club De Dinakan", "Goal", "1:04",0),
                new Comment(R.drawable.dink, "Club De Dinakan", "Red", "1:04",0),
                new Comment(R.drawable.manav, "Real Manavalan", "Yellow", "1:04",1),
                new Comment(R.drawable.dink, "Club De Dinakan", "Red", "1:04",0),
                new Comment(R.drawable.manav, "Real Manavalan", "Goal", "1:04",1),
                new Comment(R.drawable.dink, "Club De Dinakan", "Goal", "1:04",0),
                new Comment(R.drawable.manav, "Real Manavalan", "Goal", "1:04",1),
                new Comment(R.drawable.dink, "Club De Dinakan", "Red", "1:04",0),
                new Comment(R.drawable.manav, "Real Manavalan", "Goal", "1:04",1)));
        return comments;
    }
}
