package com.example.quizdemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView testView;
    private Toolbar toolbar;

    private List<TestModel> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testView = findViewById(R.id.test_recycle_view);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        int cat_index = getIntent().getIntExtra("Cat_ID", 0);
        getSupportActionBar().setTitle(CategoryFragment.catList.get(cat_index).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        testView.setLayoutManager(linearLayoutManager);

        loadTestData();
        TestAdapter adapter = new TestAdapter(testList);
        testView.setAdapter(adapter);



    }

    private void loadTestData(){
        testList = new ArrayList<>();
        testList.add(new TestModel("1", 50, 20));
        testList.add(new TestModel("2", 40, 30));
        testList.add(new TestModel("3", 50, 0));
        testList.add(new TestModel("4", 60, 10));
        testList.add(new TestModel("5", 70, 70));
        testList.add(new TestModel("6", 80, 80));
        testList.add(new TestModel("7", 40, 50));
        testList.add(new TestModel("8", 90, 60));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            TestActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}