package com.android.popularmoviesapp.data;

import android.net.Uri;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class MovieContract {
    /** CHANGE THIS TO FAVORITES SAVED.
     * USE MOVIE DATA CLASS TO OBTAIN AND DISPLAY ALL INFO FOR MAINLIST OF MOVIES
     */

    public static final String CONTENT_AUTHORITY = "com.android.popularmoviesapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIES = "movies";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_MOVIES)
                .build();

        public static Uri buildFavoriteMovieUri(String movie_id){
            return CONTENT_URI.buildUpon()
                    .appendPath(movie_id)
                    .build();
        }

        public static final String TABLE_NAME_MOVIE_MAIN = "movie_data";
        public static final String TABLE_NAME_FAVORITES = "favorites_data";

        public static final String COLUMN_TITLE = "original_title";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_FAVORITE_BOOL = "favorite";
    }
}
