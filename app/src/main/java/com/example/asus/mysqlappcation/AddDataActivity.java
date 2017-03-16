package com.example.asus.mysqlappcation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Log.d("asd", "Add OK");
        final EditText newTodoListText = (EditText) findViewById(R.id.editTextAddTask);
        final Button newTodoListButton = (Button) findViewById(R.id.btnAddNewTask);

        newTodoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("asd", "Add OK");
                Toast.makeText(getApplication(),"adsadsa",2000).show();
                TodoList todoList = new TodoList();
                todoList.setTaskname(String.valueOf(newTodoListText.getText()));
                TodoListDAO TodoListDAO = new TodoListDAO(getApplicationContext());
                TodoListDAO.open();
                TodoListDAO.add(todoList);
                TodoListDAO.close();
                finish();
            }
        });

    }

}