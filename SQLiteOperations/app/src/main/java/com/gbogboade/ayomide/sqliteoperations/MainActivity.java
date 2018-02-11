package com.gbogboade.ayomide.sqliteoperations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText, passwordEditText, currentNameEditText, newNameEditText, deleteNameEditText;
    MyDBAdapter myDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = (EditText) findViewById(R.id.name_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        currentNameEditText = (EditText) findViewById(R.id.current_name_editText);
        newNameEditText = (EditText) findViewById(R.id.new_name_editText);
        deleteNameEditText = (EditText) findViewById(R.id.delete_name_editText);

        myDBAdapter = new MyDBAdapter(this);
    }

    public void addUser(View v){
        String name = nameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || password.isEmpty()){
            Message.message(getApplicationContext(), "Enter Both name and password");
        }else {
            long id = myDBAdapter.insertData(name, password);
            if (id <= 0){
                Message.message(getApplicationContext(), "Insertion Unsucessful!");
                nameEditText.setText("");
                passwordEditText.setText("");
            }else {
                Message.message(getApplicationContext(), "Insertion Sucessfull!");
                nameEditText.setText("");
                passwordEditText.setText("");
            }
        }
    }

    public void viewData(View v){
        String data = myDBAdapter.getData();
        Message.message(getApplicationContext(), data);
    }

    public void update(View v){
        String currentName = currentNameEditText.getText().toString();
        String newName = newNameEditText.getText().toString();
        if (currentName.isEmpty() || newName.isEmpty()){
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = myDBAdapter.updateName(currentName, newName);
            if (a <= 0){
                Message.message(getApplicationContext(), "Unsuccessful!");
                currentNameEditText.setText("");
                newNameEditText.setText("");
            }else {
                Message.message(getApplicationContext(), "Updated");
                nameEditText.setText("");
                passwordEditText.setText("");
            }
        }
    }

    public void delete(View v){
        String name = deleteNameEditText.getText().toString();
        if (name.isEmpty()){
            Message.message(getApplicationContext(), "Enter Data");
        } else {
            int a = myDBAdapter.delete(name);
            if (a <= 0){
                Message.message(getApplicationContext(), "Unsuccessful!");
                deleteNameEditText.setText("");
            } else {
                Message.message(getApplicationContext(), "DELETED");
                deleteNameEditText.setText("");
            }
        }
    }
}
















