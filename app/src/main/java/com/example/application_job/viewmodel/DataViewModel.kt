package com.example.application_job.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.application_job.MyItem

class DataViewModel : ViewModel{

    constructor(item: MyItem) : super()

    private val arraylistmutablelivedata = MutableLiveData<ArrayList<MyItem>>()

    private val arraylist : ArrayList<MyItem> = arrayListOf()

    fun getArrayList(): LiveData<ArrayList<MyItem>> {
        val all = arrayOf(
            "https://dummyimage.com/250x500/000/fff",
            "https://dummyimage.com/250x500/000/fff",
            "https://dummyimage.com/250x500/000/fff"
        )
        for (i in all.indices) {
            arraylist.add(MyItem("Title ${i+1}", "${all[i]}"))
        }

        arraylistmutablelivedata.value= arraylist

        return arraylistmutablelivedata
    }

}