package com.melatech.ecommerce2b.ecommerce2b.ui.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentMainCategoryBinding


class MainCategoryFragment : Fragment(R.layout.fragment_main_category) {
    lateinit var binding: FragmentMainCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        return binding.root
    }
}