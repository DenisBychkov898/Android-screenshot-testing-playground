package com.example.road.to.effective.snapshot.testing.dialogs.dropshots.dialog.parameterized

import android.graphics.Color.*
import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import com.example.road.to.effective.snapshot.testing.dialogs.DialogBuilder
import com.example.road.to.effective.snapshot.testing.dialogs.dropshots.utils.DropshotsAPI29Fix
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.runners.Parameterized
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import sergio.sastre.uitesting.utils.utils.waitForMeasuredDialog

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments). Therefore, we need to create 2 different classes to separate @UnhappyPath and
 * @HappyPath tests
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+
 */
@RunWith(Parameterized::class)
class DeleteDialogParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @HappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class DeleteDialogParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem,
) {

    private val deleteItem = testItem.deleteItem

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = DropshotsAPI29Fix(
        Dropshots(resultValidator = ThresholdValidator(0.15f))
    )

    @get:Rule
    val activityScenarioForViewRule =
        ActivityScenarioForViewRule(
            config = deleteItem.viewConfig,
            backgroundColor = TRANSPARENT,
        )

    @UnhappyPath
    @Test
    fun snapDialog() {
        val activity = activityScenarioForViewRule.activity

        val dialog = waitForMeasuredDialog(exactWidthPx = deleteItem.dialogWidth.widthInPx) {
            DialogBuilder.buildDeleteDialog(
                ctx = activity,
                onDeleteClicked = {},
                bulletTexts = itemArray(activity, deleteItem.bulletTexts)
            )
        }

        dropshots.assertSnapshot(
            view = dialog.window!!.decorView,
            name = "DeleteDialog_${testItem.name}_Parameterized",
        )
    }
}
