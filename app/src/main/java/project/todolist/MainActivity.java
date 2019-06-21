package project.todolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import project.todolist.model.todo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int ADD_ITEM_REQUEST = 1;
    private todoViewModel viewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        addButton = findViewById(R.id.add_button_id);

        final todoAdapter adapter = new todoAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(todoViewModel.class);
        viewModel.getAllTodoItems().observe(this, new Observer<List<todo>>() {
            @Override
            public void onChanged(List<todo> todos) {
                //recycleView update
                Log.i(TAG, "onChanged: " + todos.size());
                adapter.setTodoItems(todos);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, newTodoActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getItemAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(newTodoActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(newTodoActivity.EXTRA_DESC);

            todo item = new todo(false, title, desc);
            if (title.isEmpty() || desc.isEmpty()) {
                Toast.makeText(this, "Please Insert title and Description", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.insert(item);
            Toast.makeText(this, "new Item was Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Item Not Saved", Toast.LENGTH_SHORT).show();
        }

    }

    //menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_item_id:
                viewModel.deleteAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
