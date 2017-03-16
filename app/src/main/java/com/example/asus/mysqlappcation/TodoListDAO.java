package com.example.asus.mysqlappcation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class TodoListDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    public TodoListDAO (Context context){
//get context and sent to helper
        dbHelper = new DbHelper(context);
    }
    //open database
    public void open(){
        database =dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    public ArrayList<TodoList> getAlltodoList(){
        ArrayList<TodoList> todoList = new ArrayList<TodoList>();
        Cursor cursor = database.rawQuery("SELECT * FROM tbtodo_list;",null);
        cursor.moveToFirst();
//add TodoList
        TodoList todoList1;
        while (!cursor.isAfterLast()){
//add TodoList
            todoList1 = new TodoList();
            todoList1.setTaskid(cursor.getInt(0));
            todoList1.setTaskname(cursor.getString(1));
            todoList.add(todoList1);
            cursor.moveToNext();
        }
        cursor.close();
        return todoList;
    }
    public void add(TodoList todoList){
//object was threw and recieve it
        TodoList newTodoList = new TodoList();
        newTodoList = todoList;
//after that GET value from object Todolist and send to ContentValues
        ContentValues values = new ContentValues();
//put table name and value to put it
        values.put("taskname", newTodoList.getTaskname());
        this.database.insert("tbtodo_list",null,values);
//to show result message
        Log.d("Todo List Demo :::", "Add OK");
    }

    public void update(TodoList todoList){
        TodoList updateTodolist = todoList;
        ContentValues values = new ContentValues();
        values.put("taskname",updateTodolist.getTaskname());
        values.put("taskid",updateTodolist.getTaskid());
        String where = "taskid=" + updateTodolist.getTaskid();

        this.database.update("tbtodo_list",values,where,null);
        Log.d("TodoList Demo","Update Ok ");
    }

    public void delete(TodoList todoList){
        TodoList delTodoList = todoList;
        String sqlText = "DELETE FROM tbtodo_list WHERE taskid = "+ delTodoList.getTaskid();
        this.database.execSQL(sqlText);
    }
}