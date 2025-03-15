package io.github.u1tramarinet.organizerapp.ui.common

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.os.Build
import android.view.WindowManager
import android.view.WindowMetrics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import io.github.u1tramarinet.organizerapp.BuildConfig

@Composable
fun AdBanner(modifier: Modifier = Modifier) {
    val adRequest = remember { AdRequest.Builder().build() }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val adSize = context.adSize
            val view = AdView(context)
            view.setAdSize(adSize)
            view.adUnitId = BuildConfig.ADMOB_AD_UNIT_ID
            view.loadAd(adRequest)
            view
        }
    )
}

private val Context.adSize: AdSize
    get() {
        val displayMetrics = resources.displayMetrics
        val adWidthPixels =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
                val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
                windowMetrics.bounds.width()
            } else {
                displayMetrics.widthPixels
            }
        val density = displayMetrics.density
        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }