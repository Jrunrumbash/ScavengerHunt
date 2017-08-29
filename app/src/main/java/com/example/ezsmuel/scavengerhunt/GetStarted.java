package com.example.ezsmuel.scavengerhunt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GetStarted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
    }
    public void submitDetails(View v){
        EditText nameField = (EditText) findViewById(R.id.userNameEditText);
        String name = nameField.getText().toString();   //users name
        EditText emailField = (EditText) findViewById(R.id.emailEditText);
        String email = emailField.getText().toString(); //users email address
        String[] TO = {"tech.day.ireland@ericsson.com"};   //sends emails to this address

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, TO);
        String subject = "TechDay Scavenger Hunt -";//Scavenger Hunt Participant
        intent.putExtra(Intent.EXTRA_SUBJECT, subject +" '"+  name+"'");
        String userInfo = ("Name: "+name+ "\n"+"E-mail: "+email);
        intent.putExtra(Intent.EXTRA_TEXT, userInfo);

        Toast.makeText(this, "Please confirm your name and email", Toast.LENGTH_LONG).show();

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        Button submitDetails = (Button) findViewById(R.id.submitButton);


    }

public void newStart(View view) {
    Intent intent = new Intent(this, NewStartPage.class);
    startActivity(intent);
}


    public void startScavengerHunt(View view) {
        Intent intent = new Intent(this, ScannerPage.class);
        startActivity(intent);

    }

}
