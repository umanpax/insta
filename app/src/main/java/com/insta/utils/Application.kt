package com.insta.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.insta.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 *
 */

class Application {
    companion object {


        fun launchActivity(from: AppCompatActivity, to: Class<*>, closePreviousActivity: Boolean) {
            val i = Intent(from, to)
            from.startActivity(i)
            if (closePreviousActivity) {
                from.finish()
            }
        }

        @SuppressLint("MissingPermission")
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

        fun updateWorkflow(clubWorkflow: Workflow) {
            Workflow.Singleton.INSTANCE!!.getInstance().updateInstance(clubWorkflow)
        }

        fun getWorkflow(): Workflow {
            return if (Workflow.Singleton.INSTANCE != null) Workflow.Singleton.INSTANCE!!.getInstance()
            else Workflow()
        }

        fun changeStatusBarColor(activity: Activity, color: Int) {
            val window: Window = activity.window
            window.statusBarColor = ContextCompat.getColor(activity, color)
            window.setUiOptions(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        }


        fun showSnackBar(context: Context, layout: View, message: String) {
            // showing snack bar with Undo option
            val snackbar = Snackbar
                .make(layout, message, Snackbar.LENGTH_LONG)
            snackbar.setAction("Ok") {
                //DO SOMETHING HERE
            }
            snackbar.setActionTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorClubInsta
                )
            )
            snackbar.show()
        }


        @SuppressLint("SimpleDateFormat")
        fun convertToFormatSpecific(
            mInputFormat: String,
            mOutputFormat: String,
            dateString: String
        ): String {
            val inputFormat: DateFormat = SimpleDateFormat(mInputFormat)
            val outputFormat: DateFormat = SimpleDateFormat(mOutputFormat)
            val inputDateStr = dateString
            val date: Date = inputFormat.parse(inputDateStr)
            val outputDateStr: String = outputFormat.format(date)
            return outputDateStr
        }


    }

}

