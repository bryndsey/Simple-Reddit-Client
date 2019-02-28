package com.bryndsey.simpleredditclient.ui.redditpostdetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ContentView
import androidx.fragment.app.Fragment
import com.bryndsey.simpleredditclient.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.image_display.*

@ContentView(R.layout.image_display)
class UrlImageDisplayFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = arguments?.getString("imageUrl")

        if (imageUrl == null) {
            Toast.makeText(context, "Invalid image URL", Toast.LENGTH_LONG).show()
        } else {
            Glide.with(this)
                    .load(imageUrl)
                    .fitCenter()
                    .into(imageDisplay)

        }
    }
}