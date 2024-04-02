package com.example.nbktask.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Adds the given observer to the observers list within the lifespan of the given
 * owner. The events are dispatched on the main thread. If LiveData already has data
 * set, it will be delivered to the observer.
 *
 * @param sharedFlow The stateFlow to observe.
 * @param flow The observer that will receive the events.
 * @see SharedFlow.collectLatest
 */
fun <T> LifecycleOwner.collectLatest(sharedFlow: SharedFlow<T>, flow: (T) -> Unit) {
    lifecycleScope.launch {
        sharedFlow.collectLatest {
            flow(it)
        }
    }
}
/**
 * Adds the given observer to the observers list within the lifespan of the given
 * owner. The events are dispatched on the main thread. If LiveData already has data
 * set, it will be delivered to the observer.
 *
 * @param stateFlow The StateFlow to observe.
 * @param flow The observer that will receive the events.
 * @see StateFlow.collectLatest
 */
fun <T> LifecycleOwner.collectLatest(stateFlow: StateFlow<T>, flow: (T) -> Unit) {
    lifecycleScope.launch {
        stateFlow.collectLatest {
            flow(it)
        }
    }
}
/**
 * Adds the given observer to the observers list within the lifespan of the given
 * owner. The events are dispatched on the main thread. If flow already has data
 * set, it will be delivered to the collector.
 *
 * @param flow The Flow to collect.
 * @param collector The observer that will receive the events.
 * @see Flow.collectLatest
 */
fun <T> LifecycleOwner.collectLatest(flow: Flow<T>, collector: (T) -> Unit) {
    lifecycleScope.launch {
        flow.collectLatest {
            collector(it)
        }
    }
}
