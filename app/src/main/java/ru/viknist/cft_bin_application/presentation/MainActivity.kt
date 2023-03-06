package ru.viknist.cft_bin_application.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.viknist.cft_bin_application.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var historyList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val historyFragment = HistoryFragment()

        val binEditText = findViewById<EditText>(R.id.binEditText)
        val searchImageView = findViewById<ImageView>(R.id.searchImageView)
        val historyImageView = findViewById<ImageView>(R.id.historyImageView)
        val schemeTextView = findViewById<TextView>(R.id.schemeTextView)
        val typeTextView = findViewById<TextView>(R.id.typeTextView)
        val brandTextView = findViewById<TextView>(R.id.brandTextView)
        val prepaidTextView = findViewById<TextView>(R.id.prepaidTextView)
        val countryNameTextView = findViewById<TextView>(R.id.countryNameTextView)
        val countryAlphaTextView = findViewById<TextView>(R.id.countryAlphaTextView)
        val countryFlagTextView = findViewById<TextView>(R.id.countryFlagTextView)
        val counrtyCoordsTextView = findViewById<TextView>(R.id.coordinatesTextView)
        var latitude = 0
        var longitude = 0
        val cardNumberTextView = findViewById<TextView>(R.id.cardNumberTextView)
        val bankNameTextView = findViewById<TextView>(R.id.bankNameTextView)
        val bankUrlTextView = findViewById<TextView>(R.id.bankUrlTextView)
        val bankPhoneTextView = findViewById<TextView>(R.id.phoneTextView)
        val luhnTextView = findViewById<TextView>(R.id.luhnTextView)
        val infoLayout = findViewById<ConstraintLayout>(R.id.infoLayout)


        viewModel.binInfoLiveData.observe(this, Observer { binInfo ->
            schemeTextView.text = binInfo.scheme?.replaceFirstChar { it.uppercase() }
            typeTextView.text = binInfo.type?.replaceFirstChar { it.uppercase() }
            brandTextView.text = binInfo.brand?.replaceFirstChar { it.uppercase() }
            prepaidTextView.text =
                if (binInfo.prepaid == true) "Yes" else "No"
            countryNameTextView.text = binInfo.country?.name
            countryAlphaTextView.text = binInfo.country?.alpha2
            countryFlagTextView.text = binInfo.country?.emoji
            latitude = binInfo.country?.latitude!!
            longitude = binInfo.country.longitude!!
            counrtyCoordsTextView.text = "(latitude: $latitude, longitude: $longitude)"
            cardNumberTextView.text = binInfo.number?.length.toString()
            luhnTextView.text = if (binInfo.number?.luhn == true) "Yes" else "No"
            bankNameTextView.text = binInfo.bank?.name
            bankUrlTextView.text = binInfo.bank?.url
            bankPhoneTextView.text = binInfo.bank?.phone
            infoLayout.visibility = View.VISIBLE
        })

        viewModel.historyListLiveData.observe(this, Observer {
            historyList = it
        })

        searchImageView.setOnClickListener {
            val bin = binEditText.text.toString()
            viewModel.searchBin(bin)
        }

        historyImageView.setOnClickListener {
            historyFragment.list = historyList
            historyFragment.viewModel = viewModel
            historyFragment.show(supportFragmentManager, "History")
        }

        counrtyCoordsTextView.setOnClickListener {
            if (counrtyCoordsTextView.text != "") {
                openMap(latitude, longitude)
            }
        }

        bankPhoneTextView.setOnClickListener {
            if (bankPhoneTextView.text != "") {
                openPhone(bankPhoneTextView.text.toString())
            }
        }

        bankUrlTextView.setOnClickListener {
            if (bankUrlTextView.text != "") {
                openUrl(bankUrlTextView.text.toString())
            }
        }
    }

    fun openMap(latitude: Int, longitude: Int) {
        val geoCoords = "geo:$latitude,$longitude?z=4"
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoCoords))
        if (mapIntent.resolveActivity(packageManager) != null) {
            startActivity(mapIntent)
        }
    }

    fun openPhone(phoneNumber: String) {
        val phoneUriString = "tel:${phoneNumber}"
        val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneUriString))
        if (phoneIntent.resolveActivity(packageManager) != null) {
            startActivity(phoneIntent)
        }
    }

    fun openUrl(url: String) {
        val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://$url"))
        if (urlIntent.resolveActivity(packageManager) != null) {
            startActivity(urlIntent)
        }
    }
}