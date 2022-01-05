package com.example.proje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentManager2";
    private static final String  TABLE_CONTACTS = "contacts";
    private static final String  KEY_ID = "id";
    private static final String  KEY_NAME = "name";
    private static final String  KEY_COURSE = "course";


    public DatabaseHelper (Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE  " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " STRING,"
                + KEY_COURSE + " STRING" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    void  addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getNo());
        values.put(KEY_COURSE, student.getCourse());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    Student getStudent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID, KEY_NAME, KEY_COURSE},KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return student;
    }
    public List<Student> getAllStudents(){
        List<Student> contactList = new ArrayList<>();

        String selectQuery = "SELECT *FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setNo(cursor.getString(1));
                student.setCourse(cursor.getString(2));

                contactList.add(student);
            }while (cursor.moveToNext());
        }
        return contactList;
    }

    public int upgradeStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getNo());
        values.put(KEY_COURSE, student.getCourse());

        return  db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_ID+ "=?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }

    public int getStudentsCount(){
        String countQuery = "SELECT *FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
}
