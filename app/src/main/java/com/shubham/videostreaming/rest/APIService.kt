package com.shubham.videostreaming.rest

import com.shubham.videostreaming.model.Response
import retrofit2.Call
import retrofit2.http.GET


interface APIService {
    @GET("RestController.php?view=insipiring_films")    //End Url
    fun fetchVideo(): Call<Response>
}