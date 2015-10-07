package com.example.stonesoup.solarsystem.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stonesoup.solarsystem.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUp extends AppCompatActivity {
    private EditText UserName , Password, Email;
    private Button ButtonSignUp;
    private String stUserName, stPassword,stEmail;
    private ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        UserName = (EditText)findViewById(R.id.sign_up_username);
        Password = (EditText)findViewById(R.id.sing_up_password);
        Email = (EditText)findViewById(R.id.sing_up_email);
        ButtonSignUp = (Button)findViewById(R.id.bt_sign_up_bt_sign);

        ButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new ParseUser();
                stUserName = UserName.getText().toString();
                user.setUsername(stUserName);
                stPassword = Password.getText().toString();
                user.setPassword(stPassword);
                stEmail = UserName.getText().toString();
                user.setEmail(stEmail);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Intent next = new Intent(SignUp.this, LogIn.class);
                            startActivity(next);
                            Toast toast = Toast.makeText(getApplicationContext(),"Nice to see you " + stUserName, Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
