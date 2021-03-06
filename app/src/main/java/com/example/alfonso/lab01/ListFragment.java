package com.example.alfonso.lab01;


import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private static List<Form> forms;
    ListView listView;
    private static ArrayList<Form> dataModels;
    private static FormAdapter adapter;
    private static final String DATABASE_NAME = "forms_db";

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        final AppDatabase formDatabase = Room.databaseBuilder(view.getContext(), AppDatabase.class,
                DATABASE_NAME).fallbackToDestructiveMigration().build();
        listView = (ListView) view.findViewById(R.id.list_form);
        dataModels = new ArrayList<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                forms = formDatabase.formDao().getAll();
                System.out.print(forms.size());
                for(int i = 0; i < forms.size(); i++) {
                    Form element = new Form();
                    element.setName(forms.get(i).getName());
                    element.setDate(forms.get(i).getDate());
                    element.setCategory(forms.get(i).getCategory());
                    element.setDescription(forms.get(i).getDescription());
                    dataModels.add(element);
                }
                adapter = new FormAdapter(dataModels, getActivity().getApplicationContext());
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QuestionFragment fragment = new QuestionFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        fragment).commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }
}
