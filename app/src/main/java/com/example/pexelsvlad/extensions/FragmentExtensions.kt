package com.example.pexelsvlad.extensions


import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pexelsvlad.R

fun Fragment.configureActionBar(
    title: String,
    visible: Boolean = true,
    hasBackButton: Boolean = true
) = (activity as? AppCompatActivity)?.supportActionBar?.run {
    if (visible) {
        show()
    } else {
        hide()
    }
    this.title = ""
    if (customView == null) {
        configureDefaultActionBar(this, title, hasBackButton)
    } else {
        configureCustomActionBar(customView, title, hasBackButton)
    }
}

private fun configureDefaultActionBar(actionBar: ActionBar, title: String, hasBackBottom: Boolean) =
    with(actionBar) {
        this.title = title
        setDisplayHomeAsUpEnabled(hasBackBottom)
        setHomeButtonEnabled(hasBackBottom)
        if (hasBackBottom) {
            setHomeAsUpIndicator(R.drawable.arrow_icon)
        } else {
            setHomeAsUpIndicator(null)
        }
    }

private fun Fragment.configureCustomActionBar(
    customView: View,
    title: String,
    hasBackBottom: Boolean
) {
    val titleView = customView.findViewById<TextView>(R.id.actionBarTitle)
    val buttonView = customView.findViewById<View>(R.id.backArrow)
    val backButtonViewGroup = customView.findViewById<ViewGroup>(R.id.backButton)
    val backButtonView = customView.findViewById<CardView>(R.id.backButtonCardView)
    buttonView.isInvisible = !hasBackBottom
    titleView.text = title
    if (!hasBackBottom){
       backButtonViewGroup.setBackgroundColor(resources.getColor(R.color.white))
        backButtonView.setCardElevation(0F)
    }
    buttonView.setOnClickListener {
        findNavController().popBackStack()
    }
}