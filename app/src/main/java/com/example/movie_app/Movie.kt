package com.example.movie_app

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Movie(jsonObject: JSONObject) {
    private val backdropPath: String
    private val picturePath: String
    val title: String
    val overview: String

    init {
        backdropPath = jsonObject.getString("backdrop_path")
        picturePath = jsonObject.getString("poster_path")
        title = jsonObject.getString("title")
        overview = jsonObject.getString("overview")
    }

    fun getBackdropPath(): String {
        return String.format("https://image.tmdb.org/t/p/w500%s", backdropPath)
    }

    fun getPicturePath(): String {
        return String.format("https://image.tmdb.org/t/p/w500%s", picturePath)
    }

    companion object {
        @Throws(JSONException::class)
        fun fromJSONArray(movieJsonArray: JSONArray): List<Movie> {
            val movies: MutableList<Movie> = ArrayList()
            for (i in 0 until movieJsonArray.length()) {
                movies.add(Movie(movieJsonArray.getJSONObject(i)))
            }
            return movies
        }
    }
}