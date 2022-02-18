package com.piash.sarker.repository

import android.content.Context
import com.piash.sarker.Utils
import com.piash.sarker.api.ApiService
import com.piash.sarker.api.Resource
import com.piash.sarker.api.Status
import com.piash.sarker.database.DataStorageDao
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.CommentResponse
import com.piash.sarker.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val apiService: ApiService,
    private val dataStorageDao: DataStorageDao
) {
    @Inject
    lateinit var contex: Context

    suspend fun getImageList(): Flow<Resource<List<Movie>?>> {
        return flow {
            emit(getImageFromCache())
            emit(Resource.loading(null))
            if (Utils.isNetworkAvailable(contex)) {
                val response = apiService.getPhotosList()
                if (response.isSuccessful) {
                    response.body().let {
                        dataStorageDao.deleteAll()
                        it!!.results?.let { it1 ->
                            dataStorageDao.insertPhotos(it1)
                            emit(Resource.success(it.results))
                        }
                    }
                } else {
                    emit(Resource.error(null, "Error"))
                }
            } else {
                emit(Resource.error(null, "Error"))
            }

        }.flowOn(Dispatchers.IO)


    }

    private suspend fun getImageFromCache(): Resource<List<Movie>> =
        Resource(Status.SUCCESS, dataStorageDao.getPhotos(), "")


    suspend fun getCommentFromServer(): Response<CommentResponse> {
        return apiService.getComment()
    }

    fun getAllCommentFromDatabase(): Resource<List<CommentEntity>> {
        return Resource(Status.SUCCESS, dataStorageDao.getAllComments(), "")
    }

    suspend fun insertCommentDatabase(comment: CommentEntity) {
        return dataStorageDao.insertComment(comment)
    }

    suspend fun getAllCommentForSync(): List<CommentEntity> {
        return dataStorageDao.getCommentWithFlag(false)
    }

    suspend fun updateCommentInServer(comment: CommentEntity): Response<CommentEntity> {

        return apiService.updateComment(comment)
    }

    suspend fun synceCommentInServer() {
        if (Utils.isNetworkAvailable(contex)) {
            val list = dataStorageDao.getCommentWithFlag(false)
            for (item in list) {
                val response = apiService.updateComment(item)
                if (response.isSuccessful) {
                    item.isSynced = true
                    dataStorageDao.insertComment(item)
                }
            }
        }
    }

    suspend fun getCommentList(): Flow<Resource<List<CommentEntity>>> {
        return flow {
            emit(Resource.loading(null))
            if (Utils.isNetworkAvailable(contex)) {
                val response = apiService.getComment()
                if (response.isSuccessful) {
                    response.body().let {
                        dataStorageDao.deleteAll()
                        it?.let {
                            val list: MutableList<CommentEntity> = mutableListOf()
                            for (item in it.data!!) {
                                list.add(CommentEntity(item?.message!!, true))
                            }
                            dataStorageDao.deleteAllComments()
                            dataStorageDao.insertAllComment(list)
                            emit(Resource.success(list))
                        }
                    }
                } else {
                    emit(getAllCommentFromDatabase())
                }
            } else {
                emit(getAllCommentFromDatabase())
            }

        }.flowOn(Dispatchers.IO)
    }


}