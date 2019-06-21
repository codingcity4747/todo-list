package project.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class newTodoActivity extends AppCompatActivity {


    private EditText etTitle,etDesc ;
    public static final String EXTRA_TITLE ="project.todolist.EXTRA_TITLE";
    public static final String EXTRA_DESC ="project.todolist.EXTRA_DESC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Item");

        etTitle = findViewById(R.id.titileInput_id);
        etDesc = findViewById(R.id.descInput_id);
    }

    //save item
    private void saveTtem(){
        String title = etTitle.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESC,desc);

        setResult(RESULT_OK,data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_note_id:
                saveTtem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
