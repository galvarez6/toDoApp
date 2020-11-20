package edu.utep.cs.cs4330.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/** Provide views for an AdapterView by returning a view
 * for each ToDoItem contained in a list. */
public class ToDoAdapter extends ArrayAdapter<ToDoItem> {

    private DataBase db;


    public ToDoAdapter(Context context, int resourceId, List<ToDoItem> items) {
        super(context, resourceId, items);
        db = new DataBase(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.todo_item, parent, false);
            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(view -> {
                CheckBox cb = (CheckBox) view;
                ToDoItem item = (ToDoItem) cb.getTag();
                item.setDone(cb.isChecked());
                int id = position +1;
                if(!checkBox.isChecked()){
                    db.delete(item.description());
                    Toast.makeText(getContext(),"debug rem: " + id, Toast.LENGTH_SHORT).show();
                }
                else if(checkBox.isChecked()){
                    db.addItem(item);
                    Toast.makeText(getContext(),"adding to db", Toast.LENGTH_SHORT).show();
                }
                //db.addItem(item);
                //Toast.makeText(getContext(),"adding to db", Toast.LENGTH_SHORT).show();
            });
        }

        ToDoItem current = getItem(position);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(current.description());
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(current.isDone());
        checkBox.setTag(current);
        return convertView;
    }
}
