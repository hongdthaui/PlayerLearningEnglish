package com.hongdthaui.playerlearningenglish.manager;

import android.content.Context;

import com.hongdthaui.playerlearningenglish.R;
import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Playlist;
import com.hongdthaui.playerlearningenglish.model.Song;
import com.hongdthaui.playerlearningenglish.room.PlaylistDB;
import com.hongdthaui.playerlearningenglish.room.PlaylistDatabase;
import com.hongdthaui.playerlearningenglish.room.PlaylistSongsDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 7/8/2020.
 */
public class PlaylistManager {
    private final int default_icon = R.drawable.ic_play_circle_24dp;
    private List<Playlist> myPlaylist;
    private List<Song> history;
    private List<Song> favorites;
    private List<Song> mostPlayed;
    private List<Song> lastAdded;
    private PlaylistDatabase database;

    public PlaylistManager(Context context) {
        myPlaylist = new ArrayList<>();
        history = new ArrayList<>();
        favorites = new ArrayList<>();
        mostPlayed = new ArrayList<>();
        lastAdded = new ArrayList<>();

        database = PlaylistDatabase.getInstance(context);
    }

    public void fetchData() {
        myPlaylist.clear();
        history.clear();
        favorites.clear();
        mostPlayed.clear();
        lastAdded.clear();
        List<PlaylistDB> playlistList = database.playlistDao().getAllPlaylist();
        for (PlaylistDB playlist : playlistList) {
            Playlist newplaylist = new Playlist(playlist.getName(), playlist.getTitle(), playlist.getIcon());

            List<PlaylistSongsDB> songs = database.playlistSongsDao().getSongs(playlist.getName());
            for (PlaylistSongsDB song : songs) {
                Song song1 = new Song(song.getSongTitle(), song.getSongArtist(), song.getSongPath(), song.getSongDuration());
                newplaylist.getSongs().add(song1);
            }
            myPlaylist.add(newplaylist);
        /*    switch (newplaylist.getName()) {
                case "history":
                    history=newplaylist;
                    break;
                case "favorites":
                    favorites=newplaylist;
                    break;
                case "mostPlayed":
                    mostPlayed=newplaylist;
                    break;
                case "lastAdded":
                    lastAdded=newplaylist;
                    break;
                default: {
                    myPlaylist.add(newplaylist);
                    break;
                }*/

            //}
        }
        List<PlaylistSongsDB> songs = database.playlistSongsDao().getSongs("history");
        for (PlaylistSongsDB song : songs) {
            Song song1 = new Song(song.getSongTitle(), song.getSongArtist(), song.getSongPath(), song.getSongDuration());
            history.add(song1);
        }

        songs = database.playlistSongsDao().getSongs("favorites");
        for (PlaylistSongsDB song : songs) {
            Song song1 = new Song(song.getSongTitle(), song.getSongArtist(), song.getSongPath(), song.getSongDuration());
            favorites.add(song1);
        }

        songs = database.playlistSongsDao().getSongs("mostPlayed");
        for (PlaylistSongsDB song : songs) {
            Song song1 = new Song(song.getSongTitle(), song.getSongArtist(), song.getSongPath(), song.getSongDuration());
            mostPlayed.add(song1);
        }

        songs = database.playlistSongsDao().getSongs("lastAdded");
        for (PlaylistSongsDB song : songs) {
            Song song1 = new Song(song.getSongTitle(), song.getSongArtist(), song.getSongPath(), song.getSongDuration());
            lastAdded.add(song1);
        }

    }

    public void addPlaylist(String name) {
        database.playlistDao().insertPlaylist(new PlaylistDB(name.replace(" ", "").toLowerCase(), name, default_icon));
        fetchData();
    }

    public void addSongToPlaylist(String name, Song song) {
        database.playlistSongsDao().insertSong(new PlaylistSongsDB(name, song));
    }

    public void addSongToHistory(Song song){
        PlaylistDB playlistDB = database.playlistDao().getPlaylist("history");
        if (playlistDB==null){
            playlistDB = new PlaylistDB("history","",R.drawable.ic_history_24dp);
            database.playlistDao().insertPlaylist(playlistDB);
        }
        PlaylistSongsDB playlistSongsDB = new PlaylistSongsDB("history",song);
        database.playlistSongsDao().insertSong(playlistSongsDB);
    }


    public List<Playlist> getMyPlaylist() {
        return myPlaylist;
    }

    public List<Song> getHistory() {
        return history;
    }

    public List<Song> getFavorites() {
        return favorites;
    }

    public List<Song> getMostPlayed() {
        return mostPlayed;
    }

    public List<Song> getLastAdded() {
        return lastAdded;
    }
}
