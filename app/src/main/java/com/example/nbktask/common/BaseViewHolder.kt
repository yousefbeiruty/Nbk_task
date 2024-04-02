package com.example.nbktask.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 *  Main base [BaseViewHolder]
 *
 *
 *  @param Model the type of input data
 */
abstract class BaseViewHolder<Model>(view: ViewBinding) : RecyclerView.ViewHolder(view.root) {
    abstract fun bind(
        model: Model,
        position: Int,
        clickListener: ((view: View, model: Model, position: Int) -> Unit)? = null
    )

    open fun onDestroy() {}

}