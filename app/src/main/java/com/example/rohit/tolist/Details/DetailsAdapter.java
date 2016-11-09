package com.example.rohit.tolist.Details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rohit.tolist.Category.OnItemClickListener;
import com.example.rohit.tolist.R;
import com.example.rohit.tolist.Models.Work;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rohit on 01/11/2016.
 */

public class DetailsAdapter extends RecyclerView.Adapter<DetailsViewHolder>{

    List<Work> myWork;
    String clkCat;
  public static  List<Work> catList;
    onLongItemClick longListener;
    onMyClickListener onClickListener;



    public DetailsAdapter(List<Work> myWork,String cat ,onLongItemClick listener,onMyClickListener onClickListener) {
        this.myWork = myWork;
        clkCat = cat;
        catList = findCategoryList(clkCat);
        longListener = listener;
        this. onClickListener = onClickListener;
    }

    private List<Work> findCategoryList(String clkCat) {
        List<Work> unq = new ArrayList<Work>();
        Iterator<Work> ite = myWork.iterator();

        for (Work wk :myWork) {
            String cat = wk.getCategory();

            if(cat.toUpperCase().equals(clkCat.toUpperCase()))
            {
                unq.add(wk);

                //System.out.println(cat.toUpperCase());
            }
        }

        return unq;
    }


    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content, parent, false);

        return new DetailsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        Work wk = catList.get(position);
        holder.heading.setText(wk.getTitle());
        holder.details.setText(wk.getDetail());
        holder.bind(wk,longListener);
        holder.bind2(position,onClickListener);
    }

    @Override
    public int getItemCount() {
        return catList==null ? 0 : catList.size();
    }

    public int removeItem(Work w) {
        int index = catList.indexOf(w);
        myWork.remove(w);
        this.notifyItemRemoved(index);
        this.notifyItemRangeChanged(index, catList.size());
        return index;
    }

}
