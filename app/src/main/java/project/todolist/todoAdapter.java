package project.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import project.todolist.model.todo;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.viewHolder> {

    private List<todo> todoItems= new ArrayList<>();


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        todo item = todoItems.get(position);
        holder.isDone.setChecked(item.isDone());
        holder.title.setText(item.getTitle());
        holder.descreption.setText(item.getDesc());

    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public void setTodoItems(List<todo> items){
        this.todoItems = items;
        notifyDataSetChanged();
    }
    public todo getItemAt(int position){
        return todoItems.get(position);
    }

     class viewHolder extends RecyclerView.ViewHolder {

        private CheckBox isDone ;
        private TextView title;
        private TextView descreption;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            isDone = itemView.findViewById(R.id.isDone_id);

            title = itemView.findViewById(R.id.title_id);

            descreption = itemView.findViewById(R.id.desc_id);
        }
    }
}
