package com.example.feedme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//This class allows the client to connect to the app
public class CustomerLogin extends AppCompatActivity implements View.OnClickListener {
    EditText FullName;
    EditText Password;

    Button Connect;
    Button ForgotPassword;
    Button NewClient;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_customer_login);
            FullName = (EditText)findViewById(R.id.FullName);

            Password = (EditText)findViewById(R.id.Password);
            Connect=(Button)findViewById(R.id.Connect);
            ForgotPassword = (Button)findViewById(R.id.ForgotPassword);
            NewClient=(Button)findViewById(R.id.NewBusiness);
            Connect.setOnClickListener(this);
            ForgotPassword.setOnClickListener(this);
            NewClient.setOnClickListener(this);

    }

        @Override
        public void onClick (View view) {

            if (view == NewClient) {
                Intent intent = new Intent(this, RegisterNewClient.class);
                startActivity(intent);
            }

            if (view == Connect) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Cients");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //from DB
                            String name = (String) postSnapshot.child("Name").getValue().toString();
                            String password = (String) postSnapshot.child("Password").getValue().toString();
                            if (name.equals(FullName.getText().toString()) && password.equals(Password.getText().toString())) {
                                flag = 1;
                                String id = (String) postSnapshot.child("id").getValue().toString();
                                Intent intent = new Intent(CustomerLogin.this, ChooseCookingBakery.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                                break;
                            }


                        }
                        if (flag == 0) {
                            Toast.makeText(CustomerLogin.this, "WRONG PASSWORD/NAME", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
            if (view == ForgotPassword) {
                Intent intent = new Intent(this, ForgetPasswordClient.class);
                startActivity(intent);
          }
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opt_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.LogOut:
                Intent intent = new Intent(this, ConnectionWindows.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
        }

