package com.magrathea.awesomerecipes.di

import androidx.annotation.IdRes
import com.magrathea.awesomerecipes.R
import com.magrathea.awesomerecipes.util.FragmentUtil
import com.magrathea.awesomerecipes.util.FragmentUtilImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class UtilModule {

    @IdRes
    @Provides
    @Named("container_id")
    fun provideContainerId(): Int {
        return R.id.nav_host_fragment
    }

    @Provides
    fun provideFragmentUtil(@Named("container_id") @IdRes containerId: Int): FragmentUtil {
        return FragmentUtilImpl(containerId = containerId)
    }
}