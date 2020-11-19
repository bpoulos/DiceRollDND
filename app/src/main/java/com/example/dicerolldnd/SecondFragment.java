package com.example.dicerolldnd;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SecondFragment extends Fragment {
    public static SharedPreferences sp;
    public static EditText username;
    public static ArrayList<String> characters = new ArrayList();
    static ListView listView;
    static String username2string;
    public ArrayAdapter<String> arrayAdapter;
    public static Set<String> set;
    public static SharedPreferences.Editor spe;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
    public void onResume(){
        super.onResume();
        set = new HashSet<String>();
        sp = this.getActivity().getSharedPreferences("Shared Preferences", Context.MODE_PRIVATE);
        spe = sp.edit();
        username = (EditText)getView().findViewById(R.id.username);
        listView = (ListView)getView().findViewById(R.id.listViewc);
        arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, characters);
        set = sp.getStringSet("key4",null);
        listView.setAdapter(arrayAdapter);
        if (set == null)
            Log.i("SF(set)","Null Object");
        else{
            characters.addAll(set);
            Log.i("Set<String>","Added Set<String> set to ArrayList<String> characters");
        }
        spe.commit();
    }
    public void save(){
        if(set != null){
            set.add(username2string);
            spe.putStringSet("key4",set);
            spe.commit();
        }
        else
            Log.i("Set<String> set","Null Object");
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        view.findViewById(R.id.button_second2).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            username2string = username.getText().toString();
            characters.add(username2string);
            arrayAdapter.notifyDataSetChanged();
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("characterList",characters);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FirstFragment firstFragment = new FirstFragment();
            firstFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container_view_tag,firstFragment);
            fragmentTransaction.commit();
            save();
        }});
        view.findViewById(R.id.button_second3).setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View view) {
            if (characters.contains(username.getText().toString())){
                characters.remove(username.getText().toString());}
            arrayAdapter.notifyDataSetChanged();
        }});
    }
}