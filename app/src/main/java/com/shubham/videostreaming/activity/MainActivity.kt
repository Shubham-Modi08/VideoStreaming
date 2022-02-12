package com.shubham.videostreaming.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.shubham.videostreaming.R
import com.shubham.videostreaming.adapter.ListAdapter
import com.shubham.videostreaming.model.ResponseItem
import com.shubham.videostreaming.rest.APIService
import com.shubham.videostreaming.rest.RestClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var mApiService: APIService? = null
    private var mAdapter: ListAdapter? = null
    private var mVideos: MutableList<ResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mApiService = RestClient.client.create(APIService::class.java)
        listRecyclerView!!.layoutManager = LinearLayoutManager(this)
        fetchVideoList()
    }

    private fun fetchVideoList() {
        val call = mApiService!!.fetchVideo();
        call.enqueue(object : Callback<com.shubham.videostreaming.model.Response> {

            override fun onResponse(
                call: Call<com.shubham.videostreaming.model.Response>,
                response: Response<com.shubham.videostreaming.model.Response>
            ) {
                mVideos = response.body()!!
                mAdapter = ListAdapter(mVideos, lifecycle)
                listRecyclerView!!.adapter = mAdapter


            }

            override fun onFailure(
                call: Call<com.shubham.videostreaming.model.Response>,
                t: Throwable
            ) {
                Log.e(TAG, "Got error : " + t.localizedMessage)
            }
        })
    }
}