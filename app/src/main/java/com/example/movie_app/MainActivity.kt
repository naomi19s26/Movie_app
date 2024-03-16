package com.example.movie_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler

import org.json.JSONArray
import org.json.JSONException
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    companion object {
        const val URL = "https://api.themoviedb.org/3/movie/now_playing?&api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
    }

    private lateinit var movies: MutableList<Movie>
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setting up recycler view
        val rvMovies: RecyclerView = findViewById(R.id.rvMovies)
        movies = mutableListOf()
        movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        client.get(URL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                Log.d("MainActivity", "onSuccess")
                val jsonObject = json?.jsonObject

                try {
                    val results: JSONArray = jsonObject?.getJSONArray("results") ?: JSONArray()
                    Log.i("MainActivity", "Results: $results")
                    movies.addAll(Movie.fromJSONArray(results))
                    movieAdapter.notifyDataSetChanged()
                    Log.i("MainActivity", "Movies: ${movies.size}")
                } catch (e: JSONException) {
                    Log.e("MainActivity", "Json exception", e)
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("MainActivity", "onFailure")
            }
        })
    }
}
