package com.melatech.ecommerce2b.ecommerce2b.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melatech.ecommerce2b.R


class AccountOptionsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_options, container, false)
    }

    companion object {

        fun newInstance() = AccountOptionsFragment()
    }
}