package com.srapps.user.a2shoppinglist;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity implements ListAdapter.CBoxListener{
    EditText tView;
    RecyclerView myList;
    List<String>shoppingList;
    CheckedTextView cView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String>dataS;
    private Parcelable recyclerViewState;
    private final String KEY_RECYCLER_STATE = "recycler_state";
    int backButtonCount=0;
    Button clearButton;
    Scanner as;


    @Override
    protected void onPause() {
        super.onPause();
        bundle = new Bundle();
        recyclerViewState = myList.getLayoutManager().onSaveInstanceState();
        bundle.putParcelable(KEY_RECYCLER_STATE, recyclerViewState);
    }

    Button b;
    Bundle bundle = null;
    Button c;
    private static String FILE_NAME ="example.txt";


    public void save(){
        String text ="";
        FileOutputStream stream  = null;

        try {
            for(int i=0; i<dataS.size(); i++){
                text+= dataS.get(i).toString()+ "\n";
                Log.d("hi",dataS.size()+"");
            }
            stream = openFileOutput(FILE_NAME,MODE_PRIVATE);
            stream.write(text.getBytes());


            Toast.makeText(this, FILE_NAME,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void load(View v){
        FileInputStream inputStream = null;

        try {
            inputStream = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while (((text =br.readLine())!=null)) {
                sb.append(text).append("\n");
            }
            tView.setText(sb.toString());
            shoppingList.add(tView.toString());

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();

        }



        finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        dataS.clear();
//        recyclerViewState = bundle.getParcelable(KEY_RECYCLER_STATE);
//        bundle.putParcelable(KEY_RECYCLER_STATE,recyclerViewState);
//
//
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bundle = outState;






    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bundle != null) {
            recyclerViewState = bundle.getParcelable(KEY_RECYCLER_STATE);
            myList.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        }
    }


    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while (((text = br.readLine()) != null)) {
                sb.append(text + " ");
            }

            dataS.add(sb.toString());




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tView = findViewById(R.id.addIText);
        cView = findViewById(R.id.appTextViewCBox);
        dataS = new ArrayList();
        shoppingList = new ArrayList();

        myList = findViewById(R.id.recycler_view);
        myList.setVisibility(View.VISIBLE);
        myList.addItemDecoration(new DividerItemDecoration(myList.getContext(),DividerItemDecoration.VERTICAL));
        layoutManager = new LinearLayoutManager(this);
        myList.setLayoutManager(layoutManager);
        myAdapter = new ListAdapter(dataS,this);
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
                c = findViewById(R.id.button);
                tView = findViewById(R.id.addIText);
                cView = findViewById(R.id.appTextViewCBox);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataS.add(tView.getText().toString());
                        tView.clearComposingText();
                        save();
                    }
                });

                c.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        load(cView);
                    }
                });
                clearButton = findViewById(R.id.button3);
                clearButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataS.clear();
                        myAdapter = new ListAdapter(dataS);
                        myList.setAdapter(myAdapter);
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
        myAdapter = new ListAdapter(dataS,this);
        myList.setAdapter(myAdapter);






    }

    public void addItem(){
        dataS.add(tView.getText().toString());
        tView.clearComposingText();


    }

    @Override
    public void onCheckClick(int position) {
        dataS.get(position);
    }
}

