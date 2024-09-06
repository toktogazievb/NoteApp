package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                txtTitle.text = "Удобство"
                txtDescription.text =
                    "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottieAnim.setAnimation(R.raw.anim_note1)
            }

            1 -> {
                txtTitle.text = "Организация"
                txtDescription.text =
                    "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottieAnim.setAnimation(R.raw.anim_note2)
            }

            2 -> {
                txtTitle.text = "Синхронизация"
                txtDescription.text =
                    "Синхронизация на всех устройствах.\n Доступ к записям в любое время и в любом месте."
                lottieAnim.setAnimation(R.raw.anim_note3)
            }
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}