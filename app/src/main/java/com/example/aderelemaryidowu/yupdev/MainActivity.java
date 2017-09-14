package com.example.aderelemaryidowu.yupdev;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    CheckBox passwordVisible;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordEditText = (EditText) findViewById(R.id.password);
        passwordVisible = (CheckBox) findViewById(R.id.checked_pass);
        usernameEditText = (EditText) findViewById(R.id.username);
        submit = (Button) findViewById(R.id.submit_button);

        passwordVisible.setOnClickListener(new View.OnClickListener() {
            String passwordText = usernameEditText.getText().toString();
            @Override
            public void onClick(View view) {
                if(passwordText != null) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setMessage("Do you want to hide the Password?")
                            .setCancelable(false)
                            .setPositiveButton("SHOW", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                                    passwordVisible.setChecked(false);
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("HIDE", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                    passwordVisible.setChecked(false);
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                }
                else {

                    Toast.makeText(MainActivity.this, "No password was entered", Toast.LENGTH_LONG).show();
                    passwordVisible.setChecked(false);

                }
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            String usernameText = usernameEditText.getText().toString();
            String passwordText = usernameEditText.getText().toString();

            @Override
            public void onClick(View view) {

                if (usernameText != null && passwordText != null){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setMessage("Do you want to Continue?")
                            .setCancelable(false)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(((Dialog)dialog).getContext(), RecyclerActivity.class));
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                }
                else  {
                    Snackbar snackbar = Snackbar.make(getCurrentFocus(), "Username and/or Password Must Not Be Empty?", Snackbar.LENGTH_LONG);
                    snackbar.setAction("TRY AGAIN", new View.OnClickListener() {
                        public void onClick(View view) {
                            Toast.makeText(MainActivity.this, "Enter username and password, then Try again. Thanks!", Toast.LENGTH_LONG).show();
                        }
                    });
                    snackbar.show();
                }
            }
        });

    }
}