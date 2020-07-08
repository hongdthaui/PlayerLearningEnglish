package com.hongdthaui.playerlearningenglish.manager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hongdthaui.playerlearningenglish.model.Album;
import com.hongdthaui.playerlearningenglish.model.Folder;
import com.hongdthaui.playerlearningenglish.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hongdthaui on 6/19/2020.
 */
public class SongManager {
    private Context context;
    private List<Song> listSong;
    private List<Album> listAlbum;
    private List<Folder> listFolder;
    public SongManager(Context context) {
        this.context = context;
        listSong = new ArrayList<>();
        listAlbum = new ArrayList<>();
        listFolder = new ArrayList<>();
    }
    public void fetchData(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] m_data = {MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA};

        Cursor c = context.getContentResolver().query(uri, m_data, MediaStore.Audio.Media.IS_MUSIC + "=1", null,
                MediaStore.Audio.Media.TITLE + " ASC");

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

            String id, name, title, album, artist, path;
            int duration;
            id = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
            name = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            title = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            album = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            artist = c.getString(c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            duration = (int) (c.getInt(c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
            path = c.getString(c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
            //Log.e("MUSIC","path="+path+" name="+name+" title="+title+" album="+album);

            Song song = new Song(name, title, album, artist, path, duration);
            listSong.add(song);

            // list Album
            boolean addedAlbum = false;
            for (Album lalbum:listAlbum) {
                if (album.equals(lalbum.getName())){
                    lalbum.getSongs().add(song);
                    addedAlbum = true;
                }
            }
            if (!addedAlbum){
                listAlbum.add(new Album(song));
                //Log.e("MUSIC","Add album="+song.getAlbum());
            }

            // list Folder
            boolean addedFolder = false;
            String[] folderArr = path.split("/");
            String folder = folderArr[folderArr.length-2];
            String pathFolder = path.substring(0,path.lastIndexOf("/"));
            //Log.e("MUSIC","folder="+folder+ " path="+pathFolder);
            for (Folder lfolder:listFolder){
                if (pathFolder.equals(lfolder.getPath())){
                    lfolder.getSongs().add(song);
                    addedFolder = true;
                }
            }
            if (!addedFolder){
                listFolder.add(new Folder(folder,pathFolder,song));
            }
/*            for (Folder folder1:mListFolder){
                Log.e("Folder","name="+folder1.getName()+" path="+folder1.getPath());
            }*/
        }
    }

    public List<Song> getListSong() {
        return listSong;
    }

    public List<Album> getListAlbum() {
        return listAlbum;
    }

    public List<Folder> getListFolder() {
        return listFolder;
    }
}
