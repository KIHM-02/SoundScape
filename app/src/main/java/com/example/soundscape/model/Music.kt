package com.example.soundscape.model

import java.io.Serializable

class Music(
    var nameSong: String,
    var nameArtist: String,
    var image: String,
    var duraton: String,
    var genre: String,
    var uri: String,
): Serializable