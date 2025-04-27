package com.example.lab9.utils;

import android.content.Context;
import com.example.lab9.model.Song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static List<Song> loadSongsFromFile(Context context) {
        List<Song> songList = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open("songs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    songList.add(new Song(parts[0], parts[1], parts[2]));
                }
            }

            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return songList;
    }
}
