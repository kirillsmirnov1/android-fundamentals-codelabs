package com.trulden.databaseexample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface WordDao {

    // The conflict strategy defines what happens,
    // if there is an existing entry.
    // The default action is ABORT.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    // Update multiple entries with one call.
    @Update
    public void updateWords(Word... words);

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM word_table")
    void deleteAll();

    // Simple query without parameters that returns values.
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();

    // Query with parameter that returns a specific word or words.
    @Query("SELECT * FROM word_table WHERE word LIKE :word ")
    public List<Word> findWord(String word);
}
