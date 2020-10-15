//Gilbert Alvarez
//CS 4330
package edu.utep.cs.cs4330.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ToDoAdapter todoAdapter;
    private ToDoDatabaseHelper dbHelper;
    private EditText toDoEdit;
    private Button add;
    private Button remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.addButton);
        remove = findViewById(R.id.removeButton);
        toDoEdit = findViewById(R.id.todoEdit);

        dbHelper = new ToDoDatabaseHelper(this);
        todoAdapter = new ToDoAdapter(this, R.layout.todo_item, dbHelper.allItems());
        todoAdapter.setItemClikListener(item -> dbHelper.update(item));
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(todoAdapter);

        add.setOnClickListener(this::addItem);
        remove.setOnClickListener(this::removeItem);
    }

    private void addItem(View view){
        String description = toDoEdit.getText().toString();
        ToDoItem item = new ToDoItem(description);
        dbHelper.addItem(item);
        todoAdapter.add(item);
        todoAdapter.notifyDataSetChanged();
//        EditText todoEdit = findViewById(R.id.todoEdit);
//        String description = todoEdit.getText().toString();
//        ToDoItem item = new ToDoItem(description);
//        todoAdapter.add(item);
//        todoAdapter.notifyDataSetChanged();
        toDoEdit.setText("");
    }

    private void removeItem(View view){
        dbHelper.deleteAll(); todoAdapter.clear(); todoAdapter.notifyDataSetChanged();
//        todoAdapter.clear();
//        todoAdapter.notifyDataSetChanged();
    }
}