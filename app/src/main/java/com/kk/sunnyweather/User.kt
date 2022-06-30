package com.kk.sunnyweather

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class User @Inject constructor(@ApplicationContext context: Context) {
    val context = context
}