package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   List<Record> records;
   Context context;

    public MyAdapter(Context context , ArrayList<Record> recordArrayList){

        this.context = context;
       records = recordArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cards_item, parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(records.get(position).type);
        holder.decryption.setText(records.get(position).text);




//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MyAdapter.this.context, AdViewActivity.class);
////                intent.putExtra("AdViewActivity.EXTRA_POS", position);
//               context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , decryption;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_name);
            decryption = itemView.findViewById(R.id.card_description);




            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    show(getAdapterPosition());
//                    int i = getAdapterPosition();
//
//
//                    Intent intent = new Intent(context, AdViewActivity.class);
//                    context.startActivities(intent);

//                    Toast toast = Toast.makeText(context ,"i" , Toast.LENGTH_LONG);
//                    toast.show();
                }
            });
        }

        void show(int position) {
            Intent intent = new Intent(context, AdViewActivity.class);
            Record record = records.get(position);
            intent.putExtra("edit_note_id", record.log);
            context.startActivity(intent);
        }


    }
}
