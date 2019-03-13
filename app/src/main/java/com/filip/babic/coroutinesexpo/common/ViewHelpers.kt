package com.filip.babic.coroutinesexpo.common

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

inline fun View.onClick(crossinline onClickAction: () -> Unit) {
    setOnClickListener { onClickAction() }
}

private val textWatchers = mutableMapOf<EditText, TextWatcher>()

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    val textWatcher = SimpleTextWatcher(onTextChanged)

    addTextChangedListener(textWatcher)
    textWatchers.put(this, textWatcher)
}

fun EditText.removeTextWatchers() {
    val boundWatcher = textWatchers[this] ?: return

    removeTextChangedListener(boundWatcher)
    textWatchers.remove(this)
}

class SimpleTextWatcher(inline val onTextChanged: (String) -> Unit) : TextWatcher {
    override fun afterTextChanged(p0: Editable?) = Unit
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val data = charSequence?.toString() ?: return

        onTextChanged(data)
    }
}
