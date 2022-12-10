package com.example.road.to.effective.snapshot.testing.recyclerview.dropshots.viewholders.parameterized

import com.dropbox.dropshots.Dropshots
import com.dropbox.dropshots.ThresholdValidator
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioForViewRule
import com.example.road.to.effective.snapshot.testing.recyclerview.R
import com.example.road.to.effective.snapshot.testing.recyclerview.dropshots.viewholders.waitForViewHolder
import com.example.road.to.effective.snapshot.testing.recyclerview.ui.rows.training.TrainingViewHolder
import com.example.road.to.effective.snapshot.testing.testannotations.HappyPath
import com.example.road.to.effective.snapshot.testing.testannotations.UnhappyPath

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
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<HappyPathTestItem> = HappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = viewHolder,
            name = "TrainingViewHolder_${testItem.name}_Parameterized",
        )
    }
}

@RunWith(Parameterized::class)
class TrainingItemParameterizedUnhappyPathTest(
    private val testItem: UnhappyPathTestItem
) {
    companion object {
        @JvmStatic
        @Parameters
        fun testItemProvider(): Array<UnhappyPathTestItem> = UnhappyPathTestItem.values()
    }

    @get:Rule
    val dropshots = Dropshots(resultValidator = ThresholdValidator(0.15f))

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

        dropshots.assertSnapshot(
            view = viewHolder,
            name = "TrainingViewHolder_${testItem.name}_Parameterized",
        )
    }
}

