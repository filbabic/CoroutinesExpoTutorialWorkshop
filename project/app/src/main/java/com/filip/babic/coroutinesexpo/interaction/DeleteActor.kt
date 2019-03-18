package com.filip.babic.coroutinesexpo.interaction

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

class DeleteActor {

    companion object {
        private const val MAX_ITEMS_TO_HANDLE = 3
    }

    fun executeActor(
        postsToDelete: List<String>,
        storageInteractor: StorageInteractor
    ) {
        if (postsToDelete.size > MAX_ITEMS_TO_HANDLE) {
            val dataPart = postsToDelete.take(MAX_ITEMS_TO_HANDLE)
            val leftToDelete = postsToDelete.drop(MAX_ITEMS_TO_HANDLE)

            val actor = createActor(storageInteractor)

            GlobalScope.launch {
                if (leftToDelete.isNotEmpty()) {
                    val nextActor = DeleteActor()

                    nextActor.executeActor(leftToDelete, storageInteractor)
                }
            }

            dataPart.forEach { actor.offer(it) }
        } else {
            val actor = createActor(storageInteractor)

            postsToDelete.forEach { actor.offer(it) }
        }
    }

    private fun createActor(storageInteractor: StorageInteractor): SendChannel<String> = GlobalScope.actor(capacity = MAX_ITEMS_TO_HANDLE) {
        var counter = 0

        while (!isClosedForReceive) {
            val data = poll() ?: continue

            counter++

            GlobalScope.launch { storageInteractor.deletePost(data) }

            if (counter == MAX_ITEMS_TO_HANDLE) {
                cancel()
            }
        }
    }
}