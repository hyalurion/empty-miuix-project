package com.hyalurion.exampleproject.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Stable string ids for the top level tabs.
 *
 * These values are persisted by [com.hyalurion.exampleproject.data.TabManager] into SharedPreferences
 * so the app restores the tab the user left off on across launches.
 */
object TabIds {
    const val SETTINGS = "settings"
}

/**
 * Base type for every destination of the app.
 *
 * Navigation 3 replaces string routes with plain Kotlin objects. Every concrete key must be
 * annotated with [Serializable] so that
 * [androidx.navigation3.runtime.rememberNavBackStack] can save and restore the back stack across
 * configuration changes and process death.
 *
 * Adding an argument to a destination is done by turning the object into a data class, e.g.
 * `@Serializable data class DetailKey(val fileName: String) : ExampleNavKey`. The argument is
 * then a real, compile time checked constructor parameter instead of a string that has to be
 * encoded into a route and parsed back out.
 */
sealed interface ExampleNavKey : NavKey

/** A destination that is reachable from the floating bottom bar. */
sealed interface TabNavKey : ExampleNavKey {
    /** Stable id used for persistence, see [TabIds]. */
    val tabId: String
}

@Serializable
data object SettingsKey : TabNavKey {
    override val tabId: String get() = TabIds.SETTINGS
}

/** Open source licenses, pushed on top of [SettingsKey]. */
@Serializable
data object LicensesKey : ExampleNavKey

/** Tabs in the order they are rendered by the floating bottom bar. */
val TAB_KEYS: List<TabNavKey> = listOf(SettingsKey)

/** Maps a persisted [TabIds] value back to its key, falling back to [SettingsKey]. */
fun tabKeyOf(tabId: String?): TabNavKey = TAB_KEYS.firstOrNull { it.tabId == tabId } ?: SettingsKey
