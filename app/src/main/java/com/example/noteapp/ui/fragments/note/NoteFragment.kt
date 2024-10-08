package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.interfaces.OnClickItem
import com.example.noteapp.ui.adapter.NoteAdapter
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView

class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter = NoteAdapter(this, this)
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        getData()

    }

    private fun initialize() {
        binding.homeRecyclerView.apply {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (sharedPreferences.isRecyclerViewGrid) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                binding.btnChangerRv.setImageResource(R.drawable.ic_rv_linear)
            } else if (!sharedPreferences.isRecyclerViewGrid) {
                layoutManager = LinearLayoutManager(requireContext())
                binding.btnChangerRv.setImageResource(R.drawable.ic_rv_grid)
            }
            adapter = noteAdapter
        }

        binding.apply {
            toggle = ActionBarDrawerToggle(
                requireActivity(),
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.onBoardFragment ->
                        findNavController().navigate(R.id.onBoardFragment)

                    R.id.noteFragment ->
                        findNavController().navigate(R.id.noteFragment)

                    R.id.chatFragment ->
                        findNavController().navigate(R.id.chatFragment)
                }
                true
            }
        }
    }

    private fun setupListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        btnChangerRv.setOnClickListener {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            if (!sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.ic_rv_linear)
                homeRecyclerView.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    sharedPreferences.isRecyclerViewGrid = true
                }
            } else if (sharedPreferences.isRecyclerViewGrid) {
                btnChangerRv.setImageResource(R.drawable.ic_rv_grid)
                homeRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    sharedPreferences.isRecyclerViewGrid = false
                }
            }
        }
        btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun getData() {
        App.appDatabase?.noteDao()?.getAll()?.observe(viewLifecycleOwner) { list ->
            noteAdapter.submitList(list)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить заметку?")
            setPositiveButton("Да") { dialog, _ ->
                App.appDatabase?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, _ ->
                dialog.cancel()
            }
            show()
        }
        builder.create()
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(noteModel.id)
        findNavController().navigate(action)
    }
}