package project.todolist;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import project.todolist.model.todo;

@Dao
public interface DAO {

    @Insert
    void insert(todo item);

    @Delete
    void delete (todo item);

    @Update
    void update(todo item);

    @Query("DELETE FROM todo_table")
    void deleteAll ();

    @Query("SELECT * FROM todo_table")
    LiveData<List<todo>> getAllTodoItems ();
}
