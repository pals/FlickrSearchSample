package com.flickrdemo.utils

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi

class StringHelperUtil {

    companion object {
        @RequiresApi(Build.VERSION_CODES.N)
        fun getHTMLFormattedString(htmlStr: String): String {
            return Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_LEGACY).toString()
        }
    }

}