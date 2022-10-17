package com.nmbs.husqvarna.ui

import com.nmbs.husqvarna.rules.MainDispatcherRule
import org.junit.Rule

open class BaseViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
}
