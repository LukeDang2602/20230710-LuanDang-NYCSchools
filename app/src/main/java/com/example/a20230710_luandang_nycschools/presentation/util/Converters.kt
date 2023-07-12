package com.example.a20230710_luandang_nycschools.presentation.util

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.example.a20230710_luandang_nycschools.ui.theme.LinkBlue
import com.example.a20230710_luandang_nycschools.ui.theme.sp_14


fun getWebsiteString(link: String, context: Context) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(textDecoration = TextDecoration.Underline, color = LinkBlue, fontSize = sp_14)
    ) {
        append(link)
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = length - link.length,
            end = length
        )
    }
}

fun getPhoneString(number: String, context: Context) = buildAnnotatedString {
    withStyle(
        style = SpanStyle(textDecoration = TextDecoration.Underline, color = LinkBlue, fontSize = sp_14)) {
        append(number)
        addStringAnnotation(
            tag = "DIAL",
            annotation = number,
            start = length - number.length,
            end = length
        )
    }
}