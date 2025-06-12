package com.example.thuc_hanh_4

import androidx.lifecycle.ViewModel

open class MainViewModel : ViewModel() {
    var userEmail: String = ""
    var generatedOtp: String = ""
    var userPassword: String = ""
}
