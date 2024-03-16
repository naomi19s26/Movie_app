package com.example.movie_app


import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(private val context: Context, private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Inflating our XML layout and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("MovieAdapter", "onCreateViewHolder")
        val movieView: View =
            LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(movieView)
    }

    // Populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("MovieAdapter", "onBindViewHolder $position")
        // Get the movie at the passed in position
        val movie = movies[position]

        // Bind the movie data into the ViewHolder
        holder.bind(movie)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvOverview: TextView
        var ivPicture: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.Title)
            tvOverview = itemView.findViewById(R.id.Overview)
            ivPicture = itemView.findViewById(R.id.Picture)
        }


        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            val imageUrl: String


            // Implementing landscape functionality
            imageUrl =
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    movie.getBackdropPath()
                } else {

                    movie.getPicturePath()
                }
            // Using glide to load images
            Glide.with(context).load(imageUrl).into(ivPicture)
        }
    }
}