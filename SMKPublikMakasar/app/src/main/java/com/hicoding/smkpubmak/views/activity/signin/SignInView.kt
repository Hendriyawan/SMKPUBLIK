package com.hicoding.smkpubmak.views.activity.signin

import com.hicoding.smkpubmak.views.activity.signup.ResponseSaveUser

class SignInView {
    interface Presenter {
        fun signIn(username: String, password: String)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onSignInSuccess(response : ResponseSaveUser)
        fun onFailure(message: String)
    }
}