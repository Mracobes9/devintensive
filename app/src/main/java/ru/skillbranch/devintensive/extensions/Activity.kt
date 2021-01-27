package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.Display
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_main.*

fun Activity.hideKeyboard() {
    val inputMethodManager = this.applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.iv_bender.windowToken, 0)
}