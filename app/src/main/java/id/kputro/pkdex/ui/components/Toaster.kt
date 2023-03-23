package id.kputro.pkdex.ui.components

import android.app.Activity
import android.widget.Toast
import es.dmoral.toasty.Toasty

fun Activity.showToast(message: String, short: Boolean = false) {
  Toasty.normal(this, message, if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG).show()
}