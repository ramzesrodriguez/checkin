package com.addhen.checkin.util

import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.DefaultDispatcher
import kotlinx.coroutines.experimental.android.UI

data class CoroutineDispatchers(
    val io: CoroutineDispatcher = DefaultDispatcher,
    val computation: CoroutineDispatcher = DefaultDispatcher,
    val main: CoroutineDispatcher = UI
)
