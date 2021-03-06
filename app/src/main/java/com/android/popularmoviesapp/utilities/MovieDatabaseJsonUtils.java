package com.android.popularmoviesapp.utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.popularmoviesapp.data.MovieContract;
import com.android.popularmoviesapp.data.MovieData;
import com.android.popularmoviesapp.data.MovieDbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class MovieDatabaseJsonUtils {

    final static String TAG = MovieDatabaseJsonUtils.class.getSimpleName();
    final static String RESULTS = "results";
    final static String ORIGINAL_TITLE = "original_title";
    final static String OVERVIEW = "overview";
    final static String RELEASE_DATE = "release_date";
    final static String POSTER_PATH = "poster_path";
    final static String BACKDROP_PATH = "backdrop_path";
    final static String VOTE_AVERAGE = "vote_average";
    final static String MOVIE_ID = "id";
    final static String POPULARITY = "popularity";

    final static String MOVIE_TRAILER_NAME = "name";
    final static String MOVIE_TRAILER_ID = "id";
    final static String MOVIE_TRAILER_KEY = "key";
    final static String MOVIE_TRAILER_SITE = "site";
    final static String MOVIE_TRAILER_TYPE = "type";
    final static String MOVIE_TRAILER_COUNT = "count";

    final static String MOVIE_REVIEW_AUTHOR = "author";
    final static String MOVIE_REVIEW_CONTENT = "content";
    final static String MOVIE_REVIEW_URL = "url";

    //public static List<String> trailer_Keys = new ArrayList<String>();


    /*public static ContentValues getContentValueReviewData (Context context, String movieJsonStr)
            throws JSONException {

        JSONObject reviewData = new JSONObject(movieJsonStr);

        // check for an error
        //if (movieData.has())

        JSONArray results = reviewData.getJSONArray(RESULTS);
        //Log.d(TAG, results.getJSONObject(0).getString("key"));
        //List<String> trailerKeys = new ArrayList<String>();


        ContentValues reviewContentValues = new ContentValues();
        //results.length gets the amount of reviews....
        for(int i = 0; i < results.length(); i++){

            String author;
            String content;
            String url;

            // get current JSON object
             JSONObject currentReview = results.getJSONObject(i);

            //extract movie details from current JSON object
            author = currentReview.getString(MOVIE_REVIEW_AUTHOR);
            content = currentReview.getString(MOVIE_REVIEW_CONTENT);
            url = currentReview.getString(MOVIE_REVIEW_URL);

            Log.v(TAG, author);
            ContentValues reviewSpecifics = new ContentValues();
            reviewSpecifics.put(MovieContract.MovieEntry.COLUMN_REVIEW_AUTHOR, author);
            reviewSpecifics.put(MovieContract.MovieEntry.COLUMN_REVIEW_CONTENT, content);
            reviewSpecifics.put(MovieContract.MovieEntry.COLUMN_REVIEW_URL, url);

            reviewContentValues = reviewSpecifics;
        }

        return reviewContentValues;
    }*/

    /*public static ContentValues getContentValueTrailerData (Context context, String trailerJsonStr)
            throws JSONException {

        JSONObject trailerData = new JSONObject(trailerJsonStr);

        // check for an error
        //if (movieData.has())

        JSONArray results = trailerData.getJSONArray(RESULTS);
        //Log.d(TAG, results.getJSONObject(0).getString("key"));
        ArrayList<String> trailerKeys = new ArrayList<String>();


        ContentValues trailerContentValues = new ContentValues();

        for(int i = 0; i < results.length(); i++){

            String name;
            String site;
            String type;
            String key;
            String id;

            // get current JSON object
            JSONObject currentTrailer = results.getJSONObject(i);

            //extract movie details from current JSON object
            name = currentTrailer.getString(MOVIE_TRAILER_NAME);
            site = currentTrailer.getString(MOVIE_TRAILER_SITE);
            key = currentTrailer.getString(MOVIE_TRAILER_KEY);
            trailerKeys.add(key);
            Log.d(TAG, trailerKeys.get(i));
            id = currentTrailer.getString(MOVIE_TRAILER_ID);
            type = currentTrailer.getString(MOVIE_TRAILER_TYPE);

            Log.v(TAG, type);

            if(type.equals("Trailer")) {
                ContentValues trailerSpecifics = new ContentValues();
                trailerSpecifics.put(MovieContract.MovieEntry.COLUMN_TRAILER_NAME, name);
                trailerSpecifics.put(MovieContract.MovieEntry.COLUMN_TRAILER_KEY, key);
                trailerSpecifics.put(MovieContract.MovieEntry.COLUMN_TRAILER_SITE, site);
                trailerSpecifics.put(MovieContract.MovieEntry.COLUMN_TRAILER_ID, id);
                trailerSpecifics.put(MovieContract.MovieEntry.COLUMN_TRAILER_TYPE, type);

                trailerContentValues = trailerSpecifics;
            }
        }

        return trailerContentValues;
    }*/

    public static void getFavoriteContentValueData (Context context, MovieData data, boolean checkFavBool){
        SQLiteDatabase mDb;
        MovieDbHelper movieDbHelper = new MovieDbHelper(context);


        String original_title = data.getOriginal_title();
        String posterPath = data.getPoster_path();
        String backdropPath = data.getBackdrop_path();
        String overview = data.getOverview();
        String voteAverage = data.getVote_average();
        String releaseDate = data.getRelease_date();
        String id  = data.getMovie_id();
        String fav_bool = Boolean.toString(checkFavBool);
        Log.d(TAG, fav_bool);


        ContentValues favoriteData = new ContentValues();
        favoriteData.put(MovieContract.MovieEntry.COLUMN_TITLE, original_title);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, overview);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE,voteAverage);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, id);
        favoriteData.put(MovieContract.MovieEntry.COLUMN_FAVORITE_BOOL, fav_bool);

        mDb = movieDbHelper.getWritableDatabase();
        long rowCount = mDb.insert(MovieContract.MovieEntry.TABLE_NAME_MOVIE_MAIN, null, favoriteData);
    }

    public static ContentValues[] getContentValueMovieData (Context context, String movieJsonStr)
            throws JSONException {

        JSONObject moviesData
                = new JSONObject(movieJsonStr);

        // check for an error
        //if (movieData.has())

        JSONArray results = moviesData.getJSONArray(RESULTS);
        ContentValues[] movieContentValues = new ContentValues[results.length()];

        for(int i = 0; i < results.length(); i++){

            String original_title;
            String posterPath;
            String backdropPath;
            String overview;
            String voteAverage;
            String releaseDate;
            String id;
            // get current JSON object
            JSONObject currentMovie = results.getJSONObject(i);

            //extract movie details from current JSON object
            original_title = currentMovie.getString(ORIGINAL_TITLE);
            posterPath = currentMovie.getString(POSTER_PATH);
            backdropPath = currentMovie.getString(BACKDROP_PATH);
            overview = currentMovie.getString(OVERVIEW);
            voteAverage = currentMovie.getString(VOTE_AVERAGE);
            releaseDate = currentMovie.getString(RELEASE_DATE);
            id = currentMovie.getString(MOVIE_ID);

            ContentValues movieSpecifics = new ContentValues();
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_TITLE, original_title);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, posterPath);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, overview);
            movieSpecifics.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, id);

            movieContentValues[i] = movieSpecifics;
        }

        return movieContentValues;
    }

    public static ArrayList<MovieData> getMovieData(String json) throws JSONException {
        JSONObject movieData = new JSONObject(json);
        JSONArray results = movieData.getJSONArray(RESULTS);
        ArrayList<MovieData> moviesArray = new ArrayList<MovieData>();
        for(int i = 0; i < results.length(); i++){
            JSONObject currentMovie = results.getJSONObject(i);
            MovieData movieSpecifics = new MovieData(
                    currentMovie.getString(MOVIE_ID),
                    currentMovie.getString(ORIGINAL_TITLE),
                    currentMovie.getString(POSTER_PATH),
                    currentMovie.getString(BACKDROP_PATH),
                    currentMovie.getString(VOTE_AVERAGE),
                    currentMovie.getString(RELEASE_DATE),
                    currentMovie.getString(OVERVIEW)

            );
            moviesArray.add(movieSpecifics);
            //Log.v(TAG, movieArray.get(i).getPoster_path());
        }
        return moviesArray;
    }

    public static ArrayList<MovieData> getMovieTrailerData(String json) throws JSONException {
        JSONObject movieTrailerData = new JSONObject(json);
        JSONArray results = movieTrailerData.getJSONArray(RESULTS);
        ArrayList<MovieData> movieTrailerArray = new ArrayList<MovieData>();
        for(int i = 0; i < results.length(); i++){
            JSONObject currentMovie = results.getJSONObject(i);
            MovieData trailerData = new MovieData(
                    currentMovie.getString(MOVIE_TRAILER_NAME),
                    currentMovie.getString(MOVIE_TRAILER_ID),
                    currentMovie.getString(MOVIE_TRAILER_KEY),
                    currentMovie.getString(MOVIE_TRAILER_SITE),
                    currentMovie.getString(MOVIE_TRAILER_TYPE)
            );
            movieTrailerArray.add(trailerData);
            Log.v(TAG, movieTrailerArray.get(i).getTrailer_name());
        }
        return movieTrailerArray;
    }

    public static ArrayList<MovieData> getMovieReviewData(String json) throws JSONException {
        JSONObject movieReviewData = new JSONObject(json);
        JSONArray results = movieReviewData.getJSONArray(RESULTS);
        ArrayList<MovieData> movieReviewArray = new ArrayList<MovieData>();
        for(int i = 0; i < results.length(); i++){
            JSONObject currentMovie = results.getJSONObject(i);
            MovieData reviewData = new MovieData(
                    currentMovie.getString(MOVIE_REVIEW_AUTHOR),
                    currentMovie.getString(MOVIE_REVIEW_CONTENT),
                    currentMovie.getString(MOVIE_REVIEW_URL)
            );
            movieReviewArray.add(reviewData);
            Log.v(TAG, movieReviewArray.get(i).getReview_author());
        }
        return movieReviewArray;
    }
}
