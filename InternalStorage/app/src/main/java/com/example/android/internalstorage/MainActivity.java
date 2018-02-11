package com.example.android.internalstorage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText edtname, edtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname = (EditText) findViewById(R.id.editName);
        edtpass = (EditText) findViewById(R.id.editPass);
    }

    public void save(View v){
        File file = null;
        String name = edtname.getText().toString();
        String password = edtpass.getText().toString();
        FileOutputStream fileOutputStream = null;
        try{
            name = name + " ";
            file = getFilesDir();
            fileOutputStream = openFileOutput("Code.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(name.getBytes());
            fileOutputStream.write(password.getBytes());
            return;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                fileOutputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void next(View v){
        Toast.makeText(this, "Next", Toast.LENGTH_LONG);
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
