package com.example.application_job.carousels

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class CenterZoomLayoutManager(context:Context, orientation : Int, reverseLayout: Boolean) : LinearLayoutManager(context,orientation, reverseLayout) {

    val mShrinkAmount : Float = 0.15f
    val mShrinkDistance : Float = 0.9f

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == VERTICAL)
        {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            val midpoint = height / 2f
            val d0: Float = 0f
            val d1: Float = mShrinkDistance * midpoint
            val s0: Float = 1f
            val s1: Float = 1f - mShrinkAmount
            for (i in 0 until childCount)
            {
                val child:View = getChildAt(i)!!
                val childMidpoint =
                    (getDecoratedBottom(child) + getDecoratedTop(child)) / 2f
                val d: Float = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.setScaleX(scale)
                child.setScaleY(scale)
            }
            return scrolled
        }
        else
        {
            return 0
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        var orientation = orientation
        if (orientation == HORIZONTAL)
        {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            val midpoint = width / 2f
            val d0: Float = 0f
            val d1: Float = mShrinkDistance * midpoint
            val s0: Float = 1f
            val s1: Float = 1f - mShrinkAmount
            for (i in 0 until childCount)
            {
                val child = getChildAt(i)!!
                val childMidpoint =
                    (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                val d: Float = Math.min(d1, Math.abs(midpoint - childMidpoint))
                val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                child.setScaleX(scale)
                child.setScaleY(scale)
            }
            return scrolled
        }
        else
        {
            return 0
        }
    }

}