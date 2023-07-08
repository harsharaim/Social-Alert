package com.example.socialalert.User;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.socialalert.Issue.Model;
import com.example.socialalert.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link user_contactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class user_contactFragment extends Fragment {
    private RecyclerViewAdapter adapter;
    private ArrayList<Model> modelArrayList;
    private RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public user_contactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment user_contactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static user_contactFragment newInstance(String param1, String param2) {
        user_contactFragment fragment = new user_contactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user_contact, container, false);
        modelArrayList=new ArrayList<>();
        recyclerView=v.findViewById(R.id.recyclerView);
        modelArrayList.add(new Model("Air Ambulance", "+91 9540161344"));
        modelArrayList.add(new Model("Ambulance", "102"));
        modelArrayList.add(new Model("Anti Poison", "1066"));
        modelArrayList.add(new Model("CYBER CRIME HELPLINE", "155620"));
        modelArrayList.add(new Model("Deputy Commissioner Of Police", "1094"));
        modelArrayList.add(new Model("Disaster Management", "01126701728"));
        modelArrayList.add(new Model("Disaster Management Services", "108"));
        modelArrayList.add(new Model("FIRE", "101"));
        modelArrayList.add(new Model("Kisan Call Centre", "18001801551"));
        modelArrayList.add(new Model("LPG Leak Helpline", "1906"));
        modelArrayList.add(new Model("NDRF HELPLINE", "+91 9711077372"));
        modelArrayList.add(new Model("Police", "100"));
        modelArrayList.add(new Model("Railway Accident Emergency Service", "1072"));
        modelArrayList.add(new Model("Railway Enquiry", "139"));
        modelArrayList.add(new Model("Road Accident Emergency Service", "1073"));
        modelArrayList.add(new Model("Senior Citizen Helpline", "14567"));
        modelArrayList.add(new Model("Tourist Helpline", "1800111363"));
        modelArrayList.add(new Model("Women Helpline", "1091"));
        modelArrayList.add(new Model("Women Helpline-(Domestic Abuse)", "181"));


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new RecyclerViewAdapter(modelArrayList,getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }
}