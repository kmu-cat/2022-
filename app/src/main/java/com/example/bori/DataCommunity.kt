package com.example.bori

data class DataCommunity (
    var docId: String = "",
//    var email: String? = null,
//    var content: String? = null,
    var date: String? = null, // 표시는 안하고 DB 정렬하려고
    var comment: String= "",
    var buccat: String= "",
    var username: String= "",
)