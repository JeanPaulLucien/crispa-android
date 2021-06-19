package io.github.chronosx88.yggdrasil

import android.app.Application
import android.content.Context
import org.acra.config.dialog
import org.acra.config.httpSender
import org.acra.data.StringFormat
import org.acra.ktx.initAcra
import org.acra.sender.HttpSender

class YggApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        initAcra {
            //core configuration:
            buildConfigClass = BuildConfig::class.java
            reportFormat = StringFormat.JSON
            //each plugin you chose above can be configured in a block like this:
            httpSender {
                uri = "http://<host>/report"
                basicAuthLogin="***"
                basicAuthPassword = "***"
                httpMethod = HttpSender.Method.POST
            }
            dialog {
                //required
                text = getString(R.string.report_dialog_text)
                //optional, enables the dialog title
                title = getString(R.string.app_name)
                //defaults to android.R.string.ok
                positiveButtonText = getString(android.R.string.ok)
                //defaults to android.R.string.cancel
                negativeButtonText = getString(android.R.string.cancel)
                //optional, enables the comment input
                commentPrompt = getString(R.string.report_dialog_comment)
                //optional, enables the email input
                //emailPrompt = getString(R.string.report_dialog_email)
                //defaults to android.R.drawable.ic_dialog_alert
                resIcon = android.R.drawable.ic_dialog_alert
                //optional, defaults to @android:style/Theme.Dialog
                resTheme = R.style.Theme_AppCompat_Dialog
            }
        }
    }
}