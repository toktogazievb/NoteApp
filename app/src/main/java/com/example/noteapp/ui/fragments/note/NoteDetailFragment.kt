package com.example.noteapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.google.android.material.button.MaterialButton
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDateAndTime()
        setupListeners()
    }


    private fun setDateAndTime() = with(binding) {
        val calendar = Calendar.getInstance().time
        val russianLocale = Locale("ru")
        val dateFormat = SimpleDateFormat("dd MMMM", russianLocale).format(calendar)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar)
        txtDate.text = dateFormat
        txtTime.text = timeFormat
    }

    private fun setupListeners() = with(binding) {
        btnBack.setOnClickListener { findNavController().navigateUp() }
        val colors = listOf(
            R.color.yellow,
            R.color.purple,
            R.color.pink,
            R.color.light_red,
            R.color.green,
            R.color.blue
        )
        btnReady.visibility = View.VISIBLE
        btnReady.setOnClickListener {
            val etTitle = binding.etTitle.text.toString()
            val etDescription = binding.etDescription.text.toString()
            val date = "213123"
            App.appDatabase?.noteDao()?.insertMode(NoteModel(etTitle, etDescription))
            findNavController().navigateUp()
        }
    }
}
