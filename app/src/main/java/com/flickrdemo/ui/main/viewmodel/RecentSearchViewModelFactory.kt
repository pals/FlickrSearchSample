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

package com.flickrdemo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flickrdemo.data.api.ApiHelper
import com.flickrdemo.data.repository.Repository
import com.flickrdemo.database.RecentSearchDatabaseDao
import com.flickrdemo.utils.ResponseHandler

@Suppress("UNCHECKED_CAST")
class RecentSearchViewModelFactory(

    private val apiHelper: ApiHelper,
    private val database: RecentSearchDatabaseDao,
    private val responseHandler: ResponseHandler
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecentSearchViewModel::class.java)) {


            return RecentSearchViewModel(Repository(apiHelper, database, responseHandler)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

