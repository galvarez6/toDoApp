package edu.utep.cs.cs4330.mytodo;

import java.util.ArrayList;
import java.util.List;

/** A to-do item. */
public class ToDoItem {

    /** Unique id of this item. */
    private int id;

    private String description;

    /** True if this item was done. */
    private boolean done;

    public ToDoItem(String description) {
        this(description, false);
    }

    public ToDoItem(String description, boolean done) {
        this(0, description, done);
    }

    public ToDoItem(int id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public int id() {
        return id;
    }

    public String description() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
