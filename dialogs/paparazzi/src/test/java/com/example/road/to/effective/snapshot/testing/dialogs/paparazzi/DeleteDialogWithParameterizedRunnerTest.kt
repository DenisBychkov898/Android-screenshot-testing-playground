package com.example.road.to.effective.snapshot.testing.dialogs.paparazzi

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            softButtons = false,
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
    )

    @Test
    fun snapDialogHappyPath() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )

        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5.copy(
            softButtons = false,
            screenWidth = testItem.deleteItem.screenWidth.widthInPx,
            nightMode = testItem.deleteItem.deviceConfig.nightMode,
            fontScale = testItem.deleteItem.deviceConfig.fontScale,
        ),
        theme = "Theme.RoadToEffectiveSnapshotTesting",
    )

    @Test
    fun snapDialogUnhappyPath() {
        val dialog =
            DialogBuilder.buildDeleteDialog(
                ctx = paparazzi.context,
                onDeleteClicked = {},
                bulletTexts = itemArray(paparazzi.context, testItem.deleteItem.bulletTexts)
            )
        paparazzi.snapshot(
            view =  dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}