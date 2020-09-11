package com.hicoding.smkpubmak.views.fragment.absens.siswa

import com.hicoding.smkpubmak.views.model.RequestAbsensiSiswa

class AbsenSiswaView {
    interface Presenter {
        fun onFinger(request: RequestAbsensiSiswa)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onFingerSuccess(response: Any)
        fun onFailure(message: String)
    }
}