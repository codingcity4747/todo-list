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
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import project.todolist.model.todo;

public class todoAdapter extends ListAdapter<todo,todoAdapter.viewHolder> {

    private onItemClickListener listener ;

    public todoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<todo>  DIFF_CALLBACK = new DiffUtil.ItemCallback<todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull todo oldItem, @NonNull todo newItem) {
             return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull todo oldItem, @NonNull todo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDesc().equals(newItem.getDesc()) ;
        }
    };


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        todo item = getItem(position);
        holder.isDone.setChecked(item.isDone());
        holder.title.setText(item.getTitle());
        holder.descreption.setText(item.getDesc());

    }




    public todo getItemAt(int position){
        return getItem(position);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener !=null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }


    public interface onItemClickListener{
        void onItemClick(todo item);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }
}
