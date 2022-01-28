package com.theapache64.composeandroidtemplate.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theapache64.composeandroidtemplate.BuildConfig
import com.theapache64.composeandroidtemplate.util.flow.mutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val SPLASH_DURATION_IN_MILLIS = 1500L
    }

    private val _versionName = MutableStateFlow("v${BuildConfig.VERSION_NAME}")
    val versionName : StateFlow<String> = _versionName

    private val _isSplashFinished = mutableEventFlow<Boolean>()
    val isSplashFinished : SharedFlow<Boolean> = _isSplashFinished

    init {
        viewModelScope.launch {
            delay(SPLASH_DURATION_IN_MILLIS)
            _isSplashFinished.tryEmit(true)
        }
    }
}