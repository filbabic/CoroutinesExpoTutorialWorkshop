package com.filip.babic.coroutinesexpo.di

import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractor
import com.filip.babic.coroutinesexpo.interaction.AuthenticationInteractorImpl
import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.interaction.StorageInteractorImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module.module

val NetworkingModule = module {
    single { FirebaseAuth.getInstance() }
    single { AuthenticationInteractorImpl(get()) as AuthenticationInteractor }

    single { FirebaseFirestore.getInstance() }
    single { StorageInteractorImpl(get()) as StorageInteractor }
}