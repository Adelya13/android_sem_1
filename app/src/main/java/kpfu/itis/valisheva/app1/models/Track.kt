package kpfu.itis.valisheva.app1.models

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Track(
    val id: Int,
    val title: String,
    val author: String,
    @DrawableRes val cover: Int,
    @RawRes val trackSrc: Int,
)

