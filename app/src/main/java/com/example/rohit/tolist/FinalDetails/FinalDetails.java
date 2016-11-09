package com.example.rohit.tolist.FinalDetails;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rohit.tolist.AddWork.AddWork;
import com.example.rohit.tolist.Details.DetailsActivity;
import com.example.rohit.tolist.R;

public class FinalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     setContentView(R.layout.pager_file);
       setContentView(R.layout.activity_final_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        int position =0;
        ViewPager mViewPager  = (ViewPager) findViewById(R.id.pager);

        Intent data = getIntent();
        //if (basket != null) {

        Bundle basket = getIntent().getExtras();
        if (basket != null) {
            position = basket.getInt("position");
        }


      /*  String pos = data.getStringExtra("position");
            position = Integer.parseInt(pos);
        //}*/
        //Log.v("Pager_position",position);
        //setContentView();

        mViewPager.setAdapter(new CustomPagerAdapter(this));
        mViewPager.setCurrentItem(position);
        Log.v("rohit","pagerSet");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void newWork(MenuItem item) {
        startActivity(new Intent(FinalDetails.this, AddWork.class));

       // mRecycleView.invalidate();
    }
}
