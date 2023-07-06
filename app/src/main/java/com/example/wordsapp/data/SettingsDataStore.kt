package com.example.wordsapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"

//create instance of preference datastore
private val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREFERENCES_NAME)

class SettingsDataStore(context : Context) {
    private val IS_LINEAR_LAYOUT_MANAGER = booleanPreferencesKey("is_linear_layout_manager")
}