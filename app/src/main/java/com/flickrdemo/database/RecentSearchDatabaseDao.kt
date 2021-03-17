/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flickrdemo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecentSearchDatabaseDao {

    @Insert
    suspend fun insert(recentSearchWord: RecentSearchWord)

    @Query("SELECT * from recent_searches WHERE recentSearchWordId = :key")
    suspend fun get(key: Long): RecentSearchWord

    @Query("DELETE FROM recent_searches WHERE recentSearchWordId IN (SELECT recentSearchWordId FROM recent_searches ORDER BY recentSearchWordId ASC LIMIT 1)")
    suspend fun deleteOldestKeyword()

    @Query("SELECT * FROM recent_searches ORDER BY recentSearchWordId DESC")
    fun getRecentFiveSearches(): LiveData<List<RecentSearchWord>>

    @Query("SELECT * FROM recent_searches ORDER BY recentSearchWordId DESC LIMIT 1")
    suspend fun getRecentSearchKeyword(): RecentSearchWord?

    @Query("DELETE FROM recent_searches")
    suspend fun clear()
}
