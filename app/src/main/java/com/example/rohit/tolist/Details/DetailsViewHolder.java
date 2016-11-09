package com.example.rohit.tolist.Details;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.rohit.tolist.Category.OnItemClickListener;
import com.example.rohit.tolist.Models.Work;
import com.example.rohit.tolist.R;

/**
 * Created by Rohit on 01/11/2016.
 */
 public  class DetailsViewHolder extends RecyclerView.ViewHolder {

            public TextView heading,details;
            public DetailsViewHolder(View itemView) {
                super(itemView);
                heading = (TextView) itemView.findViewById(R.id.head);
                details= (TextView) itemView.findViewById(R.id.detail);

            }
    public void bind(final Work wk , final onLongItemClick listener){
       heading.setLongClickable(true);
        heading.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(wk);

                return  true;
            }
        });
    }


    public void bind2(final int wk, final onMyClickListener onClickListener) {


        heading.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                onClickListener.onItemClick(wk);
            }
        });


    }
}


