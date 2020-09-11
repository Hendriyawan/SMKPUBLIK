package com.hicoding.smkpubmak.views.activity.signup

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.model.RequestRegisterGuru
import com.hicoding.smkpubmak.views.model.RequestRegisterSiswa
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.layout_input_guru.*
import kotlinx.android.synthetic.main.layout_input_siswa.*
import org.jetbrains.anko.startActivity

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {
    private var userAs: String = "s"
    private var kelas = "X"
    private var jurusan = "Pemrograman"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupTabLayout()
    }

    /**
     * setup view TabLayout
     */
    private fun setupTabLayout() {
        spinner_kelas.setItems("X", "XI", "XII")
        spinner_jurusan.setItems("Pemrograman", "Akomodasi Perhotelan", "Administrasi Perkantoran", "TKJ", "Akutansi", "Tata Boga")

        spinner_kelas.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<String>{
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: String) {
                kelas = item
            }
        })

        spinner_jurusan.setOnItemSelectedListener(object : MaterialSpinner.OnItemSelectedListener<String>{
            override fun onItemSelected(view: MaterialSpinner?, position: Int, id: Long, item: String) {
                jurusan = item
            }
        })

        (tab_layout as TabLayout).apply {
            addTab(this.newTab().setText("Sebagai Siswa"))
            addTab(this.newTab().setText("Sebagai Guru"))

            //set listener of tab click / selected
            setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    setActionTabSelected(tab!!)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    setActionTabSelected(tab!!)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            })
        }
        button_next_register.setOnClickListener {
            if (userAs == "s") {
                val username = edit_input_username_siswa.text.toString()
                val nama = edit_input_name_siswa.text.toString()
                val password = edit_input_password_siswa.text.toString()
                val photo = "-"
                val nisn = edit_input_nisn_siswa.text.toString().toInt()
                val email = edit_input_email_siswa.text.toString()

                val requestRegisterSiswa = RequestRegisterSiswa(
                    username,
                    nama,
                    password,
                    photo,
                    kelas,
                    jurusan,
                    nisn,
                    email
                )
                startActivity<SignUpUploadPhotoActivity>(
                    SignUpUploadPhotoActivity.EXTRA_USER_AS to userAs,
                    SignUpUploadPhotoActivity.EXTRA_REQUEST_REGISTER to requestRegisterSiswa
                )

            } else {
                val username = edit_input_username_guru.text.toString()
                val nama = edit_input_name_guru.text.toString()
                val password = edit_input_password_guru.text.toString()
                val photo = "-"
                val waliKelas = edit_input_wali_kelas.text.toString()
                val nuptk = edit_input_nuptk_guru.text.toString().toInt()
                val email = edit_input_email_siswa.text.toString()
                val requestRegisterGuru = RequestRegisterGuru(
                    username,
                    nama,
                    password,
                    photo,
                    waliKelas,
                    nuptk,
                    email
                )

                startActivity<SignUpUploadPhotoActivity>(
                    SignUpUploadPhotoActivity.EXTRA_USER_AS to userAs,
                    SignUpUploadPhotoActivity.EXTRA_REQUEST_REGISTER to requestRegisterGuru
                )
            }
        }
    }

    private fun setActionTabSelected(tab: TabLayout.Tab) {
        when (tab.position) {
            0 -> {
                layout_input_siswa.visibility = View.VISIBLE
                layout_input_guru.visibility = View.GONE
                layout_input_siswa.animation =
                    AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
                userAs = "s"
            }

            1 -> {
                layout_input_siswa.visibility = View.GONE
                layout_input_guru.visibility = View.VISIBLE
                layout_input_guru.animation =
                    AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
                userAs = "t"
            }
        }
    }
}