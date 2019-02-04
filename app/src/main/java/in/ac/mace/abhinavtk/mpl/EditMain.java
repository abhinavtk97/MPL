package in.ac.mace.abhinavtk.mpl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EditMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_edit_main);
        final EditText headnoti = findViewById(R.id.headnotifi);
        final EditText messagenoti = findViewById(R.id.messagenotifi);
        Button send = findViewById(R.id.buttonnotifi);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = messagenoti.getText().toString();
                final String heading = headnoti.getText().toString();
                new AlertDialog.Builder(EditMain.this).setTitle("Are you sure to send")
                        .setMessage("Heading: "+heading+"\nMessage: "+message)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendMessage(heading,message);
                                messagenoti.setText("");
                                headnoti.setText("");
                            }
                        }).setNegativeButton(android.R.string.no,null).show();
            }
        });

    }

    private void sendMessage(String head,String message){

        String json = "{ \"to\": \"/topics/recieve\"," +
                "\"data\": {" +
                "\"head\":\""+head+"\",\"message\":\""+message+"\",}}";
        try {
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Authorization","key=AAAAgobSHTc:APA91bHtHMCKB5rDWh7wFf6At0dKaxkjW9AzkP4piIimpjIh63sZMH4j9OPB__UIc2b7Ez86pbXHhx87FsIed7bcXOhnRyauzaYMknR-iCpustL5N6Nnwl-SaFq83qjSB6zn6zZHrhRP");
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(json);
            dataOutputStream.close();
            DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
            StringBuffer ss = new StringBuffer();
            String temp;
            while((temp = dataInputStream.readLine())!=null){
                ss.append(temp);
            }
            dataInputStream.close();
            Toast.makeText(EditMain.this,"Message Send",Toast.LENGTH_LONG);
        } catch (MalformedURLException e) {
            Log.e("POST",e.toString());
        } catch (ProtocolException e) {
            Log.e("POST",e.toString());
        } catch (IOException e) {
            Log.e("POST",e.toString());
        }
    }
}
