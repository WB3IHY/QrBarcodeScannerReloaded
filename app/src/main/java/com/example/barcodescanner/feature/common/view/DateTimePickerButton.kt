package com.example.barcodescanner.feature.common.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.example.barcodescanner.R
import com.example.barcodescanner.extension.formatOrNull
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.layout_date_time_picker_button.view.*
import java.text.SimpleDateFormat
import java.util.*

class DateTimePickerButton : FrameLayout {
    private val dateFormatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH)
    private val view: View

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        view = LayoutInflater
            .from(context)
            .inflate(R.layout.layout_date_time_picker_button, this, true)

        context.obtainStyledAttributes(attrs, R.styleable.DateTimePickerButton).apply {
            showHint(this)
            recycle()
        }

        view.setOnClickListener {
            showDateTimePickerDialog()
        }

        showDateTime()
    }

    var dateTime: Long = System.currentTimeMillis()
        set(value) {
            field = value
            showDateTime()
        }

    private fun showHint(attributes: TypedArray) {
        view.text_view_hint.text = attributes.getString(R.styleable.DateTimePickerButton_hint).orEmpty()
    }

    private fun showDateTimePickerDialog() {
        val fragmentManager = (context as? FragmentActivity)?.supportFragmentManager ?: return
        val title = view.text_view_hint.text.toString()
        val currentCal = Calendar.getInstance().apply { timeInMillis = dateTime }

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(title)
            .setSelection(dateTime)
            .build()

        datePicker.addOnPositiveButtonClickListener { selectedDateMs ->
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(currentCal.get(Calendar.HOUR_OF_DAY))
                .setMinute(currentCal.get(Calendar.MINUTE))
                .setTitleText(title)
                .build()

            timePicker.addOnPositiveButtonClickListener {
                val combined = Calendar.getInstance().apply {
                    timeInMillis = selectedDateMs
                    set(Calendar.HOUR_OF_DAY, timePicker.hour)
                    set(Calendar.MINUTE, timePicker.minute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }
                dateTime = combined.timeInMillis
            }

            timePicker.show(fragmentManager, "time_picker")
        }

        datePicker.show(fragmentManager, "date_picker")
    }

    private fun showDateTime() {
        view.text_view_date_time.text = dateFormatter.formatOrNull(dateTime).orEmpty()
    }
}
