package com.example.rohit.tolist.Category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohit.tolist.R;
import com.example.rohit.tolist.Models.Work;

/**
 * Created by Rohit on 01/11/2016.
 */
 public  class CategoryViewHolder extends RecyclerView.ViewHolder  {

            public TextView heading;
            public CategoryViewHolder(View itemView) {
                super(itemView);
                heading = (TextView) itemView.findViewById(R.id.head2);
                //details= (TextView) itemView.findViewById(R.id.detail2);

            }


    public void bind(final String wk, final OnItemClickListener listener) {

        heading.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                listener.onItemClick(wk);
            }
        });
    }

}


