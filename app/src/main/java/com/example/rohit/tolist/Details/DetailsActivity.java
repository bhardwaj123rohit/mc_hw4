package com.example.rohit.tolist.Details;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.rohit.tolist.AddWork.AddWork;
import com.example.rohit.tolist.Category.CategoryActivity;
import com.example.rohit.tolist.Category.OnItemClickListener;
import com.example.rohit.tolist.FinalDetails.FinalDetails;
import com.example.rohit.tolist.R;
import com.example.rohit.tolist.Models.Work;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class DetailsActivity extends AppCompatActivity {

    private  RecyclerView mRecycleView;
    private DetailsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public  List<Work> myTodo = new ArrayList<Work>();
    public  String clickedCategory;

    // Firebase database instance and refrence
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("mylist");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setting up toolbar
        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        //Fetching the Category Clicked
        Intent data = getIntent();
        String category = data.getStringExtra("category");
        clickedCategory = category;

        //Setting up the reclycler view and adapter
        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);

        mAdapter = new DetailsAdapter(myTodo,clickedCategory, new onLongItemClick(){

            @Override
            public void onLongClick(Work w) {
                // Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT);
                Log.v("Long Click","Activity Wala1");
                Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT);
                w.getReference().removeValue();
                myTodo.remove(w);
                int index =  mAdapter.removeItem(w);
               // mAdapter.notifyItemRemoved(index);
                //mAdapter.notifyItemRangeChanged(index, myTodo.size());
                mRecycleView.invalidate();
                Log.v("Data Removed: ",w.getTitle());
                finish();

            }
        },new onMyClickListener() {
            @Override
            public void onItemClick(int item) {
                Log.v("Clickable.....","Item Clicked!!");
               // Toast.makeText(DetailsActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent data = new Intent(DetailsActivity.this,FinalDetails.class);
              //  data.putExtra("title",item.getTitle());
              //  data.putExtra("details",item.getDetail());
               // data.putExtra("position",myTodo.indexOf(item));
                data.putExtra("position",item);
                //data.putExtra("workList",myTodo);
                // Bundle list = new Bundle();
                startActivity(data);
            }
        });

        mRecycleView.setAdapter(mAdapter);

        // Adding Event listener to the database So that any changes in the database will be updated in realtime.
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Work temp = dataSnapshot.getValue(Work.class);
                temp.setReference(dataSnapshot.getRef());
                myTodo.add(temp);

                Log.v("Child Added:Details ",dataSnapshot.getRef().toString());
                mAdapter = new DetailsAdapter(myTodo,clickedCategory, new onLongItemClick(){

                    @Override
                    public void onLongClick(Work w) {
                       Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT).show();
                        Log.v("Long Click","Activity Wala2");
                        myTodo.remove(w);
                       Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT);
                        w.getReference().removeValue();
                        int index =  mAdapter.removeItem(w);

                        mRecycleView.invalidate();
                        Log.v("Data Removed: ",Integer.toString(myTodo.size()));
                        finish();

                    }
                },new onMyClickListener() {
                    @Override
                    public void onItemClick(int item) {
                        Log.v("Clickable.....","Item Clicked!!");
                       // Toast.makeText(DetailsActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent data = new Intent(DetailsActivity.this,FinalDetails.class);
                        //data.putExtra("category",item.getTitle());
                        //data.putExtra("details",item.getDetail());
                        //data.putExtra("position",myTodo.indexOf(item));
                        data.putExtra("position",item);

                        //data.putExtra("workList",myTodo);
                        // Bundle list = new Bundle();
                        startActivity(data);
                    }
                });
                mRecycleView.setAdapter(mAdapter);



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Work rem  = dataSnapshot.getValue(Work.class);
                Log.v("Child Removed Details: ",rem.getTitle());
                myTodo.remove(rem);

                DetailsActivity.super.recreate();
               /* mAdapter = new DetailsAdapter(myTodo,clickedCategory, new onLongItemClick(){

                    @Override
                    public void onLongClick(Work w) {
                        Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT).show();
                        Log.v("Long Click Changed","Activity Wala2");
                        myTodo.remove(w);
                        // Toast.makeText(DetailsActivity.this,"LongPressed",Toast.LENGTH_SHORT);
                        w.getReference().removeValue();
                        int index =  mAdapter.removeItem(w);

                        mRecycleView.invalidate();
                        Log.v("Data Removed: ",Integer.toString(myTodo.size()));
                        finish();

                    }
                });
                mRecycleView.setAdapter(mAdapter);
*/
  //              mAdapter.notifyDataSetChanged();
    //            mRecycleView.invalidate();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


       // prepareList();
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
        startActivity(new Intent(DetailsActivity.this, AddWork.class));

        mRecycleView.invalidate();
    }

}
