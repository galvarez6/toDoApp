package edu.utep.cs.cs4330.mytodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.utep.cs.cs4330.mytodo.ToDoItem;

public class DataBase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "priceWatcherDB";
    private static final String Product_Table = "products";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";

    public DataBase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String table = "CREATE TABLE " + Product_Table + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT " + ")";
        Log.d("THIS", "SHIT string: "+ table);
        database.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + Product_Table);
        onCreate(database);
    }

    public void addItem(ToDoItem product) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.description());


        database.insert(Product_Table, null, values);
        //product.setId((int)id);
        database.close();
    }

    public List<ToDoItem> allProducts() {
        List<ToDoItem> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Product_Table;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
//                String url = cursor.getString(2);
//                float price = cursor.getFloat(3);
//                float percentageChange = cursor.getFloat(4);
//                String addedDate = cursor.getString(5);
//                float initialPrice = cursor.getFloat(6);
                ToDoItem product = new ToDoItem(name);
                list.add(product);
            }
            while(cursor.moveToNext());
        }
        return list;
    }

    public void delete(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        //database.execSQL("DROP TABLE IF EXISTS " + Product_Table);
        //database.delete(Product_Table, KEY_ID + " = ?", new String[] {String.valueOf(position)} );
        database.delete(Product_Table, KEY_NAME + " = ?", new String[] {name} );
        database.close();
    }

//    public void update(Product product) {
//
//        SQLiteDatabase database = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(KEY_NAME, product.getItem());
//        values.put(KEY_URL, product.getUrl());
//        values.put(KEY_PRICE, product.getInitialPrice());
//        values.put(KEY_CHANGE, product.getCurrentPrice());
//        values.put(KEY_DATE, product.getAddedDate());
//        values.put(KEY_INITIAL, product.getInitialPrice());
//
//        database.update(Product_Table, values, KEY_ID + " = ?", new String[]{String.valueOf(product.getId())});
//        database.close();
//    }
}
