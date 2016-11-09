package com.example.rohit.tolist.Category;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rohit.tolist.R;

/**
 * Created by Rohit on 02/11/2016.
 */

public class MyCategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView categoryHeading;
    public MyCategoryViewHolder(View itemView) {
        super(itemView);
        categoryHeading= (TextView) itemView.findViewById(R.id.categoryid);

    }
}
