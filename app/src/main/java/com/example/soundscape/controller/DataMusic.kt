package com.example.soundscape.controller

import android.util.Log
import com.example.soundscape.model.Music

object DataMusic {

     val musicList = ArrayList<Music>()

    fun addMusic(music: Music) {
        musicList.add(music)
        Log.d("DataMusic", "Music added: $music")
        Log.d("DataMusic", "Music list size: ${musicList.size}")
        Log.d("DataMusic", "Music list: ${music.image}")
        Log.d("DataMusic", "Music list: ${music.nameSong}")
        Log.d("DataMusic", "Music list: ${music.nameArtist}")
        

    }

    fun getSongs(): ArrayList<Music> {
        return musicList
    }

    fun findMusicByName(nameSong: String): Music? {
        return musicList.find { it.nameSong == nameSong }
    }

    fun removeMusicByName(nameSong: String): Boolean {
        return musicList.removeIf { it.nameSong == nameSong }
    }
}