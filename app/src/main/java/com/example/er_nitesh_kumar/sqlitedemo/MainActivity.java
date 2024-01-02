package com.example.er_nitesh_kumar.sqlitedemo;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Name, Pass, updateold, updatenew, delete, editaddr, editmob;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        updateold = (EditText) findViewById(R.id.editText3);
        updatenew = (EditText) findViewById(R.id.editText5);
        delete = (EditText) findViewById(R.id.editText6);

        editaddr = (EditText) findViewById(R.id.editaddr);
        editmob = (EditText) findViewById(R.id.editmob);


        helper = new myDbAdapter(this);
    }


    public void addUser(View view) {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        String t3 = editaddr.getText().toString();
        String t4 = editmob.getText().toString();
        if (t1.isEmpty() || t2.isEmpty()) {
//            Message.message(getApplicationContext(),"Enter Both Name and Password");
            Toast.makeText(MainActivity.this, "Enter Both Name and Password", Toast.LENGTH_SHORT).show();
        } else {
            long id = helper.insertData(t1, t2, t3, t4);
            if (id <= 0) {
//                Message.message(getApplicationContext(),"Insertion Unsuccessful");
                Toast.makeText(MainActivity.this, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
                Name.setText("");
                Pass.setText("");
                editaddr.setText("");
                editmob.setText("");

            } else {
                Toast.makeText(MainActivity.this, "Insertion successful", Toast.LENGTH_SHORT).show();
                Name.setText("");
                Pass.setText("");
                editaddr.setText("");
                editmob.setText("");

            }
        }
    }

    public void viewdata(View view) {
        String data = helper.getData();
//        Message.message(this, data);
        Toast.makeText(MainActivity.this, data + "", Toast.LENGTH_SHORT).show();

    }

    public void update(View view) {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if (u1.isEmpty() || u2.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();

        } else {
            int a = helper.updateName(u1, u2);
            if (a <= 0) {
//                Message.message(getApplicationContext(), "Unsuccessful");
                Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();

                updateold.setText("");
                updatenew.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();

//                Message.message(getApplicationContext(), "Updated");
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }

    public void delete(View view) {
        String uname = delete.getText().toString();
        if (uname.isEmpty()) {
            Toast.makeText(MainActivity.this, "Enter Data", Toast.LENGTH_SHORT).show();

        } else {
            int a = helper.delete(uname);
            if (a <= 0) {
                Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();

                delete.setText("");
            } else {
                Toast.makeText(MainActivity.this, "DELETED", Toast.LENGTH_SHORT).show();

                delete.setText("");
            }
        }
    }
}
