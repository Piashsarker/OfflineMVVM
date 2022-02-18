package com.piash.sarker.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class User(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("isVerified")
	val isVerified: Boolean? = null,

	@field:SerializedName("displayName")
	val displayName: String? = null,

	@field:SerializedName("bio")
	val bio: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("artistId")
	val artistId: Any? = null,

	@field:SerializedName("isOnboardingComplete")
	val isOnboardingComplete: Boolean? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("dob")
	val dob: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tncAccepted")
	val tncAccepted: Boolean? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Aggregations(

	@field:SerializedName("dislikes")
	val dislikes: Int? = null,

	@field:SerializedName("likes")
	val likes: Int? = null
)

data class DataItem(

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("isActive")
	val isActive: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("parentId")
	val parentId: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("replyCount")
	val replyCount: Int? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Meta(

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null,

	@field:SerializedName("userActions")
	val userActions: List<UserActionsItem?>? = null,

	@field:SerializedName("aggregations")
	val aggregations: Aggregations? = null,

	@field:SerializedName("isReported")
	val isReported: Boolean? = null
)

data class UserActionsItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("metadata")
	val metadata: Any? = null,

	@field:SerializedName("actionValue")
	val actionValue: String? = null,

	@field:SerializedName("entityType")
	val entityType: String? = null,

	@field:SerializedName("entitySubType")
	val entitySubType: String? = null,

	@field:SerializedName("action")
	val action: String? = null,

	@field:SerializedName("entityId")
	val entityId: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
