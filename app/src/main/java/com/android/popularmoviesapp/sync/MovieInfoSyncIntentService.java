package com.android.popularmoviesapp.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.popularmoviesapp.data.MovieContract;

public class MovieInfoSyncIntentService extends IntentService{

    public MovieInfoSyncIntentService() {
        super("MovieSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        MovieInfo.syncMovieInfo(this);
    }
}
