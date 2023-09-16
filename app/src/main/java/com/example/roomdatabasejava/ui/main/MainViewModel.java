package com.example.roomdatabasejava.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabasejava.database.Note;
import com.example.roomdatabasejava.repository.NoteRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final NoteRepository mNoteRepository;
    public MainViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }
    LiveData<List<Note>> getAllNotes() {
        return mNoteRepository.getAllNotes();
    }
}
