package com.ahmed.ntg.di

import com.ahmed.ntg.BuildConfig
import com.ahmed.ntg.common.Constants
import com.ahmed.ntg.data.remote.FixerApi
import com.ahmed.ntg.data.repository.FixerRepositoryImpl
import com.ahmed.ntg.domain.repository.FixerRepository
import com.ahmed.ntg.domain.use_case.FixerUserCase
import com.ahmed.ntg.domain.use_case.GetTimeSeries
import com.ahmed.ntg.domain.use_case.getLatestUseCase
import com.ahmed.ntg.domain.use_case.getSymbolsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): FixerApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .addInterceptor { chain ->
                        val request: Request =
                            chain.request()
                                .newBuilder()
                                .addHeader(Constants.API_KEY, BuildConfig.API_KEY)
                                .build()
                        chain.proceed(request)
                    }.build()
            ).build().create()
    }


    @Singleton
    @Provides
    fun provideMainRepository(api: FixerApi): FixerRepository = FixerRepositoryImpl(api)


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: FixerRepository): FixerUserCase {
        return FixerUserCase(
            getSymbolsUseCase = getSymbolsUseCase(repository),
            getLatestUseCase = getLatestUseCase(repository),
            getTimeSeries = GetTimeSeries(repository),

            )
    }


}