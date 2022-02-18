package com.piash.sarker.ui

import androidx.lifecycle.*
import com.piash.sarker.model.Movie
import com.piash.sarker.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel() {

    var movieId: String = saveStateHandle.get<String>("movieId")!!
    val movieData: LiveData<Movie?> = repository.getMovieById(movieId).asLiveData()


    init {
        getPopularityUpdate()
    }


    fun getPopularityUpdate() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                movieData.value?.let {
                    val popurality = (it.popularity + 100).toInt()
                    repository.updateMovie(
                        Movie(
                            movieId.toInt(),
                            it.title,
                            it.overview,
                            popurality.toDouble(),
                            it.poster_path
                        )
                    )
                }

            }

        }


    }

}