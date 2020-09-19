package org.d3if3049.sportapp.core.di

import androidx.room.Room
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.d3if3049.sportapp.core.data.SportRepository
import org.d3if3049.sportapp.core.data.source.local.LocalDataSource
import org.d3if3049.sportapp.core.data.source.local.room.SportDatabase
import org.d3if3049.sportapp.core.data.source.remote.RemoteDataSource
import org.d3if3049.sportapp.core.data.source.remote.network.ApiService
import org.d3if3049.sportapp.core.domain.repository.ISportRepository
import org.d3if3049.sportapp.core.utils.AppExecutors
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<SportDatabase>().sportDao()
    }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SportDatabase::class.java, "Sport.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {

        val hostName = "www.thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/ctt1haazs8U6LJbBhG1dMDCxflw6Q5LRFqlJP+iCf3E=")
            .add(hostName, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<ISportRepository> {
        SportRepository(get(), get(), get())
    }
}