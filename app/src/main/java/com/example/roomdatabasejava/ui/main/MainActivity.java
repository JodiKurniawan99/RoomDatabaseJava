package com.example.roomdatabasejava.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.roomdatabasejava.R;
import com.example.roomdatabasejava.databinding.ActivityMainBinding;
import com.example.roomdatabasejava.helper.ViewModelFactory;
import com.example.roomdatabasejava.ui.insert.NoteAddUpdateActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel mainViewModel = obtainViewModel(MainActivity.this);
        mainViewModel.getAllNotes().observe(this, notes -> {
            if (notes != null) {
                adapter.setListNotes(notes);
            }
        });

        adapter = new NoteAdapter();

        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setAdapter(adapter);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
            startActivity(intent);
        });
    }

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider((ViewModelStoreOwner) activity, (ViewModelProvider.Factory) factory).get(MainViewModel.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}