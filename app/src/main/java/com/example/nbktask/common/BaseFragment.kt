package com.example.nbktask.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import com.example.nbktask.extensions.getGenericClass
import com.example.nbktask.extensions.hideProgressDialog
import com.example.nbktask.extensions.showProgressDialog
import com.example.domain.common.ResultException
import javax.inject.Inject

/**
 *  Main base [Fragment]
 *
 *
 *  @param B the type of dataBinding
 *  @param VM the type of ViewModel
 */
abstract class BaseFragment<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes
    private val layoutId: Int
) : Fragment() {

    var viewBinding: B? = null

    @Inject
    lateinit var progressDialog: ProgressDialog

    protected val viewModel: VM by createViewModelLazy(
        getGenericClass<VM>(1).kotlin,
        { viewModelStore })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewBinding?.lifecycleOwner = viewLifecycleOwner

        return viewBinding?.root
    }

    // This function is responsible to handle viewState that included in BaseViewModel
    open fun handleViewState(
        viewState: BaseViewState
    ) {
        when (viewState) {
            is BaseViewState.Idle -> {
            }
            is BaseViewState.Loading -> {
                progressDialog.showProgressDialog()
            }
            is BaseViewState.DataLoaded -> {
                progressDialog.hideProgressDialog()
            }
            is BaseViewState.Error -> {
                progressDialog.hideProgressDialog()
                handleErrorState(viewState.resultException)
            }
        }
    }

    private fun handleErrorState(resultException: ResultException) {
        Toast.makeText(requireContext(), resultException.message, Toast.LENGTH_LONG).show()
    }
}
