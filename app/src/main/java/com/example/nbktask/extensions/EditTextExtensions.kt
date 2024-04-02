package com.example.nbktask.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.onEditTextChanged(textChangedListener: (String)->Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // This method is called before the text is changed.
        }

        override fun onTextChanged(char: CharSequence?, start: Int, before: Int, count: Int) {
            textChangedListener.invoke(char.toString())
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}