package com.piash.sarker.database

import androidx.room.*
import com.piash.sarker.model.CommentEntity
import com.piash.sarker.model.Movie
import kotlinx.coroutines.flow.Flow


@Dao
interface DataStorageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<Movie>)

    @Query("SELECT * FROM movie")
    suspend fun getPhotos(): List<Movie>?

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getMovieById(id: String): Flow<Movie?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovieInformation(movie: Movie): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllComment(comment: List<CommentEntity>)

    @Query("SELECT * FROM commententity WHERE isSynced = :flag")
    suspend fun getCommentWithFlag(flag: Boolean): List<CommentEntity>

    @Query("SELECT * FROM commententity")
    fun getAllComments(): List<CommentEntity>

    @Query("DELETE FROM commententity")
    suspend fun deleteAllComments()

}