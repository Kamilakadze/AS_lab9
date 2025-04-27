package com.example.lab9.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab9.R;
import com.example.lab9.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songList;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        holder.titleTextView.setText(song.getTitle());
        holder.artistTextView.setText(song.getArtist());

        // Загружаем состояние избранного
        song.setFavorite(loadFavoriteState(holder.itemView.getContext(), song));

        if (song.isFavorite()) {
            holder.favoriteTextView.setVisibility(View.VISIBLE);
        } else {
            holder.favoriteTextView.setVisibility(View.GONE);
        }

        // Клик — Избранное
        holder.itemView.setOnClickListener(v -> {
            Context context = holder.itemView.getContext();

            context.stopService(new Intent(context, com.example.lab9.services.MusicService.class));

            Intent serviceIntent = new Intent(context, com.example.lab9.services.MusicService.class);
            serviceIntent.putExtra("songFile", song.getFilePath());
            context.startService(serviceIntent);
        });



        // Долгое нажатие — Удаление
        holder.itemView.setOnLongClickListener(v -> {
            showDeleteDialog(holder.itemView.getContext(), position);
            return true;
        });
    }


    @Override
    public int getItemCount() {
        return songList.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, artistTextView, favoriteTextView;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.song_title);
            artistTextView = itemView.findViewById(R.id.song_artist);
            favoriteTextView = itemView.findViewById(R.id.favorite_mark);
        }
    }

    private void saveFavoriteState(Context context, Song song) {
        context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
                .edit()
                .putBoolean(song.getTitle(), song.isFavorite())
                .apply();
    }

    private boolean loadFavoriteState(Context context, Song song) {
        return context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
                .getBoolean(song.getTitle(), false);
    }

    private void showDeleteDialog(Context context, int position) {
        new androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Удаление песни")
                .setMessage("Вы точно хотите удалить эту песню?")
                .setPositiveButton("Да", (dialog, which) -> {
                    songList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, songList.size());
                })
                .setNegativeButton("Отмена", null)
                .show();
    }
}
