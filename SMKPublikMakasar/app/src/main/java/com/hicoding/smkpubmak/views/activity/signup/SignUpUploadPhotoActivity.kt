package com.hicoding.smkpubmak.views.activity.signup

import android.os.Bundle
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.activity.base.BaseActivity
import com.hicoding.smkpubmak.views.activity.main.MainActivity
import com.hicoding.smkpubmak.views.model.RequestRegisterGuru
import com.hicoding.smkpubmak.views.model.RequestRegisterSiswa
import com.hicoding.smkpubmak.views.model.RequestSaveUser
import com.hicoding.smkpubmak.views.utils.AppPreferences
import kotlinx.android.synthetic.main.activity_sign_up_upload_photo.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignUpUploadPhotoActivity : BaseActivity(), SignUpView.MainView {

    companion object {
        const val EXTRA_USER_AS = "user_as"
        const val EXTRA_REQUEST_REGISTER = "request_register"
    }


    private val userAs: String by lazy { intent.getStringExtra(EXTRA_USER_AS) }
    private val requestRegisterGuru: RequestRegisterGuru by lazy {
        intent.getParcelableExtra<RequestRegisterGuru>(
            EXTRA_REQUEST_REGISTER
        )
    }
    private val requestRegisterSiswa: RequestRegisterSiswa by lazy {
        intent.getParcelableExtra<RequestRegisterSiswa>(
            EXTRA_REQUEST_REGISTER
        )
    }

    //sign up presenter
    private lateinit var signUpPresenter: SignUpPresenter

    private var photoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_upload_photo)

        initViews()
    }

    //setup all views
    private fun initViews() {

        initProgressDialog()

        signUpPresenter = SignUpPresenter(this)

        //button save next sign up with photo profile
        button_save_photo.setOnClickListener {
            if (photoUrl.isNullOrEmpty()) {
                toast("Upload foto profile Anda !")
            } else {
                if (userAs == "t") {
                    val request = RequestRegisterGuru(
                        requestRegisterGuru.username,
                        requestRegisterGuru.nama,
                        requestRegisterGuru.password,
                        photoUrl!!,
                        requestRegisterGuru.wali_kelas,
                        requestRegisterGuru.nuptk,
                        requestRegisterGuru.email
                    )
                    signUpPresenter.signUpGuru(request)
                } else {
                    val request = RequestRegisterSiswa(
                        requestRegisterSiswa.username,
                        requestRegisterSiswa.nama,
                        requestRegisterSiswa.password,
                        photoUrl!!,
                        requestRegisterSiswa.kelas,
                        requestRegisterSiswa.jurusan,
                        requestRegisterSiswa.nisn,
                        requestRegisterSiswa.email
                    )
                    signUpPresenter.signUpSiswa(request)
                }
            }
        }

        //button upload letter
        button_upload_photo_later.setOnClickListener {
            if (userAs == "t") {
                signUpPresenter.signUpGuru(requestRegisterGuru)
            } else {
                signUpPresenter.signUpSiswa(requestRegisterSiswa)
            }
        }
    }

    override fun onStartProgress() {
        showProgress()
    }

    override fun onStopProgress() {
        hideProgress()
    }

    override fun onSignUpSuccess(response: ResponseRegister) {
        val requestSaveUser = RequestSaveUser(
            response.id,
            response.username,
            response.nama,
            response.password,
            response.email,
            response.userAs
        )
        signUpPresenter.saveUser(requestSaveUser)
    }

    override fun onSuccessUploadPhotoProfile(image: String) {
        photoUrl = image
    }

    override fun onSuccessSaveUser(response: ResponseSaveUser) {
        AppPreferences.saveUser(this, response)
        startActivity<MainActivity>()
        finish()
    }

    override fun onFailure(message: String) {
        toast(message)
    }
}