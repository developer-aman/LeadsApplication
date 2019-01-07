package com.agtechnosolution.leadsapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.agtechnosolution.leadsapplication.R.id.nv;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle t;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private ArrayList<Lead> listFetched;
    private DatabaseHandler db;
    LeadsRecyclerAdapter leadsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseHandler(this);
        drawerLayout = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, drawerLayout,R.string.Open, R.string.Close);
        recyclerView=findViewById(R.id.leads_recycler);
        emptyView=findViewById(R.id.empty_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView)findViewById(nv);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.all_leads:
                        drawerLayout.closeDrawers();
                        Toast.makeText(MainActivity.this, "All Leads",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.add_lead:
                        drawerLayout.closeDrawers();
                        Intent intent=new Intent(MainActivity.this,AddActivity.class);
                        startActivity(intent);
                        break;
//                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
                return true;
            }
        });

    }

    public void populateData(){
        listFetched=db.getAllLeads();
        if(listFetched.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            leadsRecyclerAdapter=new LeadsRecyclerAdapter(listFetched);
            recyclerView.setAdapter(leadsRecyclerAdapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(db!=null)
            db.close();
    }
}
