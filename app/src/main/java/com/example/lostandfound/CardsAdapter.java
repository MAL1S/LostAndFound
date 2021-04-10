package com.example.lostandfound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    private static int viewHolderCount;
    private int cardItems;

    private Context parent;


    public CardsAdapter(int numberOfItems , Context parent){
        cardItems = numberOfItems;
        viewHolderCount = 0;
        this.parent = parent;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIfForListItem = R.layout.cards_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIfForListItem , parent , false);

        CardViewHolder viewHolder = new CardViewHolder(view);

        viewHolder.listItemCardView.setText("Собака");
        viewHolder.viewHolderIndex.setText("Найдена собака");

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.bind(position);

        if (position % 2 == 0) {

            holder.card.setBackgroundResource(R.color.light_grey);
        }

        else {
            holder.card.setBackgroundResource(R.color.white);
        }

    }

    @Override
    public int getItemCount() {
        return cardItems;
    }

    class CardViewHolder extends RecyclerView.ViewHolder{

        TextView listItemCardView;
        TextView viewHolderIndex;
        CardView card;
        ImageView companyImg;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemCardView = itemView.findViewById(R.id.card_name);
            viewHolderIndex = itemView.findViewById(R.id.card_description);

            card = (CardView) itemView.findViewById(R.id.item_card);

            companyImg = (ImageView) itemView.findViewById(R.id.img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getAdapterPosition();

                    Toast toast = Toast.makeText(parent , "i = " + i , Toast.LENGTH_SHORT);
                    toast.show();
                }
            });



        }

        void bind( int listIndex){

            //listItemCardView.setText(String.valueOf(listIndex));
        }
    }
}
