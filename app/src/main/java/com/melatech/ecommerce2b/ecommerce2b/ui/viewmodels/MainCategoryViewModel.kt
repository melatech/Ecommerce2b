package com.melatech.ecommerce2b.ecommerce2b.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.melatech.ecommerce2b.data.Product
import com.melatech.ecommerce2b.data.User
import com.melatech.ecommerce2b.ecommerce2b.ui.adapters.SpecialProductsAdapter
import com.melatech.ecommerce2b.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _specialProducts = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val specialProducts: StateFlow<Resource<List<Product>>> = _specialProducts

    init {
        fetchSpecialProducts()
    }

    private fun fetchSpecialProducts(){
        viewModelScope.launch(IO) { _specialProducts.emit(Resource.Loading()) }
        firestore.collection("Products")
            .whereEqualTo("category", "Special Products")
            .get()
            .addOnSuccessListener { result ->
                val specialProductsList = result.toObjects(Product::class.java)
                viewModelScope.launch(IO) { _specialProducts.emit(Resource.Success(specialProductsList))  }

            }
            .addOnFailureListener {
                viewModelScope.launch(IO) {
                    _specialProducts.emit(Resource.Error(it.message.toString()))
                }

            }
    }



}