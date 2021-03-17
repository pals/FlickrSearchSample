package com.flickrdemo

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.flickrdemo.database.FlickrDemoDatabase
import com.flickrdemo.database.RecentSearchDatabaseDao
import com.flickrdemo.database.RecentSearchWord
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest {

    private lateinit var recentSearchDatabaseDao: RecentSearchDatabaseDao
    private lateinit var db: FlickrDemoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FlickrDemoDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        recentSearchDatabaseDao = db.recentSearchDatabaseDao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertRecentSearchTest() = runBlocking {
        val recentSearchWord = RecentSearchWord()
        recentSearchWord.recentSearchKeyword = "RoomTest"
        recentSearchDatabaseDao.insert(recentSearchWord)


    }
}