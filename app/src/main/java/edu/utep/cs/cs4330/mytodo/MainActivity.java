//Gilbert Alvarez
//CS 4330
package edu.utep.cs.cs4330.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBase db;

    private ToDoAdapter todoAdapter;
    private Button add;
    private Button remove;
    private static List<ToDoItem> productsList = new ArrayList<ToDoItem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBase(this);

        productsList = db.allProducts();

        add = findViewById(R.id.addButton);
        remove = findViewById(R.id.removeButton);

        todoAdapter = new ToDoAdapter(this, R.layout.todo_item, productsList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(todoAdapter);

        add.setOnClickListener(this::addItem);
        remove.setOnClickListener(this::removeItem);
    }

    private void addItem(View view){
        EditText todoEdit = findViewById(R.id.todoEdit);
        String description = todoEdit.getText().toString();
        ToDoItem item = new ToDoItem(description);
        todoAdapter.add(item);
        todoAdapter.notifyDataSetChanged();
        todoEdit.setText("");
        //db.addItem(item);
    }

    private void removeItem(View view){
        todoAdapter.clear();
        todoAdapter.notifyDataSetChanged();
    }
}