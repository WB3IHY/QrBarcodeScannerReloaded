package com.example.barcodescanner.feature.tabs.settings.errorcorrection

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.barcodescanner.R
import com.example.barcodescanner.di.settings
import com.example.barcodescanner.extension.applySystemWindowInsets
import com.example.barcodescanner.extension.unsafeLazy
import com.example.barcodescanner.feature.BaseActivity
import com.example.barcodescanner.feature.common.view.SettingsRadioButton
import kotlinx.android.synthetic.main.activity_choose_qr_error_correction.*

class ChooseQrErrorCorrectionActivity : BaseActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ChooseQrErrorCorrectionActivity::class.java))
        }
    }

    private val buttons by unsafeLazy {
        listOf(button_ecl_l, button_ecl_m, button_ecl_q, button_ecl_h)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_qr_error_correction)
        root_view.applySystemWindowInsets(applyTop = true, applyRight = true, applyBottom = true)
        toolbar.setNavigationOnClickListener { finish() }
        showInitialValue()
        handleSettingsChanged()
    }

    private fun showInitialValue() {
        when (settings.qrErrorCorrectionLevel) {
            "L" -> button_ecl_l.isChecked = true
            "Q" -> button_ecl_q.isChecked = true
            "H" -> button_ecl_h.isChecked = true
            else -> button_ecl_m.isChecked = true
        }
    }

    private fun handleSettingsChanged() {
        button_ecl_l.setCheckedChangedListener("L")
        button_ecl_m.setCheckedChangedListener("M")
        button_ecl_q.setCheckedChangedListener("Q")
        button_ecl_h.setCheckedChangedListener("H")
    }

    private fun SettingsRadioButton.setCheckedChangedListener(level: String) {
        setCheckedChangedListener { isChecked ->
            if (isChecked) {
                buttons.forEach { if (it !== this) it.isChecked = false }
                settings.qrErrorCorrectionLevel = level
            }
        }
    }
}
