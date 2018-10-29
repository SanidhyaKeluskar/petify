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

    //enum to describe reasons location detection might fail
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
        //create location request
        val locationRequest = LocationRequest()

        //check for location permission
        val permissionResult = ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        //if location permission granted, proceed with location detection
        if(permissionResult == PackageManager.PERMISSION_GRANTED) {
            //create timer
            val timer = Timer()

            //create location detection callback
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    //stop location updates
                    fusedLocationClient.removeLocationUpdates(this)
                    var location=locationResult!!.locations.get(locationResult!!.locations.size-1);

                    mylatitude=location.latitude.toDouble()
                    myLogitude=location.longitude.toDouble()
                  //  Log.d("hii from method",myLogitude)
                  //  Log.d("hii from method",mylatitude)


                    //cancel timer
                    timer.cancel()
                    getZipCode()

                    //fire callback with location
                    locationListener?.locationFound(locationResult.locations.first())
                }
            }

            //start a timer to ensure location detection ends after 10 seconds
            timer.schedule(timerTask {
                //if timer expires, stop location updates and fire callback
                fusedLocationClient?.removeLocationUpdates(locationCallback)
                //run on UI thread
                (context as AppCompatActivity).runOnUiThread { locationListener?.locationNotFound(FailureReason.TIMEOUT) }
            }, 5*1000) //5 seconds


            //start location updates
            fusedLocationClient.requestLocationUpdates(locationRequest,locationCallback, null)
        }
        else {
            //else if no permission, fire callback
            locationListener?.locationNotFound(FailureReason.NO_PERMISSION)
        }
    }

    fun getZipCode(){
        geocoder= Geocoder(context,Locale.getDefault())
        addresscode=geocoder.getFromLocation(mylatitude,myLogitude,1)
        postalCode = addresscode.get(0).getPostalCode()
        Log.d("hii",postalCode)
    }

}