package com.example.nbktask.common

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import com.example.nbktask.R
import com.example.nbktask.databinding.DialogBinding

class ProgressDialog(private val mContext: Context) : AppCompatDialog(mContext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: DialogBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                mContext
            ), R.layout.dialog, null, false
        )
        setContentView(binding.root)

        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}