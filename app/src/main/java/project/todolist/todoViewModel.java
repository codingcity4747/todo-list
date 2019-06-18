package project.todolist;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import project.todolist.model.todo;

public class todoViewModel extends AndroidViewModel {

    private todoRepository repository;
    private LiveData<List<todo>> allTodoItems;


    public todoViewModel(@NonNull Application application) {
        super(application);
        repository = new todoRepository(application);
        allTodoItems = repository.getAllTodoItems();
    }


    public void insert (todo item){
        repository.insert(item);
    }

    public void update (todo item){
        repository.update(item);
    }

    public void delete (todo item){
        repository.delete(item);
    }

    public void deleteAll(){
        repository.deleteAllTodoItems();
    }

    public LiveData<List<todo>>getAllTodoItems(){
        return allTodoItems;
    }
}
