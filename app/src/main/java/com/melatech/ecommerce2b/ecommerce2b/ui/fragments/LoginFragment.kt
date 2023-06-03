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
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.melatech.ecommerce2b.R
import com.melatech.ecommerce2b.databinding.FragmentLoginBinding
import com.melatech.ecommerce2b.databinding.FragmentRegisterBinding
import com.melatech.ecommerce2b.ecommerce2b.ui.activities.ShoppingActivity
import com.melatech.ecommerce2b.ecommerce2b.ui.dialogs.setUpBottomSheetDialog
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

        binding.tvDontHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.apply {
            buttonLoginLogin.setOnClickListener {
                val email = edEmailLogin.text.toString().trim()
                val password = edPasswordLogin.text.toString()
                println("jason: buttonLoginLogin.setOnClickListener viewModel.login(email, password)")
                viewModel.login(email, password)
            }
        }

        binding.tvForgotPasswordLogin.setOnClickListener {
            setUpBottomSheetDialog { email ->
                println("jason: tvForgotPasswordLogin.setOnClickListener viewModel.resetPassword(email) ")
                viewModel.resetPassword(email)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            launch {
                println("jason: viewModel.resetPassword.collect ")
                viewModel.resetPassword.collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            Snackbar.make(
                                requireView(),
                                "Reset link was sent to your email",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        is Resource.Error -> {
                            Snackbar.make(
                                requireView(),
                                "Error: ${it.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        else -> Unit
                    }
                }
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
                            binding.buttonLoginLogin.revertAnimation()
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
