package com.example.rohit.tolist.FinalDetails;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.rohit.tolist.Details.DetailsActivity;
import com.example.rohit.tolist.Details.DetailsAdapter;
import com.example.rohit.tolist.R;

/**
 * Created by Rohit on 09/11/2016.
 */
class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return DetailsAdapter.catList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ScrollView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_file, container, false);

        TextView titleName = (TextView) itemView.findViewById(R.id.pager_title);
        TextView detailsTask = (TextView) itemView.findViewById(R.id.pager_details);

        String title = DetailsAdapter.catList.get(position).getTitle();
        String details = DetailsAdapter.catList.get(position).getDetail();

        titleName.setText(title);
        detailsTask.setText(details);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ScrollView) object);
    }
}

