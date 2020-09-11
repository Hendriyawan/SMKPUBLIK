package com.hicoding.smkpubmak.views.activity.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.GravityCompat
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.activity.base.BaseActivity
import com.hicoding.smkpubmak.views.fragment.absens.gurustaff.AbsenGuruStaffFragment
import com.hicoding.smkpubmak.views.fragment.absens.siswa.AbsenSiswaFragment
import com.hicoding.smkpubmak.views.fragment.contact.ContactFragment
import com.hicoding.smkpubmak.views.fragment.home.HomeFragment
import com.hicoding.smkpubmak.views.fragment.info.InfoPendaftaranFragment
import com.hicoding.smkpubmak.views.fragment.materi.MateriPelajaranFragment
import com.hicoding.smkpubmak.views.fragment.setting.SettingsFragment
import com.hicoding.smkpubmak.views.utils.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    /*//fragments
    private lateinit var homeFragment: HomeFragment
    private lateinit var absenGuruFragment: AbsenGuruStaffFragment
    private lateinit var absenSiswaFragment: AbsenSiswaFragment
    private lateinit var materiFragment: MateriPelajaranFragment
    private lateinit var infoPendaftaranFragment: InfoPendaftaranFragment
    private lateinit var contactFragment: ContactFragment
    private lateinit var settingFragment: SettingsFragment
    private lateinit var activeFragment: Fragment*/

    //fragment manager
    //private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        //toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Home"
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }

        //createFragment()

        //drawer layout & NavigationView
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {

        }

        drawer_layout.addDrawerListener(drawerToggle)
        val dataUser = AppPreferences.getUser(this)
        navigation_view.menu.findItem(R.id.navigation_absen_guru).isVisible =
            dataUser?.userAs == "t"

        loadFragment(HomeFragment(), R.id.fragment_container)
        setTitleToolbar(R.string.navigation_title_home)

        //handle Navigation select item
        navigation_view.apply {
            this.setCheckedItem(R.id.navigation_home)
            setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        loadFragment(HomeFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_home)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                    R.id.navigation_absen_guru -> {
                        loadFragment(AbsenGuruStaffFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_absen_guru)
                        drawer_layout.closeDrawer(GravityCompat.START)

                    }

                    R.id.navigation_absen_siswa -> {
                        loadFragment(AbsenSiswaFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_absen_siswa)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                    R.id.navigation_materi_pelajaran -> {
                        loadFragment(MateriPelajaranFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_materi_pelajaran)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    R.id.navigation_info_pendaftaran -> {
                        loadFragment(InfoPendaftaranFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_info_pendaftaran)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                    R.id.navigation_contact -> {
                        loadFragment(ContactFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_contact)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }

                    R.id.navigation_settings -> {
                        loadFragment(SettingsFragment(), R.id.fragment_container)
                        setTitleToolbar(R.string.navigation_title_settings)
                        drawer_layout.closeDrawer(GravityCompat.START)
                    }
                    R.id.navigation_logout -> {
                        AppPreferences.removeUser(this@MainActivity)
                        finish()
                    }
                }
                true
            }
        }

        val view = navigation_view.getHeaderView(0)
        val buttonClose = view.findViewById<AppCompatImageView>(R.id.image_view_close_drawer)
        buttonClose.setOnClickListener {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*//creating fragments
    private fun createFragment() {
        homeFragment = HomeFragment()
        absenGuruFragment = AbsenGuruStaffFragment()
        absenSiswaFragment = AbsenSiswaFragment()
        materiFragment = MateriPelajaranFragment()
        infoPendaftaranFragment = InfoPendaftaranFragment()
        contactFragment = ContactFragment()
        settingFragment = SettingsFragment()

        activeFragment = HomeFragment()

        //default display fragment
        fm = supportFragmentManager

        fm.beginTransaction()
            .add(R.id.fragment_container, homeFragment, homeFragment::class.simpleName).commit()
        fm.beginTransaction()
            .add(R.id.fragment_container, absenGuruFragment, absenGuruFragment::class.simpleName)
            .hide(absenGuruFragment).commit()
        fm.beginTransaction()
            .add(R.id.fragment_container, absenSiswaFragment, absenSiswaFragment::class.simpleName)
            .hide(absenSiswaFragment).commit()

        fm.beginTransaction()
            .add(R.id.fragment_container, materiFragment, materiFragment::class.simpleName)
            .hide(materiFragment).commit()

        fm.beginTransaction().add(
            R.id.fragment_container,
            infoPendaftaranFragment,
            infoPendaftaranFragment::class.java.simpleName
        ).hide(infoPendaftaranFragment)

        fm.beginTransaction()
            .add(R.id.fragment_container, contactFragment, contactFragment::class.simpleName)
            .hide(contactFragment).commit()
        fm.beginTransaction()
            .add(R.id.fragment_container, settingFragment, settingFragment::class.simpleName)
            .hide(settingFragment).commit()
    }

    //remove fragment
    private fun removeFragment() {
        if (fm.findFragmentByTag(homeFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(homeFragment).commit()
        }
        if (fm.findFragmentByTag(absenGuruFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(absenSiswaFragment).commit()
        }
        if (fm.findFragmentByTag(absenSiswaFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(absenSiswaFragment).commit()
        }
        if (fm.findFragmentByTag(materiFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(materiFragment).commit()
        }
        if (fm.findFragmentByTag(infoPendaftaranFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(infoPendaftaranFragment).commit()
        }
        if (fm.findFragmentByTag(contactFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(contactFragment).commit()
        }
        if (fm.findFragmentByTag(settingFragment::class.java.simpleName) != null) {
            fm.beginTransaction().remove(settingFragment).commit()
        }
    }

    //switch fragment
    private fun switchFragment(fragment: Fragment) {
        drawer_layout.closeDrawer(GravityCompat.START)
        if (activeFragment != fragment) {
            fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .hide(activeFragment).show(fragment).commit()
            activeFragment = fragment

            when (activeFragment::class.java.simpleName) {
                homeFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_home)
                absenGuruFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_absen_guru)
                absenSiswaFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_absen_siswa)
                materiFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_materi_pelajaran)
                infoPendaftaranFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_info_pendaftaran)
                contactFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_contact)
                settingFragment::class.java.simpleName -> setTitleToolbar(R.string.navigation_title_settings)
            }
        }
    }*/

    //set title of Toolbar
    private fun setTitleToolbar(title: Int) {
        supportActionBar?.title = resources.getString(title)
    }
}