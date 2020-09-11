package com.hicoding.smkpubmak.views.utils

import android.content.Context
import com.hicoding.smkpubmak.views.activity.signup.ResponseSaveUser
import com.iamhabib.easy_preference.EasyPreference

object AppPreferences {

    fun saveUser(context: Context, response: ResponseSaveUser) {
        EasyPreference.with(context)
            .addObject("user", response)
            .save()
    }

    fun getUser(context: Context) : ResponseSaveUser? {
        return EasyPreference.with(context).getObject("user", ResponseSaveUser::class.java)
    }

    fun removeUser(context: Context){
        EasyPreference.with(context).remove("user").save()
    }
}