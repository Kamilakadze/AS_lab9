package com.example.lab9.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab9.R;
import com.example.lab9.adapters.SongAdapter;
import com.example.lab9.model.Song;
import com.example.lab9.services.MusicService;
import com.example.lab9.utils.FileHelper;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SongAdapter songAdapter;
    private List<Song> songList;
    private Button btnStopMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        recyclerView = findViewById(R.id.playlist_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnStopMusic = findViewById(R.id.btn_stop_music);
        btnStopMusic.setOnClickListener(v -> stopService(new Intent(this, MusicService.class)));

        songList = FileHelper.loadSongsFromFile(this);
        songAdapter = new SongAdapter(songList);
        recyclerView.setAdapter(songAdapter);
    }
}
