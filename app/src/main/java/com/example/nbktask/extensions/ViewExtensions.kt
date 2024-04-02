package com.example.nbktask.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu

fun Context.showToast(msg: String) =
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Context.openMenu(view: View?, menu: Int, menuClick: (Int) -> Unit) {
    this.apply {
        val popupMenu =
            view?.let {
                PopupMenu(
                    this,
                    it
                )
            }
        popupMenu?.menuInflater?.inflate(menu, popupMenu.menu)

        popupMenu?.setOnMenuItemClickListener { menuItem ->
            menuClick.invoke(menuItem.itemId)
            true
        }
        popupMenu?.show()
    }
}

fun Context.openWebUrl(url: String?) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}
