package project.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import project.todolist.model.todo;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private todoViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(todoViewModel.class);
        viewModel.getAllTodoItems().observe(this,new Observer<List<todo>>(){
            @Override
            public void onChanged(List<todo> todos) {
                //recycleView update
                Toast.makeText(MainActivity.this,"onChanged",Toast.LENGTH_LONG).show();;
            }
        });
    }
}
