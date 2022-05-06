package com.rasaapps.raul.ceibajsonkotlin

class UserModal {

    private var userName: String? = null
    private var userEmail: String? = null
    private var userPhone: String? = null
    private var userPosition: String? = null

    constructor(userName: String?, userEmail: String?, userPhone: String?) {
        this.userName = userName
        this.userEmail = userEmail
        this.userPhone = userPhone
    }

    // Update with improved search filter link
    constructor(userName: String?, userEmail: String?, userPhone: String?, userPosition: String?){
        this.userName = userName
        this.userEmail = userEmail
        this.userPhone = userPhone
        this.userPosition = userPosition
    }

    fun getUserName(): String? {
        return userName
    }

    fun getUserEmail(): String? {
        return userEmail
    }

    fun getUserPhone(): String? {
        return userPhone
    }

    fun getUserPosition(): String?{
        return userPosition
    }




}