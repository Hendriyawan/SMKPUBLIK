package com.hicoding.smkpubmak.views.activity.signin

import android.os.Bundle
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.activity.base.BaseActivity
import com.hicoding.smkpubmak.views.activity.main.MainActivity
import com.hicoding.smkpubmak.views.activity.signup.ResponseSaveUser
import com.hicoding.smkpubmak.views.activity.signup.SignUpActivity
import com.hicoding.smkpubmak.views.utils.AppPreferences
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignInActivity : BaseActivity(), SignInView.MainView {
    private lateinit var signInPresenter: SignInPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initViews()
    }

    private fun initViews() {

        //progress dialog
        initProgressDialog()

        signInPresenter = SignInPresenter(this)

        //button save sign in
        button_sign_in.setOnClickListener {
            val username = edit_input_username.text.toString()
            val password = edit_input_password.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                toast("username atau password masih kosong !")
            } else {
                signInPresenter.signIn(username, password)
            }
        }

        //button go to register new account
        button_register_new_account.setOnClickListener {
            startActivity<SignUpActivity>()
        }
    }

    override fun onStartProgress() {
        showProgress()
    }

    override fun onStopProgress() {
        hideProgress()
    }

    override fun onSignInSuccess(response: ResponseSaveUser) {
        AppPreferences.saveUser(this, response)
        finish()
        startActivity<MainActivity>()
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}