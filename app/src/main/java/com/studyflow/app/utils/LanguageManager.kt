package com.studyflow.app.utils

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

object LanguageManager {

    fun setLanguage(languageCode: String) {

        val appLocale = LocaleListCompat.forLanguageTags(
            languageCode
        )

        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}