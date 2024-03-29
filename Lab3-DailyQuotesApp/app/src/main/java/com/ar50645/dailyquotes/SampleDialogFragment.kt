package com.ar50645.dailyquotes

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SampleDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(R.string.welcome)
        builder.setMessage(R.string.welcome_message)
        builder.setPositiveButton(R.string.ok, null)
        return builder.create()
    }
}