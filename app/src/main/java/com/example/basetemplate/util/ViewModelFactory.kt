package com.example.basetemplate.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class ViewModelFactory<T:ViewModel> (
    private val kClass: KClass<T>,// KClass is the holder of class of type ViewModel that needs to be inject
    private val creator:() -> T// This is the Lambda function, this is provided be the ActivityModule/FragmentModule,
    // when creator lambda is called then that module creates and return the instance of ViewModel
        ):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java))
            return creator() as T
        throw IllegalAccessException("Unknown class name")

    }
}