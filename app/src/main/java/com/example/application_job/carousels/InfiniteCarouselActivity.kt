package com.example.application_job.carousels

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.rgb
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.util.Log
import com.bumptech.glide.load.engine.Resource
import com.example.application_job.DiscreteScrollViewIndicator
import com.example.application_job.carousels.CenterZoomLayoutManager
import com.example.application_job.MyAdapter
import com.example.application_job.MyItem
import com.example.application_job.R
import com.example.application_job.transformer.CirclePagerIndicatorDecoration
import com.example.application_job.transformer.InfiniteCarouselTransformer
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import kotlinx.android.synthetic.main.activity_infinite_scroll_carousel.*


class InfiniteCarouselActivity : AppCompatActivity()
    {
    private lateinit var mAdapter: MyAdapter
    private var arrayList: ArrayList<MyItem> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infinite_scroll_carousel)

        mAdapter = MyAdapter(arrayList)
        // data
        loadData()


        //RecyclerView-------------------------------------------------------------------------------------------------//
        recycler.layoutManager = CenterZoomLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = MyAdapter(arrayList)

        recycler2.layoutManager = CenterZoomLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recycler2.adapter = MyAdapter(arrayList)



        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler)
        snapHelper.attachToRecyclerView(recycler2)
        //------------------------------------------------------------------------------------------------------------//


        //RecyclerView Indicator Library----------------------------------------------------------------------//
        pageIndicator.attachTo(recycler)
        indicator.attachToRecyclerView(recycler)
        recycler.addItemDecoration(CirclePagerIndicatorDecoration())

        pageIndicator2.attachTo(recycler2)
        indicator2.attachToRecyclerView(recycler2)
        recycler2.addItemDecoration(CirclePagerIndicatorDecoration())
        //----------------------------------------------------------------------------------------------------//

    }
    val all = arrayOf("https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/600x400/000/fff",
        "https://dummyimage.com/600x400/000/fff",
        "https://dummyimage.com/600x400/000/fff")

    private fun loadData() {
        for (i in all.indices) {
            arrayList.add(MyItem("Title ${i+1}", "${all[i]}"))
        }
        mAdapter.notifyDataSetChanged()
    }
}