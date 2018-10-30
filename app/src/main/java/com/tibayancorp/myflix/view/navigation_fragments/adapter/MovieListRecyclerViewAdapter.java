package com.tibayancorp.myflix.view.navigation_fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.tibayancorp.myflix.R;
import com.tibayancorp.myflix.view.navigation_fragments.MoviesFragment.OnListFragmentInteractionListener;
import com.tibayancorp.myflix.view.navigation_fragments.dummy.DummyContent.DummyItem;
import com.tibayancorp.myflix.model.entities.Movie;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieListRecyclerViewAdapter extends RecyclerView.Adapter<MovieListRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> movieList;
    private final OnListFragmentInteractionListener mListener;

    public MovieListRecyclerViewAdapter(List<Movie> items, OnListFragmentInteractionListener listener) {
        movieList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movieItem = movieList.get(position);
        holder.movieTitleView.setText(movieList.get(position).getTitle());
        holder.movieDescriptionView.setText(movieList.get(position).getOverview());
        holder.movieRatingView.setText(movieList.get(position).getVote_average());
        //holder.moviePosterView.setText(movieList.get(position).content); This is where Picasso will be used for Loading the image.
        
        /* Review this code first before running. */
        Picasso.get().load(API.IMAGE_BASE_URL+movieList.get(position).getBackdropPath()).into(holder.moviePosterView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.movieItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView movieTitleView;
        public final TextView movieDescriptionView;
        public final TextView movieRatingView;
        public final ImageView moviePosterView;
        public Movie movieItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            movieTitleView = (TextView) view.findViewById(R.id.movieItemTitle);
            movieDescriptionView = (TextView) view.findViewById(R.id.movieItemDescription);
            movieRatingView = (TextView) view.findViewById(R.id.movieItemRating);
            moviePosterView = (TextView) view.findViewById(R.id.movieItemPoster);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + movieTitleView.getText() + "'";
        }
    }
}
