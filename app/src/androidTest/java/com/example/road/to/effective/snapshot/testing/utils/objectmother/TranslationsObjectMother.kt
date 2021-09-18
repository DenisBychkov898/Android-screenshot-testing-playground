package com.example.road.to.effective.snapshot.testing.utils.objectmother

import com.example.road.to.effective.snapshot.testing.recyclerview.data.Language
import com.example.road.to.effective.snapshot.testing.recyclerview.data.Translation

object TranslationsObjectMother {

    fun translationsPerLang(count: Int) =
        Language.values().map { lang -> lang to translations(count) }.toMap()

    fun translation(): Translation =
        Translation("word", setOf(IntRange(0, 4)), Language.English, Language.English)

    fun translations(amount: Int): List<Translation> {
        val translation = translation()
        return mutableListOf<Translation>().apply { repeat(amount) { add(translation) } }
    }
}