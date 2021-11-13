package com.omelette.android.api

import com.omelette.android.bean.NewsBean
import com.omelette.android.bean.PicsBean
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
interface OmeletService {
    @GET("/")
    suspend fun searchPlaces(@Query("oxwlxojflwblxbsapi")  api:String, @Query("page")  page:Int): PicsBean

    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&custom_fields=thumb_c,views&dev=1")
    suspend fun searchNews( @Query("page")  page:Int): NewsBean

    companion object {
        private const val BASE_URL = "http://i.jandan.net/"

        fun create(): OmeletService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(OmeletService::class.java)
        }
    }
}
