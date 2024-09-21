package com.example.quizdemoapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }

    private GridView catView;
    public static List<CategoryModel> catList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);

        catView= view.findViewById(R.id.cat_Grid);

        loadCategories();
        CategoryAdapter adapter= new CategoryAdapter(catList);
        catView.setAdapter(adapter);

        return view;
    }

    private void loadCategories() {
        catList.clear();

        catList.add(new CategoryModel("1", "GK1", 20));
        catList.add(new CategoryModel("2", "GK2", 25));
        catList.add(new CategoryModel("3", "GK3", 20));
        catList.add(new CategoryModel("4", "GK4", 25));
        catList.add(new CategoryModel("5", "GK5", 20));
        catList.add(new CategoryModel("6", "GK6", 25));

    }
}