package com.example.dicerolldnd;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Integer> dice1 = new ArrayList(); //first dice
    public static ArrayList<Integer> dice2 = new ArrayList(); //second dice
    public static ArrayList<Integer> dice3 = new ArrayList(); //third dice\
    public static int dicerandom = 0;
    public static int dice1random = 0;
    public static int dice2random = 0;
    public static int dice3random = 0;

    public static EditText total;
    public static ArrayList<String> outputBox = new ArrayList();
    public static String all;
    public static int turn = 0;
    public static EditText username;
    public static ArrayList<String> characters = new ArrayList();
    public static ArrayAdapter<String> arrAd;
    static ListView listView;
    static String username2string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_tag, new FirstFragment());
        username = (EditText)findViewById(R.id.username);
        fragmentTransaction.commit();
        total = (EditText)findViewById(R.id.total);
        dice1.add(1); dice1.add(2); dice1.add(3); dice1.add(4); dice1.add(5); dice1.add(6);
        dice2.add(1); dice2.add(2); dice2.add(3); dice2.add(4); dice2.add(5); dice2.add(6);
        dice3.add(1); dice3.add(2); dice3.add(3); dice3.add(4); dice3.add(5); dice3.add(6);
        arrAd = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, characters);
        listView = (ListView) findViewById(R.id.listViewc);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
}