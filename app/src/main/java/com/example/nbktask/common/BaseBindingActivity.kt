package com.example.nbktask.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingActivity<B : ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int
) : AppCompatActivity() {

    protected var viewBinding: B? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId);
        viewBinding?.lifecycleOwner = this

    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
}