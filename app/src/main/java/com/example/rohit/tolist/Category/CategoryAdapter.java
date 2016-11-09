package com.example.rohit.tolist.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.support.v7.widget.RecyclerView;

import com.example.rohit.tolist.R;
import com.example.rohit.tolist.Models.Work;

/**
 * Created by Rohit on 01/11/2016.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {




    List<Work> myWork;
    List<String>categories;
    private final OnItemClickListener listener;


    public CategoryAdapter(List<Work> myWork, OnItemClickListener listener) {
        this.myWork = myWork;
        categories=findUniqueCategory(myWork);
        this.listener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content2, parent, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
      //  final Work wk = myWork.get(position);
        String st="bug!!";
    //    if(categories.size() > position)
         st = categories.get(position);

        holder.heading.setText(st.toUpperCase());

        holder.bind(st, listener);

    }

    @Override
    public int getItemCount() {
        return categories==null ? 0 : categories.size();
    }

    public List<String> findUniqueCategory(List<Work> lst){
        List<String> unq = new ArrayList<String>();
        Iterator<Work> ite = lst.iterator();

        for (Work wk :lst) {
            String cat = wk.getCategory();

            if(!unq.contains(cat.toUpperCase()))
            {
                unq.add(cat.toUpperCase());

            //    System.out.println(cat.toUpperCase());
            }
        }

        return unq;
    }


}
