package com.trulden.roomwordssample.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static WordRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null) {
                    // create db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract WordDao wordDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mDao;
        String[] words = {"Dolphin", "Crocodile", "Cobra"};

        PopulateDbAsync(WordRoomDatabase db){
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for(String word : words){
                mDao.insert(new Word(word));
            }

            return null;
        }

    }
}
