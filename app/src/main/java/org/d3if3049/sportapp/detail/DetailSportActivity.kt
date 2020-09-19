package org.d3if3049.sportapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_sport.*
import kotlinx.android.synthetic.main.content_detail_tourism.*
import org.d3if3049.sportapp.R
import org.d3if3049.sportapp.core.domain.model.Sport
import org.koin.android.viewmodel.ext.android.viewModel

class DetailSportActivity : AppCompatActivity() {

    private val detailSportViewModel: DetailSportViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_sport)
        setSupportActionBar(toolbar)


        val detailSport = intent.getParcelableExtra<Sport>(EXTRA_DATA)
        showDetailSport(detailSport)
    }

    private fun showDetailSport(detailSport: Sport?) {
        detailSport?.let {
            supportActionBar?.title = detailSport.strSport
            tv_detail_description.text = detailSport.strSportDescription
            Glide.with(this@DetailSportActivity)
                .load(detailSport.strSportThumb)
                .into(text_detail_image)

            var statusFavorite = detailSport.isFavorite
            setStatusFavorite(statusFavorite)
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailSportViewModel.setFavoriteSport(detailSport, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

}