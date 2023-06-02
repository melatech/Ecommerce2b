package com.melatech.ecommerce2b.ecommerce2b.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentIntroductionBinding
import com.melatech.ecommerce2b.databinding.FragmentRegisterBinding
import com.melatech.ecommerce2b.ecommerce2b.ui.viewmodels.RegisterViewModel


class IntroductionFragment : Fragment(R.layout.fragment_introduction) {

    private lateinit var binding: FragmentIntroductionBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntroductionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_introductionFragment_to_accountOptionsFragment)
        }
    }

    companion object {

        fun newInstance() = IntroductionFragment()
    }
}