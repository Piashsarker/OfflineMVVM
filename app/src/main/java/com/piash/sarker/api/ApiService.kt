package com.piash.sarker.api

import com.piash.sarker.API_KEY
import com.piash.sarker.API_TOKEN
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.CommentResponse
import com.piash.sarker.model.MovieDesc
import com.piash.sarker.model.TrendingMovieResponse
import org.w3c.dom.Comment
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("/3/trending/movie/week?api_key=${API_KEY}")
    suspend fun getPhotosList(): Response<TrendingMovieResponse>

    @GET("/3/movie/{movie_id}?api_key=${API_KEY}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDesc>


    /**
     * Get comments : GET https://api.dev.noice.id/community-api/v1/post/e0000b7c-185f-4af0-9e3b-c1dcc6a22757?limit=10&page=1
    Submit comment : POST https://api.dev.noice.id/community-api/v1/post
    {
    "message": "test",
    "parentId": "e0000b7c-185f-4af0-9e3b-c1dcc6a22757",
    "type": "comment"
    }
    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InNpbV9rZXlfdjEifQ.eyJpZCI6IjQ5YjU0MWViLWJjNWUtNDkwOS05YTdiLTViMzMyOWNkMjNkMyIsInJvbGVzIjpbInVzZXIiXSwiaWF0IjoxNjQ1MTgxNDQ0LCJleHAiOjE2NDUxODUwNDR9.ZPzAmBcvZ6K3ROh7gNPJuJXED_Os2Q7s2HVZbk86HmQ
     */

    @POST("community-api/v1/post")
    suspend fun updateComment(@Body comment: CommentEntity, @Header("Authorization") token: String = API_TOKEN): Response<CommentEntity>

    @GET("community-api/v1/post/e0000b7c-185f-4af0-9e3b-c1dcc6a22757?limit=10&page=1")
    suspend fun getComment(@Header("Authorization") token: String=  API_TOKEN): Response<CommentResponse>

}