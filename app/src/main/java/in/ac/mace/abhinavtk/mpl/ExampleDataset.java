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
                new Comment(R.drawable.dink, "Aaron Bradley", "When the sensor experiments for deep space, all mermaids accelerate mysterious, vital moons.", "jan 12, 2014",0),
                new Comment(R.drawable.manav, "Barry Allen", "It is a cold powerdrain, sir.", "jun 1, 2015",1),
                new Comment(R.drawable.che, "Bella Holmes", "Particle of a calm shield, control the alignment!", "sep 21, 1937",0),
                new Comment(R.drawable.che, "Caroline Shaw", "The human kahless quickly promises the phenomenan.", "may 23, 1967",0),
                new Comment(R.drawable.che, "Connor Graham", "Ionic cannon at the infinity room was the sensor of voyage, imitated to a dead pathway.", "sep 1, 1972",0),
                new Comment(R.drawable.manav, "Deann Hunt", "Vital particles, to the port.", "aug 13, 1995",1),
                new Comment(R.drawable.che, "Ella Cole", "Stars fly with hypnosis at the boldly infinity room!", "nov 18, 1952",0),
                new Comment(R.drawable.manav, "Jayden Shaw", "Hypnosis, definition, and powerdrain.", "apr 1, 2013",1),
                new Comment(R.drawable.che, "Jerry Carrol", "When the queen experiments for nowhere, all particles control reliable, cold captains.", "nov 14, 1964",0),
                new Comment(R.drawable.manav, "Lena Lukas", "When the c-beam experiments for astral city, all cosmonauts acquire remarkable, virtual lieutenant commanders.", "may 4, 1965",1),
                new Comment(R.drawable.che, "Leonard Kim", "Starships walk with love at the cold parallel universe!", "jul 3, 1974",0),
                new Comment(R.drawable.manav, "Mark Baker", "Friendship at the bridge that is when quirky green people yell.", "dec 24, 1989",1)));
        return comments;
    }
}
