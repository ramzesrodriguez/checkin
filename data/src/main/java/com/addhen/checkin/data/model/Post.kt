/*
 * Copyright (c) 2015 - 2018 Henry Addo
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
 *
 */

package com.addhen.checkin.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.Date

data class Post(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "location") val location: Location,
    @ColumnInfo(name = "created") val created: Date,
    @ColumnInfo(name = "likes") val likes: Long,
    @ColumnInfo(name = "like") val like: Like,
    @ColumnInfo(name = "user") val user: User
) : Model
