package keluskar.sanidhya.findacat

import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.support.annotation.IntegerRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import org.jetbrains.anko.toast
import java.util.*
import kotlin.concurrent.timerTask


 class LocationDetector(private val context: Context) {
    var mylatitude: Double = 0.0
    var myLogitude: Double = 0.0
     companion object {
          var postalCode:String=""
     }

    lateinit var geocoder: Geocoder
    lateinit var addresscode: List<Address>
    val fusedLocationClient: FusedLocationProviderClient

    init {
        fusedLocationClient = FusedLocationProviderClient(context)
    }

    enum class FailureReason {
        TIMEOUT,
        NO_PERMISSION
    }

    var locationListener: LocationListener? = null

    interface LocationListener {
        fun locationFound(location: Location)
        fun locationNotFound(reason: FailureReason)
    }

    fun detectLocation() {

        val locationRequest = LocationRequest()

        val permissionResult = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)


        if(permissionResult == PackageManager.PERMISSION_GRANTED) {

            val timer = Timer()


            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {

                    fusedLocationClient.removeLocationUpdates(this)
                    var location=locationResult!!.locations.get(locationResult!!.locations.size-1);

                    mylatitude=location.latitude.toDouble()
                    myLogitude=location.longitude.toDouble()

                    timer.cancel()
                    getZipCode()

                    locationListener?.locationFound(locationResult.locations.first())
                }
            }


            timer.schedule(timerTask {

                fusedLocationClient?.removeLocationUpdates(locationCallback)

                (context as AppCompatActivity).runOnUiThread { locationListener?.locationNotFound(FailureReason.TIMEOUT) }
            }, 5*1000) //5 seconds



            fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, null)
        }
        else {

            locationListener?.locationNotFound(FailureReason.NO_PERMISSION)
        }
    }

    fun getZipCode(){
        geocoder= Geocoder(context,Locale.getDefault())
        addresscode=geocoder.getFromLocation(mylatitude,myLogitude,1)
        postalCode = addresscode.get(0).getPostalCode()

    }

}