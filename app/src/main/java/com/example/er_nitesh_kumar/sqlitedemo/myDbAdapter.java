package com.example.er_nitesh_kumar.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;

import java.util.ArrayList;

/**
 * Created by Er_Nitesh_Kumar on 14-12-2017.
 */

public class myDbAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myDatabase";    // Database Name
    private static final String TABLE_NAME = "myTable";   // Table Name
    private static final int DATABASE_Version = 1;
    private Context context;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public myDbAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
//            db.execSQL("CREATE TABLE [Circle] ([CircleCode] TEXT,[CircleName] TEXT )");

            db.execSQL("CREATE TABLE myTable (_id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR(255),Password VARCHAR(255),Address VARCHAR(255),Mobile VARCHAR(255))");
        } catch (Exception e) {
//            Message.message(context,""+e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
//            Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
//            Message.message(context,""+e);
        }

    }


//    public void insertCircles(Circle[] result) {
//
//
//        long c = -1;
//        try {
//
//            SQLiteDatabase db = this.getWritableDatabase();
//            // db.execSQL("Delete from RANGE");
//            for (Circle circle : result) {
//
//                ContentValues values = new ContentValues();
//                values.put("CircleCode", circle.get_CircleCode());
//                values.put("CircleName", circle.get_CircleName());
//                String[] whereArgs = new String[]{circle.get_CircleCode()};
//                c = db.update("Circle", values, "CircleCode=?", whereArgs);
//                if (!(c > 0)) {
//
//                    c = db.insert("Circle", null, values);
//                }
//
//            }
//            db.close();
//
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        // return plantationList;
//    }

    public long insertData(String name, String pass, String addr, String mob) {
        SQLiteDatabase dbb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Password", pass);
        contentValues.put("Address", addr);
        contentValues.put("Mobile", mob);
        long id = dbb.insert("myTable", null, contentValues);
        return id;
    }


//    public ArrayList<SchemeNameClass> getSchemeLocal(String zoneid, String circleid, String divid) {
//
//
//        ArrayList<SchemeNameClass> schemeList = new ArrayList<SchemeNameClass>();
//        String[] params = new String[]{zoneid.trim(), circleid.trim(), divid.trim()};
//
//
//        try {
//
//            SQLiteDatabase db = this.getReadableDatabase();
//            Cursor cur = db
//                    .rawQuery(
//                            "SELECT  * from SchemeList WHERE ZoneCode=? and CircleCode=? and DivisionCode=? ",
//                            params);
//            int x = cur.getCount();
////			"SELECT DeptCode,DeptName from RoadType ORDER BY DeptName",
//            while (cur.moveToNext()) {
//
//                SchemeNameClass scheme = new SchemeNameClass();
//                scheme.setSchemeCode(cur.getString(cur
//                        .getColumnIndex("SchemeCode")));
//                scheme.setSchemeName(cur.getString(cur
//                        .getColumnIndex("SchemeName")));
//                scheme.setZoneCode(cur.getString(cur
//                        .getColumnIndex("ZoneCode")));
//                scheme.setCircleCode(cur.getString(cur
//                        .getColumnIndex("CircleCode")));
//                scheme.setDivisionCode(cur.getString(cur
//                        .getColumnIndex("DivisionCode")));
//
//                schemeList.add(scheme);
//            }
//
//            cur.close();
//            db.close();
//
//        } catch (Exception e) {
//            // TODO: handle exception
//            e.printStackTrace();
//        }
//        return schemeList;
//
//    }


    public String getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "Name", "Password", "Address", "Mobile"};
        Cursor cursor = db.query("myTable", columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int cid = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String password = cursor.getString(cursor.getColumnIndex("Password"));
            String Address = cursor.getString(cursor.getColumnIndex("Address"));
            String Mobile = cursor.getString(cursor.getColumnIndex("Mobile"));


            buffer.append(cid + "   " + name + "   " + password + "  " + Address + "  " + Mobile + "\n");
        }
        return buffer.toString();
    }


    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", newName);
        String[] whereArgs = {oldName};
        int count = db.update("myTable", contentValues, "Name=?", whereArgs);
        return count;
    }

    public int delete(String uname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete("myTable", "Name=?", whereArgs);
        return count;
    }

}
