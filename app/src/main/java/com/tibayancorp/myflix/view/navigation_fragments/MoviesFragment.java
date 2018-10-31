package com.tibayancorp.myflix.view.navigation_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tibayancorp.myflix.R;
import com.tibayancorp.myflix.view.navigation_fragments.adapter.MovieListRecyclerViewAdapter;
import com.tibayancorp.myflix.view.navigation_fragments.dummy.DummyContent;
import com.tibayancorp.myflix.view.navigation_fragments.dummy.DummyContent.DummyItem;
import com.tibayancorp.myflix.view_model.MovieListViewModel;
import com.tibayancorp.myflix.model.Entities.Movie;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MoviesFragment extends Fragment {
    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerView;
    private MovieListRecyclerViewAdapter adapter;
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private String initialList = "Top Rated";

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MoviesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        // Initialize the first list of movies that will show in this fragment.
        movieListViewModel.onSelectedFilter(initialList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.movie_filters, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    /** From Menu Android Documentation for using android:onClick
     *      Warning: If you obfuscate your code using ProGuard (or a similar tool),
     *      be sure to exclude the method you specify in this attribute from renaming, because it can break the functionality.
     *      https://developer.android.com/guide/topics/resources/menu-resource#java
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view.findViewById(R.id.movieRecyclerView);
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MovieListRecyclerViewAdapter(movieListViewModel.getMovieList(), mListener);
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    public void onItemClicked(MenuItem item) {
        movieListViewModel.onSelectedFilter(item.getTitle().toString());
        if(adapter == null){
            adapter = new MovieListRecyclerViewAdapter(movieListViewModel.getMovieList(), mListener);
        } else if (adapter.getItemCount() > 0){
            adapter.clearAll();
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Movie movieItem);
    }
}
