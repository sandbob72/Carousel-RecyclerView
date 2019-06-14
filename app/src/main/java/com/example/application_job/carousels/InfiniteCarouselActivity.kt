package com.example.application_job.carousels

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Scroller
import com.example.application_job.*
import com.example.application_job.transformer.CirclePagerIndicatorDecoration
import com.example.application_job.transformer.InfiniteCarouselTransformer
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter
import kotlinx.android.synthetic.main.activity_infinite_scroll_carousel.*
import me.relex.circleindicator.CircleIndicator
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator

class InfiniteCarouselActivity : AppCompatActivity(),DiscreteScrollView.OnItemChangedListener<MyAdapter.MyViewHolder>
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
        supportActionBar?.let {
            supportActionBar?.title = "Infinite Carousel"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val I1 = findViewById<DiscreteScrollView>(R.id.infinite_carousel_1)
        mAdapter = MyAdapter(arrayList)

//        // Infinite scroll
//        mInfiniteScrollWrapper = InfiniteScrollAdapter.wrap(mAdapter)
//        I1.adapter = mInfiniteScrollWrapper
//
//        // Item transformer
//        I1.setItemTransformer(InfiniteCarouselTransformer())
//
//        // Item change listener
//        I1.addOnItemChangedListener(this)

        //-----------------------------------------------------------------------//

        mInfiniteScrollWrapper = InfiniteScrollAdapter.wrap(mAdapter)
        infinite_carousel_2.adapter = mInfiniteScrollWrapper

        // Item transformer
        infinite_carousel_2.setItemTransformer(InfiniteCarouselTransformer())

        // Item change listener
        infinite_carousel_2.addOnItemChangedListener(this)

        // data
        loadData()

        //-----------------------------------------------------------------------------------------------------

        //recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.layoutManager = CenterZoomLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler.adapter = MyAdapter(arrayList)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycler)


        pageIndicator.attachTo(recycler)
        indicator.attachToRecyclerView(recycler)

        recycler.addItemDecoration(CirclePagerIndicatorDecoration())

//        val pageIndicators = DiscreteScrollViewIndicator.Builder(infinite_carousel_2)
//        infinite_carousel_2.addItemDecoration(pageIndicators)








        //-----------------------------------------------------------------------------------------------------

    }
    val all = arrayOf("https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenArrow_5c4915494b4039.94050514.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_GreenLantern_20190116_5c3fc8c14ceda8.50076512.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_Batman_20190116_5c3fc4b40faec2.47318964.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_Aquaman_5c411a95e71072.35445903.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_Flash_20190116_5c3fcaaa18f0e8.03668117.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_CaptainBoomerang_5c47c7697e4c81.97961521.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_Bizarro_5c4114e7bebcd5.79694855.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_HarleyQuinn_5c4a3e75812334.21707976.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_Joker_5c4a42b7ef2091.76638294.jpg",
        "https://www.dccomics.com/sites/default/files/styles/character_thumb_160x160/public/Char_Profile_CaptainCold_5c47c824a31862.57032666.jpg")

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