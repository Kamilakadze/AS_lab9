package com.example.lab9.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab9.R;
import com.example.lab9.adapters.SongAdapter;
import com.example.lab9.model.Song;
import com.example.lab9.utils.FileHelper;

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

        songList = FileHelper.loadSongsFromFile(this);

        songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);
    }
}
