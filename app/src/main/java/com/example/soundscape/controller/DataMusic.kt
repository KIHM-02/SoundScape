package com.example.soundscape.controller

import android.util.Log
import com.example.soundscape.model.Music

object DataMusic {

    val musicList = ArrayList<Music>()



    fun addMusic(music: Music) {
        musicList.add(music)
        Log.d("DataMusic", "Music added: $music")
    }

    fun findMusicByName(nameSong: String): Music? {
        return musicList.find { it.nameSong == nameSong }
    }

    fun removeMusicByName(nameSong: String): Boolean {
        return musicList.removeIf { it.nameSong == nameSong }
    }




}