package com.srapps.user.a2shoppinglist;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tView;
    RecyclerView myList;
    List<String>shoppingList;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<String>dataS;
    Button b;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataS = new ArrayList();

        myList = findViewById(R.id.recycler_view);
        myList.setVisibility(View.VISIBLE);
        myList.addItemDecoration(new DividerItemDecoration(myList.getContext(),DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        myList.setLayoutManager(layoutManager);

        myAdapter = new ListAdapter(dataS);
        myList.setAdapter(myAdapter);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        switch(id){
            case R.id.add_product:
                setContentView(R.layout.add_item);
                b = findViewById(R.id.button2);
                tView = findViewById(R.id.addIText);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataS.add(tView.getText().toString());
                        Toast.makeText(getApplicationContext(),"Item Added",Toast.LENGTH_SHORT).show();
                        tView.clearComposingText();
                    }
                });
                break;
            case R.id.first_list:
                setContentView(R.layout.activity_main);
                updateList();


                break;
        }
        return true;
    }



    public void updateList(){


        myList = findViewById(R.id.recycler_view);
        myList.setVisibility(View.VISIBLE);
        myList.addItemDecoration(new DividerItemDecoration(myList.getContext(),DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        myList.setLayoutManager(layoutManager);
        myAdapter = new ListAdapter(dataS);
        myList.setAdapter(myAdapter);


    }

    public void addItem(){
        dataS.add((String)tView.getText());
        tView.clearComposingText();

    }
}


