package project.todolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import project.todolist.model.todo;

@Database (entities ={todo.class},version =1)
public abstract class todoDatabase extends RoomDatabase {

    private static final String databaseName = "todoList_database";
    private static todoDatabase instance;


    public abstract DAO todoDao();


    public static synchronized todoDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),todoDatabase.class
            ,databaseName).fallbackToDestructiveMigration().build();
        }

        return instance;
    }

}
