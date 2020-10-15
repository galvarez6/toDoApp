package edu.utep.cs.cs4330.mytodo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/** Provide views for an AdapterView by returning a view
 * for each ToDoItem contained in a list. */
public class ToDoAdapter extends ArrayAdapter<ToDoItem> {


    public ToDoAdapter(Context context, int resourceId, List<ToDoItem> items) {
        super(context, resourceId, items);
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
