package project.todolist;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import project.todolist.model.todo;

public class todoRepository {

    private DAO todoDao;
    private LiveData<List<todo>> allTodoItems;

    public todoRepository(Application application){
        todoDatabase database = todoDatabase.getInstance(application);
        todoDao = database.todoDao();
        allTodoItems = todoDao.getAllTodoItems();
    }

    public void insert (todo item){
        new insertAsyncTask(todoDao).execute(item);
    }

    public void update (todo item){
        new updateAsyncTask(todoDao).execute(item);
    }

    public void delete (todo item){
        new deleteAsyncTask(todoDao).execute(item);
    }

    public LiveData<List<todo>> getAllTodoItems(){
        return allTodoItems;
    }

    public void deleteAllTodoItems(){
        new deleteAllAsyncTask(todoDao).execute();
    }


    //AsyncTask classes to do background database operation
    // because database operation does not excute on the main thread
    // if it does it will freeze the main UI thread until it finish
    //the operation

    /*get all items*/
    private static class getAllItemsAsyncTask extends AsyncTask<Void,Void,Void>{
        private DAO todoDao ;

        private getAllItemsAsyncTask(DAO dao){
            todoDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.getAllTodoItems();
            return null;
        }
    }



    /*insert*/
    private static class insertAsyncTask extends AsyncTask<todo,Void,Void>{

        private DAO todoDao;

        private insertAsyncTask (DAO dao){
            todoDao = dao;
        }
        @Override
        protected Void doInBackground(todo... todos) {
            todoDao.insert(todos[0]);
            return null;
        }
    }


    /*update*/
    private static class updateAsyncTask extends AsyncTask<todo,Void,Void>{
        private DAO todoDao ;


        private updateAsyncTask(DAO dao){
            todoDao = dao;
        }

        @Override
        protected Void doInBackground(todo... todos) {
            todoDao.update(todos[0]);
            return null;
        }
    }


    /*delete item*/
    private static class deleteAsyncTask extends AsyncTask<todo,Void,Void>{
        private DAO todoDao;

        private deleteAsyncTask (DAO dao){
            todoDao = dao;
        }


        @Override
        protected Void doInBackground(todo... todos) {
            todoDao.delete(todos[0]);
            return null;
        }
    }


    /*delete all items*/
    private static class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private DAO todoDao;

        private  deleteAllAsyncTask(DAO dao){
            todoDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.deleteAll();
            return null;
        }
    }

}
