package com.example.rohit.tolist.Category;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.rohit.tolist.AddWork.AddWork;
import com.example.rohit.tolist.Details.DetailsActivity;
import com.example.rohit.tolist.R;
import com.example.rohit.tolist.Models.Work;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class CategoryActivity extends AppCompatActivity {

    private   RecyclerView mRecycleView;
    private CategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Work> myTodo;

    public final String updationError = "Updation Faliure";
    // Firebase database instance and refrence
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("mylist");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myTodo= new ArrayList<Work>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("mylist");
      //  prepareList();


        //Setting up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        mRecycleView = (RecyclerView) findViewById(R.id.recycler_view2);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        mAdapter = new CategoryAdapter(myTodo, new OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Log.v("Clickable.....","Item Clicked!!");
                Toast.makeText(CategoryActivity.this, item, Toast.LENGTH_SHORT).show();
                Intent data = new Intent(CategoryActivity.this,DetailsActivity.class);
                data.putExtra("category",item);
                //data.putExtra("workList",myTodo);
               // Bundle list = new Bundle();
                startActivityForResult(data,1);
            }
        });
        mRecycleView.setAdapter(mAdapter);

/*        // Button launches NewPostActivity
        findViewById(R.id.action_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, AddWork.class));

                mRecycleView.invalidate();
            }
        });*/


        // Adding Event listener to the database So that any changes in the database will be updated in realtime.
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               // Log.v("Child Added:Category ",s);
                myTodo.add(dataSnapshot.getValue(Work.class));
                mRecycleView.invalidate();
                mAdapter = new CategoryAdapter(myTodo, new OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        Log.v("Clickable.....","Item Clicked!!");
                        Toast.makeText(CategoryActivity.this, item, Toast.LENGTH_SHORT).show();
                        Intent data = new Intent(CategoryActivity.this,DetailsActivity.class);
                        data.putExtra("category",item);
                        //data.putExtra("workList",myTodo);
                        // Bundle list = new Bundle();
                        startActivityForResult(data,1);
                    }
                });
                mRecycleView.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Work rem  = dataSnapshot.getValue(Work.class);
                Log.v("Category Child removed",rem.getTitle());
                CategoryActivity.super.recreate();
             /*   mRecycleView.invalidate();
                myTodo.remove(rem);
                mAdapter = new CategoryAdapter(myTodo, new OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        Log.v("Clickable.....","Item Clicked!!");
                        Toast.makeText(CategoryActivity.this, item, Toast.LENGTH_SHORT).show();
                        Intent data = new Intent(CategoryActivity.this,DetailsActivity.class);
                        data.putExtra("category",item);
                        //data.putExtra("workList",myTodo);
                        // Bundle list = new Bundle();
                        startActivityForResult(data,1);
                    }
                });
                mRecycleView.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();
                mRecycleView.invalidate();*/
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.v("Child Moved",((Work)dataSnapshot.getValue()).getTitle());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // myRef.setValue(myTodo);

    }
// Dummy data to be added first time
    /*private void prepareList() {

        myTodo.add(new Work("Mobile Computing","Learing Firebase","assignments"));
        myTodo.add(new Work("Mobile Computing","Learing Recycler","assignments"));
        myTodo.add(new Work("Foundations of Computer Security ","Key Logger","assignments"));
        myTodo.add(new Work("Foundations of Computer Security ","MultiLevel Security","assignments"));

        myTodo.add(new Work("Foundations of Computer Security ","Android Malware","projects"));
        myTodo.add(new Work("Object Oriented Database","Smart Helath Database","projects"));

    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CategoryActivity.super.recreate();
        mRecycleView.invalidate();
/*        mRecycleView.invalidate();
        mAdapter = new CategoryAdapter(myTodo, new OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Log.v("Clickable.....","Item Clicked!!");
                Toast.makeText(CategoryActivity.this, item, Toast.LENGTH_SHORT).show();
                Intent data = new Intent(CategoryActivity.this,DetailsActivity.class);
                data.putExtra("category",item);
                //data.putExtra("workList",myTodo);
                // Bundle list = new Bundle();
                startActivityForResult(data,1);
            }
        });
        mRecycleView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();*/

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void newWork(MenuItem item) {
        startActivity(new Intent(CategoryActivity.this, AddWork.class));

        mRecycleView.invalidate();
    }
}


