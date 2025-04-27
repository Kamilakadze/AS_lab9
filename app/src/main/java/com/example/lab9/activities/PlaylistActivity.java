package com.example.lab9.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab9.R;
import com.example.lab9.adapters.SongAdapter;
import com.example.lab9.model.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        recyclerView = findViewById(R.id.playlist_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        songList = new ArrayList<>();

        songList.add(new Song("Stay", "The Kid LAROI, Justin Bieber", "stay.mp3"));
        songList.add(new Song("Alone", "Alan Walker", "alone.mp3"));
        songList.add(new Song("Believer", "Imagine Dragons", "believer.mp3"));

        songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);
    }
}
