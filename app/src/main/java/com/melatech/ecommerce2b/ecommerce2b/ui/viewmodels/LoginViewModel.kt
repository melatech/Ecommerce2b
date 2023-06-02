package com.melatech.ecommerce2b.ecommerce2b.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.melatech.ecommerce2b.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,

    ) : ViewModel() {
    private val _login = MutableSharedFlow<Resource<FirebaseUser>>()
    val login = _login.asSharedFlow()

    fun login(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) { _login.emit(Resource.Loading()) }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                viewModelScope.launch((Dispatchers.IO)) {
                    it.user?.let {
                        _login.emit(Resource.Success(it))
                    }
                }
            }
            .addOnFailureListener {
                viewModelScope.launch(IO) {
                    _login.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}
