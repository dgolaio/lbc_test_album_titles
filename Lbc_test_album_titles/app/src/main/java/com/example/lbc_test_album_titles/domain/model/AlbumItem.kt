package com.example.lbc_test_album_titles.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author : Duarte Golaio
 * @mailto : duarte_golaio@hotmail.com
 * @created : 14/03/2025
 **/

/**
 * Data Class of an Album with JsonAnnotations to Moshi
 * and Annotations to Room
 */

@Entity(tableName = "albums")
@JsonClass(generateAdapter = true)
data class AlbumItem(
    @Json(name = "albumId") val albumId: Int,
    @PrimaryKey
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "url") val imageUrl: String,
    @Json(name = "thumbnailUrl") val thumbnailUrl: String
)