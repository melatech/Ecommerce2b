package com.melatech.ecommerce2b.ecommerce2b.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentLoginBinding
import com.melatech.ecommerce2b.databinding.FragmentRegisterBinding
import com.melatech.ecommerce2b.ecommerce2b.ui.activities.ShoppingActivity
import com.melatech.ecommerce2b.ecommerce2b.ui.viewmodels.LoginViewModel
import com.melatech.ecommerce2b.ecommerce2b.ui.viewmodels.RegisterViewModel
import com.melatech.ecommerce2b.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonLoginLogin.setOnClickListener {
                val email = edEmailLogin.text.toString().trim()
                val password = edPasswordLogin.text.toString()
                viewModel.login(email, password)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                viewModel.login.collect {
                    when (it) {
                        is Resource.Loading -> {
                            binding.buttonLoginLogin.startAnimation()
                        }
                        is Resource.Success -> {
                            Intent(requireActivity(), ShoppingActivity::class.java).also { intent ->
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                            }
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                            binding.buttonLoginLogin.revertAnimation()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}