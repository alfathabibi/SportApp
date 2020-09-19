package org.d3if3049.sportapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite.*
import org.d3if3049.sportapp.core.ui.SportAdapter
import org.d3if3049.sportapp.detail.DetailSportActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"

        val sportAdapter = SportAdapter()
        sportAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailSportActivity::class.java)
            intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteSport.observe(this, Observer {
            sportAdapter.setData(it)
            view_empty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_sport) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = sportAdapter
        }
    }


}