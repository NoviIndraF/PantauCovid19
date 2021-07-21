package com.nifcompany.pantaucovid19.core.di

import androidx.room.Room
import com.nifcompany.pantaucovid19.core.data.source.local.room.CovidDatabase
import com.nifcompany.pantaucovid19.core.data.source.remote.RemoteDataSource
import com.nifcompany.pantaucovid19.core.data.source.remote.network.ApiService
import com.nifcompany.pantaucovid19.core.domain.repository.ICovidRepository
import com.nifcompany.pantaucovid19.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<CovidDatabase>().covidDao() }
    single {
        val passphrase: ByteArray= SQLiteDatabase.getBytes("pantaucovid".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CovidDatabase::class.java, "Covid"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "tourism-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/pvk3g76Lgd71C8n6o3RZOIM4+yWhIlyaJh5Nw97XYE0=")
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
            .baseUrl("https://apicovid19indonesia-v2.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { com.nifcompany.pantaucovid19.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICovidRepository> {
        com.nifcompany.pantaucovid19.core.data.CovidRepository(
            get(),
            get(),
            get()
        )
    }
}