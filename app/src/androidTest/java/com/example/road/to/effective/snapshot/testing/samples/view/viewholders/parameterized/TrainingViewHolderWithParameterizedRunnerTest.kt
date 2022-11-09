package com.example.road.to.effective.snapshot.testing.samples.view.viewholders.parameterized

import com.example.road.to.effective.snapshot.testing.recyclerviewscreen.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.utils.annotations.UnhappyPath
import com.example.road.to.effective.snapshot.testing.utils.waitForViewHolder
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import com.example.road.to.effective.snapshot.testing.R
import com.example.road.to.effective.snapshot.testing.utils.annotations.HappyPath

/**
 * Example of Parameterized test with Parameterized Runner.
 *
 * Unlike TestParameterInjector, the testItem is used in all @Tests (the test methods do not admit
 * arguments).
 *
 * On the other hand, ParameterizedRunner is compatible with instrumented test of any API level,
 * whereas TestParameterInjector requires API 24+, throwing
 * java.lang.NoClassDefFoundError: com.google.common.cache.CacheBuilder error in lower APIs
 */
@RunWith(Parameterized::class)
class TrainingItemParameterizedHappyPathTest(
    private val testItem: HappyPathTestItem
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val rule = ActivityScenarioForViewRule(config = testItem.item.viewConfig)

    @HappyPath
    @Test
    fun snapTrainingItem() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = "TrainingViewHolder_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class TrainingItemParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val rule = ActivityScenarioForViewRule(config = testItem.item.viewConfig)

    @UnhappyPath
    @Test
    fun snapTrainingItem() {
        val layout = rule.inflateAndWaitForIdle(R.layout.training_row)

        val viewHolder = waitForViewHolder {
            TrainingViewHolder(layout).apply {
                bind(item = testItem.item.trainingItem, languageClickedListener = null)
            }
        }

        compareScreenshot(
            holder = viewHolder,
            heightInPx = viewHolder.itemView.measuredHeight,
            name = "TrainingViewHolder_${testItem.name}_Parameterized",
        )
    }
}