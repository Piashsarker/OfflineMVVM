package com.piash.sarker.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piash.sarker.Utils
import com.piash.sarker.api.Resource
import com.piash.sarker.api.Status
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.Movie
import com.piash.sarker.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) :
    ViewModel() {
    private var _helloData = MutableLiveData<String>()
    var helloData: LiveData<String> = _helloData
    val photos = MutableLiveData<Resource<List<Movie>?>>()

    val comments = MutableLiveData<Resource<List<CommentEntity>>>()
    val errorCommetn = MutableLiveData<String>()


    init {
        _helloData.value = "Live Data Response"
        getImageList()
        getCommentData()
        synceComment()
    }

    private fun synceComment() {
        viewModelScope.launch {
            repository.synceCommentInServer()
        }
    }


    fun getImageList() {
        photos.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            repository.getImageList()
                .catch { exception ->
                    run {
                        Resource.error(
                            null,
                            exception.message.toString()
                        )
                    }
                }
                .collect {
                    photos.postValue(it)
                }
        }
    }

    fun getCommentData() {
        comments.postValue(Resource(Status.LOADING, null, "Loading"))
        viewModelScope.launch {
            repository.getCommentList().collect {
                comments.postValue(it)
            }
        }
    }

    fun addComment(context: Context, commentEntity: CommentEntity) {
        viewModelScope.launch {
            if (Utils.isNetworkAvailable(context)) {
                val response =
                    repository.updateCommentInServer(CommentEntity(commentEntity.message, null))
                if (response.isSuccessful) {
                    repository.insertCommentDatabase(CommentEntity(commentEntity.message, true))
                } else {
                    repository.insertCommentDatabase(CommentEntity(commentEntity.message))
                }
                getCommentData()
            } else {
                repository.insertCommentDatabase(commentEntity)
                getCommentData()
            }
        }

    }


}
