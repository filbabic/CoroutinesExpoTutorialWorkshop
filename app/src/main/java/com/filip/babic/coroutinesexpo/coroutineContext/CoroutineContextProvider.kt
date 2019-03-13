package com.filip.babic.coroutinesexpo.coroutineContext

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { Dispatchers.Default }
    override val main: CoroutineContext by lazy { Dispatchers.Main }
}

class TestCoroutineContextProviderImpl : CoroutineContextProvider {

    override val io: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val main: CoroutineContext by lazy { Dispatchers.Unconfined }
}

interface CoroutineContextProvider {

    val main: CoroutineContext

    val io: CoroutineContext
}
