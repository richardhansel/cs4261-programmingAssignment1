package com.example.myloginapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection connect;
    String ConnectionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "LOGIN FAILED !!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void GetTextFromSQL(View v)
    {
        TextView tx1 = (TextView) findViewById(R.id.firstName);
        TextView tx2 = (TextView) findViewById(R.id.lastName);

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null)
            {
                String query = "Select * from Users Where First_Name = 'Richard'";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while(rs.next()){
                    tx1.setText(rs.getString(2));
                    tx2.setText(rs.getString(3));
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch(Exception ex) {
            Log.e("Error ", ex.getMessage());
        }
    }


}