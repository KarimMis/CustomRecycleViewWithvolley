package info.androidhive.recyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.androidhive.recyclerview.app.AppController;
import info.androidhive.recyclerview.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        NetworkImageView thumbNail;
        public TextView title, rating, genre, year;


        public MyViewHolder(View view) {
            super(view);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            thumbNail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            rating = (TextView) view.findViewById(R.id.rating);
            year = (TextView) view.findViewById(R.id.releaseYear);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        // thumbnail image
        holder.thumbNail.setImageUrl(movie.getThumbnailUrl(), imageLoader);

        // title
        holder.title.setText(movie.getTitle());

        // rating
        holder.rating.setText("Rating: " + String.valueOf(movie.getRating()));

        // genre
        String genreStr = "";
        for (String str : movie.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;
        holder.genre.setText(genreStr);

        // release year
        holder.year.setText(String.valueOf(movie.getYear()));
    }

}
