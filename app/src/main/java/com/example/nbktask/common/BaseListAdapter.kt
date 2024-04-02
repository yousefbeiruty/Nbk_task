package com.example.nbktask.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<V : BaseViewHolder<DL>, DL>(diffCallback: DiffUtil.ItemCallback<DL>) :
    ListAdapter<DL, V>(diffCallback) {

    protected open var context: Context? = null

    protected open var inflater: LayoutInflater? = null

    private var onItemClickListener: ((view: View, model: DL, position: Int) -> Unit)? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.context = recyclerView.context
        inflater = LayoutInflater.from(context)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.onItemClickListener = null
        this.inflater = null
        this.context = null
    }

    override fun onBindViewHolder(holder: V, position: Int) {
        if (onItemClickListener != null) {
            handleClickListener(holder)
        }
    }

    private fun handleClickListener(holder: V) {
        holder.itemView.setOnClickListener { view ->
            onItemClicked(view, holder)
        }
    }

    open fun onItemClicked(view: View, holder: V) {
        val clickedPosition = holder.adapterPosition
        if (clickedPosition != RecyclerView.NO_POSITION) {
            val model = getItem(clickedPosition)
            onItemClickListener?.invoke(view, model, clickedPosition)
        }
    }

    fun setOnItemClickListener(onItemClickListener: (view: View, model: DL, position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    fun getOnItemClickListener(): ((view: View, model: DL, position: Int) -> Unit)? {
        return onItemClickListener
    }
}