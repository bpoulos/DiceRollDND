package com.example.dicerolldnd;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FirstFragment extends Fragment {
    SharedPreferences sp;
    public EditText editText1; //first dice num(gets from dice1random)
    public EditText editText2; //second dice num(gets from dice2random)
    public EditText editText3; //third dice num(gets from dice3random)
    public ArrayList<Integer> dice1 = new ArrayList(); //first dice
    public ArrayList<Integer> dice2 = new ArrayList(); //second dice
    public ArrayList<Integer> dice3 = new ArrayList(); //third dice\
    public ArrayList<String> outputBox = new ArrayList();
    ArrayList<String> plusminus = new ArrayList();
    ArrayList<Integer> addsubnum = new ArrayList();
    Set<String> charTest;
    public int choice;
    public String choiceS;
    public ArrayAdapter<String> arrayAdapter;
    public int arandom;
    ArrayList<String> array = new ArrayList<String>();
    public int achoice;
    public int dicerandom = 0;
    public int dice1random = 0;
    public int dice2random = 0;
    public int dice3random = 0;
    public EditText total;
    public String all;
    public int turn = 0;
    ListView listView;
    public String totalString;
    Set<String> charSet;
    Set<String> inputSet;
    SharedPreferences.Editor spe;
    public String type = "Initiative";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_first, container, false);

    }
    public void onClick(View view){
        Log.i("Test","OnClick");
    }
    public void onResume(){
        super.onResume();
        inputSet = new HashSet<String>();
        charSet = new HashSet<String>();
        Bundle bundle = getArguments();
        ArrayList<String> characters = bundle.getStringArrayList("charactersList");
        sp = this.getActivity().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        spe = sp.edit();
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, outputBox);
        editText3 = (EditText)getView().findViewById(R.id.textView3);
        editText2 = (EditText)getView().findViewById(R.id.textView5);
        editText1 = (EditText)getView().findViewById(R.id.textView4);
        total = (EditText)getView().findViewById(R.id.total);
        total.setText(""+sp.getString("key1", "Your Roll:"));
        if (SecondFragment.sp == null){
            Log.i("SecondFragment.sp", "Null Object");
        }
        else {
            Set<String> charTest = (SecondFragment.sp.getStringSet("key4", null));
            Log.i("SecondFragment.sp","key set to Set<String>");
            //outputBox.add(charTest.toString());
        }
        inputSet = sp.getStringSet("key2", null);
        if (inputSet == null)
            Log.i("Null", "Null");
        else {
            outputBox.addAll(inputSet);
        }
        plusminus.add("+"); plusminus.add("-"); plusminus.add("+"); plusminus.add("+"); plusminus.add("-");
        addsubnum.add(0); addsubnum.add(0); addsubnum.add(0); addsubnum.add(0); addsubnum.add(1); addsubnum.add(1); addsubnum.add(1); addsubnum.add(2); addsubnum.add(2); addsubnum.add(3);
        //arrayAdapter = new ArrayAdapter<String>(getView(),android.R.layout.simple_list_item_1, outputBox);
        inputSet.addAll(charSet);
        dice1.add(1); dice1.add(2); dice1.add(3); dice1.add(4); dice1.add(5); dice1.add(6);
        dice2.add(1); dice2.add(2); dice2.add(3); dice2.add(4); dice2.add(5); dice2.add(6);
        dice3.add(1); dice3.add(2); dice3.add(3); dice3.add(4); dice3.add(5); dice3.add(6);
        listView = (ListView)getView().findViewById(R.id.listViewOut);
        outputBox.add(0,"testingDNDmethod112"); outputBox.remove("testingDNDmethod112");
        listView.setAdapter(arrayAdapter);
        outputBox.add(characters.toString());
        spe.commit();
        arrayAdapter.notifyDataSetChanged();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.button_first2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        view.findViewById(R.id.button_first3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });
        view.findViewById(R.id.button45).setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View view) {
                type = "Initiative"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
            }});
        view.findViewById(R.id.button46).setOnClickListener(new View.OnClickListener() { @Override
            public void onClick(View view) {
                type = "Acrobatics"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
            }});
        view.findViewById(R.id.button47).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Animal Handling"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button48).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Arcana"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button49).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Athletics"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button50).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Deception"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button51).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "History"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button52).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Insight"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button53).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Intimidation"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button54).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Investigation"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button55).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Medicine"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button56).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Nature"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button57).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Perception"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button58).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Performance"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button59).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Persuasion"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button60).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Religion"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button61).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Sleight Of Hand"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button62).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Stealth"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
        view.findViewById(R.id.button63).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            type = "Survival"; Toast.makeText(getActivity(), "You have selected '"+type+"'", Toast.LENGTH_SHORT).show();
        }});
    }
    public void rollDice(){
        listView.setAdapter(arrayAdapter);
        choice = (int)(Math.random()*5);
       // editText1 = (EditText)findViewById(R.id.textView4);
        //editText2 = (EditText)findViewById(R.id.textView5);
        turn+=1;
        arandom = (int)(Math.random()*10);
        dicerandom = (int)(Math.random()*6);
        dice1random = dice1.get(dicerandom);
        dicerandom = (int)(Math.random()*6);
        dice2random = dice2.get(dicerandom);
        dicerandom = (int)(Math.random()*6);
        dice3random = dice3.get(dicerandom);
        achoice = addsubnum.get(arandom);
        choiceS = plusminus.get(choice);
        editText1.setText(""+dice1random);
        editText2.setText(""+dice2random);
        editText3.setText(""+dice3random);
        total.setText("Your Roll: "+(dice1random+dice2random+dice3random)+""+choiceS+achoice);
        showOutput();
        saveInstance();
    }
    public void saveInstance(){
        totalString = total.getText().toString();
        spe.putString("key1", totalString);
        inputSet.addAll(outputBox);
        spe.putStringSet("key2", inputSet);
        spe.commit();
    }
    public void reset(){
        outputBox.clear();
        total.setText("Your Roll:");
        saveInstance();
        arrayAdapter.notifyDataSetChanged();
    }
    public void showOutput(){
        if(choiceS == "+")
            all = ("User has rolled a "+(dice1random+dice2random+dice3random+achoice+" for "+type+"!"));
        else if(choiceS == "-")
            all = ("User has rolled a "+(dice1random+dice2random+dice3random-achoice+" for "+type+"!"));
        else
            all = ("Unable to read String; Try again.");
        outputBox.add(0,all);
    }

}