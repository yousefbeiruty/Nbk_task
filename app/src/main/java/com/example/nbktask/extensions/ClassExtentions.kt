package com.example.nbktask.extensions

import java.lang.reflect.ParameterizedType

fun <GENERIC_CLASS> Any.getGenericClass(pos: Int = 0) =
    (javaClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.get(pos) as? Class<GENERIC_CLASS>
        ?: throw IllegalArgumentException("there is no generic param at position $pos")