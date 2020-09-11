package com.hicoding.smkpubmak.views.fragment.absens.siswa

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log.e
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.hicoding.smkpubmak.BuildConfig
import com.hicoding.smkpubmak.R
import com.hicoding.smkpubmak.views.utils.NetworkUtil
import com.hicoding.smkpubmak.views.utils.getDistance
import com.hicoding.smkpubmak.views.utils.gone
import com.hicoding.smkpubmak.views.utils.show
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_absen_siswa.*
import kotlinx.android.synthetic.main.layout_finger_absen_siswa.*
import kotlinx.android.synthetic.main.layout_jurusan_absen_siswa.*
import kotlinx.android.synthetic.main.layout_kelas_absen_siswa.*
import org.jetbrains.anko.support.v4.toast
import java.text.DecimalFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [AbsenSiswaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AbsenSiswaFragment : Fragment() {

    private val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val TAG = AbsenSiswaFragment::class.java.simpleName

    //location update interval - 10 sec
    private val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
    private val REQUEST_CHECK_SETTINGS = 101
    private val FASTES_UPDATE_INERVAL_IN_MILLISECONDS: Long = 4000

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var settingClient: SettingsClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationSettingRequest: LocationSettingsRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null
    private var lat: Double = 0.0
    private var long: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        val networkAvailable = NetworkUtil.instance!!.isNetworkAvailable(requireContext())
        if (networkAvailable) {
            startLocationUpdate()
        } else {
            toast(resources.getString(R.string.none_internet_connection_message))
        }

        //button refresh now location
        button_refresh_location.setOnClickListener {
            if (networkAvailable) {
                startLocationUpdate()
            } else {
                toast(resources.getString(R.string.none_internet_connection_message))
            }
        }

        //image view start to absen
        fab_start_fingerprint.setOnClickListener {
            if (currentLocation != null) {
                val distance = getDistance(lat, long)
                if (distance > 3) {
                    val decimalFormat = DecimalFormat("0.0")
                    val unit = if (distance > 1000) "KM" else "M"
                    val message =
                        "lokasi anda ${decimalFormat.format(distance / 1000f)} $unit dari Sekolahan."
                    Toasty.info(requireContext(), message).show()
                } else {

                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_absen_siswa, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        startLocationUpdate()
                    }
                }
            }
        }
    }

    private fun init() {

        //button kelas x click
        button_kelas_X.setOnClickListener {
            layout_jurusan_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_finger_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button kelas xl click
        button_kelas_XI.setOnClickListener {
            layout_jurusan_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_finger_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button kelas xll click
        button_kelas_XII.setOnClickListener {
            layout_jurusan_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_finger_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button jurusan pemrograman click
        button_jurusan_pemrograman.setOnClickListener {
            layout_finger_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_jurusan_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button jurusan akomodasi perhotelan click
        button_jurusan_akomodasi_perhotelan.setOnClickListener {
            layout_finger_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_jurusan_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button jurusan tkj click
        button_jurusan_tkj.setOnClickListener {
            layout_finger_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_jurusan_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button jurusan akuntansi click
        button_jurusan_akuntansi.setOnClickListener {
            layout_finger_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_jurusan_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button jurusan tata boga click
        button_jurusan_tata_boga.setOnClickListener {
            layout_finger_absen_siswa.show()
            layout_kelas_absen_siswa.gone()
            layout_jurusan_absen_siswa.gone()
            updateButtonNavBack()
        }

        //button back
        button_nav_back.setOnClickListener {
            if (layout_finger_absen_siswa.visibility == View.VISIBLE){
                layout_finger_absen_siswa.visibility = View.GONE
                layout_jurusan_absen_siswa.visibility = View.VISIBLE
                layout_kelas_absen_siswa.visibility = View.GONE
                updateButtonNavBack()
            }
            if(layout_jurusan_absen_siswa.visibility == View.VISIBLE){
                layout_finger_absen_siswa.visibility = View.GONE
                layout_jurusan_absen_siswa.visibility = View.GONE
                layout_kelas_absen_siswa.visibility = View.VISIBLE
                updateButtonNavBack()
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        settingClient = LocationServices.getSettingsClient(requireActivity())
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                //location is received
                currentLocation = locationResult!!.lastLocation
                setLocationUI()
            }
        }
        locationRequest = LocationRequest()
        locationRequest.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        locationRequest.fastestInterval = FASTES_UPDATE_INERVAL_IN_MILLISECONDS
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        locationSettingRequest = builder.build()
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdate() {
        Dexter.withActivity(requireActivity())
            .withPermissions(*permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        settingClient.checkLocationSettings(locationSettingRequest)
                            .addOnSuccessListener {
                                i(
                                    TAG,
                                    "Location settings are not satisfied Attemping to upgrade " + "location setting"
                                )
                                fusedLocationClient.requestLocationUpdates(
                                    locationRequest,
                                    locationCallback,
                                    Looper.myLooper()
                                )
                                setLocationUI()
                            }.addOnFailureListener {
                                when ((it as ApiException).statusCode) {
                                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                        try {
                                            //show the dialog by calling startResolutionForResult
                                            //and check the result in onActivityResult
                                            val resolutionApiException =
                                                it as ResolvableApiException
                                            resolutionApiException.startResolutionForResult(
                                                requireActivity(),
                                                REQUEST_CHECK_SETTINGS
                                            )

                                        } catch (sie: IntentSender.SendIntentException) {
                                            i(TAG, "PendingIntent unable to execute request.")
                                        }
                                    }

                                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                                        val errorMessage =
                                            "Location settings are inadequate, and cannot be " + "fixed here. Fix In Settings. "
                                        e(TAG, errorMessage)
                                        toast(errorMessage)
                                    }
                                }
                                setLocationUI()
                            }
                    } else {
                        if (report.isAnyPermissionPermanentlyDenied) {
                            openSettings()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).check()
    }

    //set update UI
    @SuppressLint("SetTextI18n")
    private fun setLocationUI() {

        if (currentLocation != null) {
            val geocoder = Geocoder(requireContext(), Locale.getDefault())
            lat = currentLocation!!.latitude
            long = currentLocation!!.longitude

            val addresses = geocoder.getFromLocation(lat, long, 1)
            if (!addresses.isNullOrEmpty()) {
                val address = addresses[0].getAddressLine(0)
                text_lat_long.text = "$lat, $long"
                text_address.text = address

            }
        }
    }

    private fun chechPermission() = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    //open the settings of app
    private fun openSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    //update state visibility button nav back button
    private fun updateButtonNavBack(){
        button_nav_back.visibility =
            if (layout_kelas_absen_siswa.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }
}