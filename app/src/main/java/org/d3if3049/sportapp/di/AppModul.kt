package org.d3if3049.sportapp.di

import org.d3if3049.sportapp.core.domain.usecase.SportInteractor
import org.d3if3049.sportapp.core.domain.usecase.SportUseCase
import org.d3if3049.sportapp.detail.DetailSportViewModel
import org.d3if3049.sportapp.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> {
        SportInteractor(get())
    }
}

val viewModelModule = module {
    viewModel{
        HomeViewModel(get())
    }
    viewModel {
        DetailSportViewModel(get())
    }
}