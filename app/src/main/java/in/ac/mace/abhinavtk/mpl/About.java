package in.ac.mace.abhinavtk.mpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("MSL Live \n For Mar Athanasius College of Engineering")
                .addGroup("Connect with us")
                .addInstagram("mace_footballteam")
                .addGroup("About Developer")
                .addEmail("abhinavtk97@gmail.com")
                .addInstagram("abhinav.tk")
                .addGitHub("abhinavtk97")
                .create();
        setContentView(aboutPage);

    }
}
