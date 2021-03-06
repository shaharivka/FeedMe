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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//This class update the details of the client
public class UpdateClientDetails extends AppCompatActivity  implements View.OnClickListener{


    //maybe the toake from DB isnt corret check !!!!!!!
    EditText Name;
    EditText Password;
    EditText Adress;
    EditText Email;
    EditText Phone;
    Button Update;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public String newname;
    public String newphone;
    public String newemail;
    public String newadress;
    public String newpassword;
    String id_of_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("#####");
        Intent intent=getIntent();
        id_of_client= intent.getExtras().getString("id");

        System.out.println("id of client="+id_of_client);
        System.out.println("####");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_client_details);
        Name = (EditText)findViewById(R.id.Name);
        Password = (EditText)findViewById(R.id.Password);
        Adress = (EditText)findViewById(R.id.Adress);
        Email = (EditText)findViewById(R.id.Email);
        Phone = (EditText)findViewById(R.id.Phone);
        Update=(Button)findViewById(R.id.Update);


        Update.setOnClickListener(this);
        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("Cients/"+id_of_client);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newname=(String)snapshot.child("Name").getValue().toString();
                newphone=(String)snapshot.child("Phone").getValue().toString();
                newemail=(String)snapshot.child("Email").getValue().toString();
                newadress=(String)snapshot.child("Adress").getValue().toString();
                newpassword=(String)snapshot.child("Password").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {

        //need the id from last windows
        //name.getText().toString().isEmpty()

        if (view ==  Update) {

            if(!Name.getText().toString().isEmpty()) {
                newname=Name.getText().toString();
            }
            if(!Phone.getText().toString().isEmpty()) {
                newphone=Phone.getText().toString();
            }
            if(!Adress.getText().toString().isEmpty()) {
                newadress=Adress.getText().toString();
            }
            if(!Password.getText().toString().isEmpty()) {
                newpassword=Password.getText().toString();
            }
            if(!Email.getText().toString().isEmpty()) {
                newemail=Email.getText().toString();
            }
            Client newclient=new Client(newname, newpassword,newadress, newemail, newphone);
            newclient.id=id_of_client;
            reference.setValue(newclient);
            Intent intent = new Intent(this, ChooseCookingBakery.class);
            intent.putExtra("id",id_of_client);
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