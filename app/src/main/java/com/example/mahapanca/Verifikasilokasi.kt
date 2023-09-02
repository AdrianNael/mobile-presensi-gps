package com.example.mahapanca

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class Verifikasilokasi : AppCompatActivity() {
    private var smf: SupportMapFragment? = null
    private var client: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifikasilokasi)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        smf = supportFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?
        client = LocationServices.getFusedLocationProviderClient(this)
        Dexter.withContext(applicationContext)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permissionGrantedResponse: PermissionGrantedResponse) {
                    getMyLocation()
                }

                override fun onPermissionDenied(permissionDeniedResponse: PermissionDeniedResponse) {}
                override fun onPermissionRationaleShouldBeShown(
                    permissionRequest: PermissionRequest,
                    permissionToken: PermissionToken
                ) {
                    permissionToken.continuePermissionRequest()
                }
            }).check()
        val konfirmasiButton = findViewById<Button>(R.id.konfrimasibutton)
        konfirmasiButton.setOnClickListener { showConfirmationDialog() }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this, R.style.RoundedCornerAlertDialogStyle)
        builder.setTitle("Konfirmasi")
        builder.setMessage("Apakah Anda yakin dengan data ini?\n\nJika di luar area kantor, berikan alasan:")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(convertDpToPixel(20), convertDpToPixel(20), convertDpToPixel(20), convertDpToPixel(20))
        layout.layoutParams = layoutParams

        val checkBoxWfh = CheckBox(this)
        checkBoxWfh.text = "WFH"
        checkBoxWfh.setTextColor(Color.WHITE)
        val checkBoxLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        checkBoxLayoutParams.setMargins(convertDpToPixel(20), convertDpToPixel(10), convertDpToPixel(20), convertDpToPixel(0))
        checkBoxWfh.layoutParams = checkBoxLayoutParams
        layout.addView(checkBoxWfh)

        val checkBoxWorkRemote = CheckBox(this)
        checkBoxWorkRemote.text = "Work Remote"
        checkBoxWorkRemote.setTextColor(Color.WHITE)
        checkBoxWorkRemote.layoutParams = checkBoxLayoutParams
        layout.addView(checkBoxWorkRemote)

        val input = EditText(this)
        input.setTextColor(Color.WHITE)
        input.setHint("Tuliskan alasan di sini")
        input.setHintTextColor(Color.WHITE)

        val inputLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        inputLayoutParams.setMargins(convertDpToPixel(20), convertDpToPixel(10), convertDpToPixel(20), convertDpToPixel(20))
        input.layoutParams = inputLayoutParams

        layout.addView(input)

        builder.setView(layout)

        builder.setPositiveButton("Kirim") { dialogInterface, i ->
            var alasan = ""
            if (checkBoxWfh.isChecked) {
                alasan += "WFH "
            }
            if (checkBoxWorkRemote.isChecked) {
                alasan += "Work Remote"
            }
            val alasanTambahan = input.text.toString()
            if (alasanTambahan.isNotEmpty()) {
                alasan += "\nAlasan Tambahan: $alasanTambahan"
            }
            navigateToNextActivity(alasan)
            Toast.makeText(this, "Berhasil Presensi", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Batal", null)

        val dialog = builder.create()
        dialog.show()

        // Mengubah warna tombol "Kirim" dan "Batal" menjadi putih
        val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton.setTextColor(Color.GREEN)
        val negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
        negativeButton.setTextColor(Color.RED)
    }


    private fun navigateToNextActivity(alasan: String) {
        // Gunakan alasan yang telah diisi sesuai kebutuhan
        val intent = Intent(this@Verifikasilokasi, Verifikasifoto_2::class.java)
        intent.putExtra("alasan", alasan)
        startActivity(intent)
    }

    private fun convertDpToPixel(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }


    private fun navigateToNextActivity() {
        val intent = Intent(this@Verifikasilokasi, Verifikasifoto_2::class.java)
        startActivity(intent)
    }

    private fun getMyLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        client?.lastLocation?.addOnSuccessListener(this) { location: Location? ->
            location?.let {
                smf?.getMapAsync { googleMap: GoogleMap ->
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    val markerOptions =
                        MarkerOptions().position(currentLatLng).title("Lokasi Anda Sekarang !")
                    googleMap.addMarker(markerOptions)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 17f))

                    val destinationLatLng = LatLng(
                        -6.34498855726892, 106.80946439408726)

                    val officeMarkerOptions = MarkerOptions()
                        .position(destinationLatLng)
                        .title("Kantor")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    googleMap.addMarker(officeMarkerOptions)
                    val radiusInMeters = 150.0 // Radius dalam satuan meter
                    val circleOptions = CircleOptions()
                        .center(destinationLatLng)
                        .radius(radiusInMeters)
                        .strokeWidth(1f)
                        .fillColor(0x220000FF)
                    // Sesuaikan warna dan transparansi sesuai kebutuhan
                    googleMap.addCircle(circleOptions)
                }
            }
        }
    }
}
