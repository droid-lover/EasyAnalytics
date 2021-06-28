package com.example.newsapplication.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
/**
 * Created by Sachin.
 */
@Entity
data class Articles(
//
//    @ColumnInfo
//    @SerializedName("source")
//    var source: Source? = null,

    @ColumnInfo
    @SerializedName("author")
    var author: String? = null,

    @ColumnInfo
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo
    @SerializedName("description")
    var description: String? = null,

    @ColumnInfo
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo
    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @ColumnInfo
    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @ColumnInfo
    @SerializedName("content")
    var content: String? = null

):Serializable {
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0
}
