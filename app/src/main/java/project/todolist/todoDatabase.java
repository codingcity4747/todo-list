package project.todolist;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import project.todolist.model.todo;

@Database (entities ={todo.class},version =1)
public abstract class todoDatabase extends RoomDatabase {

    private static final String databaseName = "todoList_database";
    private static todoDatabase instance;

    public abstract DAO todoDao();


    public static synchronized todoDatabase getInstance(Application application){
        if(instance == null){
            instance = Room.databaseBuilder(application.getApplicationContext(),todoDatabase.class
            ,databaseName).fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        
        return instance;
    }

    //callback

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateTodoItemsAsyncTask(instance).execute();
        }

    };

    /*populate table columns items*/

    private static class populateTodoItemsAsyncTask extends AsyncTask<Void,Void,Void>{
        private DAO todoDao;

        private populateTodoItemsAsyncTask(todoDatabase db){
            todoDao = db.todoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new todo(false,"title 1","description 1"));
            todoDao.insert(new todo(false,"title 2","description 2"));
            todoDao.insert(new todo(false,"title 3","description 3"));
            return null;
        }
    }

}
