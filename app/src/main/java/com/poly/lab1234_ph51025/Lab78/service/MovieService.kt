package com.poly.lab1234_ph51025.service

import com.poly.lab1234_ph51025.Lab78.model.MovieRequest
import com.poly.lab1234_ph51025.Lab78.model.StatusResponse
import com.poly.lab1234_ph51025.response.MovieResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MovieService {
    @GET("films")
    suspend fun getListFilms(): Response<List<MovieResponse>>

    @GET("films/{id}")
    suspend fun getFilmDetail(@Path("id") id: String): Response<MovieResponse>

    @POST("films")
    suspend fun addFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>

    @PUT("films/{id}")
    suspend fun updateFilm(
        @Path("id") id: String,
        @Body filmRequest: MovieRequest
    ): Response<StatusResponse>

    @DELETE("films/{id}")
    suspend fun deleteFilm(@Path("id") id: String): Response<StatusResponse>
}
