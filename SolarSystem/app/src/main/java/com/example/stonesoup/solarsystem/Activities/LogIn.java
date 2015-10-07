package com.example.stonesoup.solarsystem.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stonesoup.solarsystem.MainActivity;
import com.example.stonesoup.solarsystem.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LogIn extends AppCompatActivity {
    private EditText userName, password;
    private Button signIn, signUp;
    private String stUserName, stPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        /**
         * skipping log in
         */
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null) {
            Intent current = new Intent(this, MainActivity.class);
            startActivity(current);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        userName = (EditText)findViewById(R.id.tv_username);
        password = (EditText)findViewById(R.id.tv_password);
        signIn = (Button)findViewById(R.id.bt_log_in);
        signUp = (Button)findViewById(R.id.bt_sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user name && password
                stUserName = userName.getText().toString();
                stPassword = password.getText().toString();

                 ParseUser.logInInBackground(stUserName, stPassword, new LogInCallback() {

                     public void done(ParseUser user, ParseException e) {
                         Log.d("billy"," " + stUserName + " " + stPassword);
                        if(user != null){
                            Toast.makeText(getApplicationContext(), "Welcome: " + stUserName,Toast.LENGTH_LONG).show();
                            Intent next = new Intent(LogIn.this, MainActivity.class);
                            startActivity(next);
                            finish();
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        } else {
                            Toast.makeText(getApplicationContext(), "No such user exist, please sign up",
                                    Toast.LENGTH_LONG).show();
                        }
                     }
                 });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
