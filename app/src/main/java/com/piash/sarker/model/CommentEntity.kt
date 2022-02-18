package com.piash.sarker.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CommentEntity(
    val message: String,
    var isSynced : Boolean? = false,
    val parentId: String = "e0000b7c-185f-4af0-9e3b-c1dcc6a22757",
    val type: String = "comment",
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
)