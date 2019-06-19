package com.example.application_job.carousels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.util.Log
import com.example.application_job.carousels.CenterZoomLayoutManager
import com.example.application_job.MyAdapter
import com.example.application_job.MyItem
import com.example.application_job.R
import com.example.application_job.transformer.CirclePagerIndicatorDecoration
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import kotlinx.android.synthetic.main.activity_infinite_scroll_carousel.*


class InfiniteCarouselActivity : AppCompatActivity(), DiscreteScrollView.OnItemChangedListener<MyAdapter.MyViewHolder>
    {
    override fun onCurrentItemChanged(viewHolder: MyAdapter.MyViewHolder?, adapterPosition: Int) {
        val realPosition = mInfiniteScrollWrapper.realCurrentPosition
        log("onCurrentItemChanged $realPosition")
    }

    private lateinit var mInfiniteScrollWrapper: InfiniteScrollAdapter<*>
    private lateinit var mAdapter: MyAdapter
    private var arrayList: ArrayList<MyItem> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infinite_scroll_carousel)


//        var dataViewModel: DataViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
//        dataViewModel.getArrayList().observe(this, Observer {
//            mAdapter = MyAdapter(it!!)
//            recycler.layoutManager = CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
//            recycler.adapter = MyAdapter(it)
//
//            val snapHelper: SnapHelper = LinearSnapHelper()
//            snapHelper.attachToRecyclerView(recycler)
//
//            pageIndicator.attachTo(recycler)
//            indicator.attachToRecyclerView(recycler)
//            recycler.addItemDecoration(CirclePagerIndicatorDecoration())
//
//        })

        mAdapter = MyAdapter(arrayList)


        //DiscreteScrollView--------------------------------------------------------------------------------//
//        // Infinite scroll
//        mInfiniteScrollWrapper = InfiniteScrollAdapter.wrap(mAdapter)
//        infinite_carousel_1.adapter = mInfiniteScrollWrapper
//
//        // Item transformer
//        infinite_carousel_1.setItemTransformer(InfiniteCarouselTransformer())
//
//        // Item change listener
//        infinite_carousel_1.addOnItemChangedListener(this)
        //DiscreteScrollView--------------------------------------------------------------------------------//

        // data
        loadData()


        //RecyclerView-------------------------------------------------------------------------------------------------//
        recycler.layoutManager = CenterZoomLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = MyAdapter(arrayList)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler)
        //------------------------------------------------------------------------------------------------------------//


        //RecyclerView Indicator Library----------------------------------------------------------------------//
        pageIndicator.attachTo(recycler)
        indicator.attachToRecyclerView(recycler)
        recycler.addItemDecoration(CirclePagerIndicatorDecoration())
        //----------------------------------------------------------------------------------------------------//

        //DiscreteScrollViewIndicator Indicator use DiscreteScrollViewIndacator.java "No Complete"
//        val pageIndicators = DiscreteScrollViewIndicator.Builder(infinite_carousel_1)
//        indicatorDiscreteScrollView.addItemDecoration(pageIndicators)
        //-----------------------------------------------------------------------------------------------------

    }
    val all = arrayOf("https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/250x500/000/fff",
        "https://dummyimage.com/600x400/000/fff",
        "https://dummyimage.com/600x400/000/fff",
        "https://dummyimage.com/600x400/000/fff")

    private fun loadData() {
        log("Loading data...")
        for (i in all.indices) {
            arrayList.add(MyItem("Title ${i+1}", "${all[i]}"))
        }
        mAdapter.notifyDataSetChanged()
    }

    private fun log(message: String) {
        Log.d("BackgroundToForeground", message)
    }

}