package com.example.newsapp.utill.extenctions

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.newsapp.R


fun ActionBar.setActionBar(
    context: Context,
    title: String,
    isHomeUpEnables: Boolean = false,
    isCenterTitle: Boolean = false,
    isGrayArrow: Boolean =false
) {
    val tv = TextView(context.applicationContext)

    // Create a LayoutParams for TextView
    val lp = ActionBar.LayoutParams(
        ActionBar.LayoutParams.WRAP_CONTENT, // Width of TextView
        ActionBar.LayoutParams.WRAP_CONTENT

    ) // Height of TextView

    if (isCenterTitle) {
        // Apply the layout parameters to TextView widget
        lp.gravity = Gravity.CENTER
    }

    if (!isCenterTitle) {
        lp.gravity = Gravity.START
    }

    // Set text to display in TextView
    tv.text = title // ActionBar title text

    //set font family
  //  tv.typeface = Typeface.DEFAULT_BOLD
    val myCustomFont: Typeface? = ResourcesCompat.getFont(context, R.font.nunito_bold)
    tv.typeface = myCustomFont


    //change text color
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryBlue))
    } else {
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryBlue))
    }
    tv.typeface

    // Set the TextView text size in dp
    // This will change the ActionBar title text size
    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)


    // Set the ActionBar display option
    this.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
    this.setCustomView(tv, lp)

   // this.elevation = 0.dpToPx()
    if (isHomeUpEnables) {
        this.setDisplayHomeAsUpEnabled(true)
        this.setDisplayShowHomeEnabled(true)
        if (isGrayArrow){
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }else {
            this.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }
}

