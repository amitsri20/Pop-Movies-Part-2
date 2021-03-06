package com.android.popularmoviesapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "movie_data.db";

    private static final int DATABASE_VERSION = 24;

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //create table
        final String SQL_CREATE_MOVIE_MAIN_TABLE =
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME_MOVIE_MAIN + " (" +
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL PRIMARY KEY, " +
                        MovieContract.MovieEntry.COLUMN_TITLE + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
                        MovieContract.MovieEntry.COLUMN_FAVORITE_BOOL + " TEXT DEFAULT 'false', " +
                        " UNIQUE (" + MovieContract.MovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE);";


        //execute SQL w/ execSQL method of SQL database obj
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_MAIN_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
       sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME_MOVIE_MAIN);
       onCreate(sqLiteDatabase);
    }
}
