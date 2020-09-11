package com.hicoding.smkpubmak.views.activity.signin

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.hicoding.smkpubmak.views.activity.signup.ResponseSaveUser
import com.hicoding.smkpubmak.views.global.Constants
import com.hicoding.smkpubmak.views.global.EndPoint

class SignInPresenter(private val mainView: SignInView.MainView) : SignInView.Presenter {
    override fun signIn(username: String, password: String) {
        mainView.onStartProgress()
        AndroidNetworking.post(EndPoint.SIGN_IN)
            .addBodyParameter("username", username)
            .addBodyParameter("password", password)
            .setPriority(Priority.MEDIUM)
            .setTag(Constants.TAG_SIGN_IN)
            .build()
            .getAsObject(
                ResponseSaveUser::class.java,
                object : ParsedRequestListener<ResponseSaveUser> {
                    override fun onResponse(response: ResponseSaveUser) {
                        mainView.onStopProgress()
                        if (response.success) {
                            mainView.onSignInSuccess(response)
                        } else {
                            mainView.onFailure(response.message)
                        }
                    }

                    override fun onError(anError: ANError?) {
                        mainView.onStopProgress()
                        mainView.onFailure(anError?.message!!)
                    }
                })
    }
}