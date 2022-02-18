package com.piash.sarker.repository

import com.piash.sarker.database.DataStorageDao
import com.piash.sarker.model.Movie
import com.piash.sarker.api.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: DataStorageDao
) {


    fun getMovieById(id: String): Flow<Movie?> {
        return dao.getMovieById(id)
    }

    suspend fun updateMovie(movie: Movie): Int {
        return dao.updateMovieInformation(movie)
    }


}