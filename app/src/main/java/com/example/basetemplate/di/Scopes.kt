package com.example.basetemplate.di

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ViewHolderScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationScope