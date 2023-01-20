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
import ru.netology.markers.databinding.EditMarkerWindowBinding
import ru.netology.markers.dto.Marker
import ru.netology.markers.viewModel.MarkerViewModel

class EditDialogFragment(
    private val marker : Marker) : DialogFragment() {

    private lateinit var binding: EditMarkerWindowBinding
    private val viewModel : MarkerViewModel by viewModels(ownerProducer = ::requireParentFragment)
    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initBinding()
        setupData()
        setupListeners()
        buildDialog()
        return dialog
    }

    private fun initBinding() {
        binding = EditMarkerWindowBinding.inflate(layoutInflater)
    }
    private fun buildDialog() {
        builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setupData() {
        binding.apply {
            name.setText(marker.name)
            description.setText(marker.description)
        }
    }
    private fun setupListeners() {
        binding.apply {
            edit.setOnClickListener{
                if (name.text.isNullOrEmpty() || description.text.isNullOrEmpty()) {
                    Toast.makeText(context, R.string.empty_fields, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.edit(marker.id, name.text.toString(), description.text.toString())
                    dialog.dismiss()
                }
            }
        }
    }
}