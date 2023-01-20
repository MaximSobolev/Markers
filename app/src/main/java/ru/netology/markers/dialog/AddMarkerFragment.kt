package ru.netology.markers.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import ru.netology.markers.R
import ru.netology.markers.databinding.DialogWindowBinding
import ru.netology.markers.viewModel.MarkerViewModel

class AddMarkerFragment : DialogFragment() {
    private lateinit var binding: DialogWindowBinding
    private val viewModel : MarkerViewModel by viewModels(ownerProducer = ::requireParentFragment)
    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initBinding()
        setupListeners()
        buildDialog()
        return dialog
    }

    private fun initBinding() {
        binding = DialogWindowBinding.inflate(layoutInflater)
    }
    private fun buildDialog() {
        builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
    private fun setupListeners() {
        binding.apply {
            save.setOnClickListener{
                if (name.text.isNullOrEmpty() || description.text.isNullOrEmpty()) {
                    Toast.makeText(context, R.string.empty_fields, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.save(name.text.toString(), description.text.toString())
                    dialog.dismiss()
                }
            }
        }
    }
}