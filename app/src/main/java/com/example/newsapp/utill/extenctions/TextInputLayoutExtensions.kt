package com.example.newsapp.utill.extenctions

import android.content.Context
import com.example.newsapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextInputEditText.validateInput(
    context: Context,
    textInputLayout: TextInputLayout,
    validator: (String) -> Boolean
): Boolean {
    if (this.getStringTrim().isNotEmpty()) {
        if (validator(this.getStringTrim())) {
            textInputLayout.error = null
            this.setBackgroundResource(R.drawable.bg_white_edit_text)
            textInputLayout.errorIconDrawable = null
        } else {
            textInputLayout.error = " "
            this.setBackgroundResource(R.drawable.bg_edit_text_error)
        }
    } else {
        textInputLayout.error = " "
        this.setBackgroundResource(R.drawable.bg_edit_text_error)
    }


    return validator(this.getStringTrim()) && this.getStringTrim().isNotEmpty()
}