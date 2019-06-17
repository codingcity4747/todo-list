package project.todolist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class todo {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    private boolean isDone;
    private String title ;
    private String desc ;


    //constructor
    public todo(boolean isDone , String title , String desc){
        this.isDone = isDone;
        this.title = title ;
        this.desc = desc;
    }

    //Getter
    public int getId() {
        return id;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }


    //Setter
    public void setId(int id) {
        this.id = id;
    }

}
