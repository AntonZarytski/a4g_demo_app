package com.android.academy.myapplication

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var adView: AdView
    private var isAdaptiveBannerInabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        adView = AdView(this)
        adView.adUnitId = "/60257202/76988"
        if (isAdaptiveBannerInabled) {
            val maxWidth: Int = getScreenWidthInDp(this).roundToInt()
            val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, maxWidth)
            adView.adSize = adSize
        } else {
            adView.adSize = AdSize.SMART_BANNER
        }
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
//                .setTestDeviceIds(Arrays.asList("C62A326B665484F9CC160B0A63E19644"))
//                .setTestDeviceIds(Arrays.asList("6A308773996DAF2F457C41C2CD047CC8"))
                .build()
        )
        root_layout.addView(adView)

        val adRequest = AdRequest.Builder().build()
        adView.adListener = AdmobBannerListener(adView)
        load_ad.setOnClickListener {
            val adSize = adView.adSize
            adView.loadAd(adRequest)
        }
    }

    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    public override fun onResume() {
        super.onResume()
        adView.resume()
    }

    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }

    fun getScreenWidthInDp(context: Context): Float {
        val window = context.getSystemService(WINDOW_SERVICE) as WindowManager
        val display = window.defaultDisplay
        val displayMetrics = context.resources.displayMetrics
        val size = Point()
        display.getSize(size)
        return size.x / displayMetrics.density
    }
}

internal class AdmobBannerListener(adView: AdView) : AdListener() {
    private val adView: AdView
    override fun onAdLoaded() {
        super.onAdLoaded()
        val adSize = adView.adSize
        var test = adSize
    }

    override fun onAdFailedToLoad(error: LoadAdError) {
        super.onAdFailedToLoad(error)
    }

    override fun onAdOpened() {
        super.onAdOpened()
    }

    init {
        this.adView = adView
    }
}
