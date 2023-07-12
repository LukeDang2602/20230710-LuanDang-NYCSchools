package com.example.a20230710_luandang_nycschools.data.model

import com.google.gson.annotations.SerializedName

//High school entity
data class HighSchoolResponseEntity(
    @SerializedName("dbn")
    val dbn: String,

    @SerializedName("school_name")
    val schoolName: String,

    @SerializedName("overview_paragraph")
    val overviewParagraph: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("phone_number")
    val phoneNumber: String,

    @SerializedName("school_email")
    val schoolEmail: String,

    @SerializedName("website")
    val website: String,

    @SerializedName("total_students")
    val totalStudents: String,

)