package com.flickrdemo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class RecentSearchWord(
    @PrimaryKey(autoGenerate = true)
    var recentSearchWordId: Long = 0L,
    @ColumnInfo(name = "recent_search_keyword")
    var recentSearchKeyword: String = ""
)