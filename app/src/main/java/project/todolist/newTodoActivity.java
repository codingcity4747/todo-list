package project.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class newTodoActivity extends AppCompatActivity {


    private EditText etTitle,etDesc ;
    public static final String EXTRA_ID ="project.todolist.EXTRA_ID";
    public static final String EXTRA_TITLE ="project.todolist.EXTRA_TITLE";
    public static final String EXTRA_DESC ="project.todolist.EXTRA_DESC";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        etTitle = findViewById(R.id.titileInput_id);
        etDesc = findViewById(R.id.descInput_id);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle(getResources().getString(R.string.editItem));
            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDesc.setText(intent.getStringExtra(EXTRA_DESC));
        }else{
            setTitle(getResources().getString(R.string.addItem));
        }



    }

    //save item
    private void saveTtem(){
        String title = etTitle.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESC,desc);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);

        }
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
