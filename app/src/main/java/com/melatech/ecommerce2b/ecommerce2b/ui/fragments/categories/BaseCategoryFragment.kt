package com.melatech.ecommerce2b.ecommerce2b.ui.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentBaseCategoryBinding
import com.melatech.ecommerce2b.databinding.FragmentMainCategoryBinding

open class BaseCategoryFragment : Fragment(R.layout.fragment_base_category) {
    lateinit var binding: FragmentBaseCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}