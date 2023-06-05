package com.melatech.ecommerce2b.ecommerce2b.ui.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentMainCategoryBinding
import com.melatech.ecommerce2b.ecommerce2b.ui.adapters.SpecialProductsAdapter

class MainCategoryFragment : Fragment(R.layout.fragment_main_category) {
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var specialProductsAdapter: SpecialProductsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpecialProductRv()
    }

    private fun  setUpSpecialProductRv(){
        specialProductsAdapter = SpecialProductsAdapter()
        binding.rvSpecialProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = specialProductsAdapter
        }

    }
}