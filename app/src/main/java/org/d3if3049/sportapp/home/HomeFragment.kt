package org.d3if3049.sportapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import org.d3if3049.sportapp.R
import org.d3if3049.sportapp.core.data.Resource
import org.d3if3049.sportapp.core.ui.SportAdapter
import org.d3if3049.sportapp.detail.DetailSportActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val sportAdapter = SportAdapter()
            sportAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailSportActivity::class.java)
                intent.putExtra(DetailSportActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.sport.observe(viewLifecycleOwner, Observer { sport ->
                if (sport != null) {
                    when (sport) {
                        is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_bar.visibility = View.GONE
                            sportAdapter.setData(sport.data)
                        }
                        is Resource.Error -> {
                            progress_bar.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = sport.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(rv_sport) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = sportAdapter
            }
        }
    }
}