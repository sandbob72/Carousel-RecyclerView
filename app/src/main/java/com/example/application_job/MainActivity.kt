package com.example.application_job

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.application_job.carousels.InfiniteCarouselActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_infinite_carousel.setOnClickListener {
            startActivity(Intent(this, InfiniteCarouselActivity::class.java))
        }

    }
}
