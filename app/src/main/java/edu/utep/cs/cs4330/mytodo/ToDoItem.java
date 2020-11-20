package edu.utep.cs.cs4330.mytodo;

import java.util.ArrayList;
import java.util.List;

/** A to-do item. */
public class ToDoItem {

    /** All the to-do items. */
    private static List<ToDoItem> allItems = new ArrayList<>();

    private String description;

    /** True if this item was done. */
    private boolean done;

    public ToDoItem(String description) {
        this(description, false);
    }

    public ToDoItem(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public String description() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /** Return all the to-do items. */
    public static List<ToDoItem> allItems() {
        return allItems;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "description='" + description + '\'' +
                '}';
    }
}
