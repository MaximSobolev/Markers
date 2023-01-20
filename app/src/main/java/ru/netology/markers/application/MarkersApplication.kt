package ru.netology.markers.application

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import com.yandex.mapkit.MapKitFactory
import ru.netology.markers.R

class MarkersApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            val ai = packageManager.getPackageIntoCompat(packageName, PackageManager.GET_META_DATA).applicationInfo
            val bundle = ai.metaData
            val key = bundle.getString("mapsApiKey")
            key?.let {
                MapKitFactory.setApiKey(key)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(this, R.string.no_access, Toast.LENGTH_LONG).show()
        }
    }

    private fun PackageManager.getPackageIntoCompat(packageName: String, flags: Int = 0): PackageInfo =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
        } else {
            @Suppress("DEPRECATION") getPackageInfo(packageName, flags)
        }
}
