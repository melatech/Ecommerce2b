package com.melatech.ecommerce2b.util

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation{
    if(email.isEmpty()){
        return RegisterValidation.Failed("Email must not be empty")
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        return RegisterValidation.Failed("Wrong email format")
    }
    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation{
    if (password.isEmpty()){
        return RegisterValidation.Failed("Password must not be empty")
    }
    if (password.length < 6){
        return RegisterValidation.Failed("Password should contain at least 6 char")
    }
    return RegisterValidation.Success
}