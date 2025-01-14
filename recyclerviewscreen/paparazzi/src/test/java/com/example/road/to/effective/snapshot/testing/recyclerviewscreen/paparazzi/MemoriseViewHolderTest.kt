package com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi

import android.view.View
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.MemoriseTestItemGenerator.generateMemoriseItem
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.memorisetext.MemoriseViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.R
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.PhoneOrientation
import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.paparazzi.utils.setPhoneOrientation
import org.junit.Rule
import org.junit.Test

/**
 * Example with ActivityScenarioForViewRule of AndroidUiTestingUtils
 */
class MemoriseViewHolderHappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig = DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                screenHeight = 1,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.memorise_row)
        val view = MemoriseViewHolder(
            container = layout,
            itemEventListener = null,
            animationDelay = 0L
        ).apply {
            bind(generateMemoriseItem(rightAligned = false, activity = paparazzi.context))
        }

        paparazzi.snapshot(
            view = view.itemView,
            name = "MemoriseView_Happy"
        )
    }
}

/**
 * Example with ActivityScenarioConfigurator.ForView() of AndroidUiTestingUtils
 *
 * This is an alternative if we cannot use ActivityScenarioForViewRule()
 */
class MemoriseViewHolderUnhappyPathTest {
    @get:Rule
    val paparazzi =
        Paparazzi(
            deviceConfig =
            DeviceConfig.PIXEL_5.copy(
                softButtons = false,
                nightMode = NightMode.NIGHT,
                locale = "en",
            ).setPhoneOrientation(PhoneOrientation.LANDSCAPE),
            theme = "Theme.RoadToEffectiveSnapshotTesting",
            renderingMode = SessionParams.RenderingMode.V_SCROLL,
        )

    @Test
    fun snapViewHolder() {
        val layout = paparazzi.inflate<View>(R.layout.memorise_row)

        val viewHolder =
            MemoriseViewHolder(
                container = layout,
                itemEventListener = null,
                animationDelay = 0L
            ).apply {
                bind(generateMemoriseItem(rightAligned = true, activity = paparazzi.context))
            }

        paparazzi.snapshot(
            view = viewHolder.itemView,
            name = "MemoriseView_Unhappy"
        )
    }
}
