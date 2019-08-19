package com.trulden.roomwordssample.database;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
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

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{

        private final WordDao mDao;
        String[] words = {"Dolphin", "Crocodile", "Cobra"};

        PopulateDBAsync(WordRoomDatabase db){
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if(mDao.getAnyWord().length<1) {
                for (String word : words) {
                    mDao.insert(new Word(word));
                }
            }

            return null;
        }

    }
}
