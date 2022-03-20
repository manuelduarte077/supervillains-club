
package com.manuelduarte077.supervillainsclub.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegexValidatorTest {

  @Test
  fun testValidateName() {
    assertFalse(RegexValidator.validateName("batman"))
    assertFalse(RegexValidator.validateName("Batman"))
    assertFalse(RegexValidator.validateName("batwoman"))
    assertFalse(RegexValidator.validateName("catman"))
    assertFalse(RegexValidator.validateName("catwoman"))

    assertTrue(RegexValidator.validateName("superman"))
    assertTrue(RegexValidator.validateName("iron man"))
  }

  @Test
  fun testFilterNames() {
    val sentence = "Captain Marvel, Super Joke, Bad Man, Bad Woman, Captain America, Bad Bear"
    assertEquals("Captain Marvel, Captain America", RegexValidator.filterNames(sentence).joinToString())
  }

  @Test
  fun testExtractNames() {
    val sentence = "Captain Marvel, Captain Amazing, Captain Chocolate, Captain Perfect"
    assertEquals("Marvel, Amazing, Chocolate, Perfect", RegexValidator.extractNames(sentence).joinToString())
  }

  @Test
  fun testReplaceNames() {
    val sentence = "Captain Marvel, Captain Amazing, Captain Chocolate, Captain Perfect"
    assertEquals("Super Evil Marvel, Super Evil Amazing, Super Evil Chocolate, Super Evil Perfect",
      RegexValidator.replaceNames(sentence)
    )
  }

  @Test
  fun testExtractMealsFromHtml() {
    val sentence = "<li>5kg Unicorn Meat</li><li>2L Lava</li><li>2kg Meteorite</li>"
    assertEquals("5kg Unicorn Meat, 2L Lava, 2kg Meteorite",
        RegexValidator.extractMealsFromHtml(sentence).joinToString())
  }

  @Test
  fun testSplit() {
    val sentence = "I just beat a hero :] looks like I'm good :) I think I can beat more heroes \uD83E\uDD23 I'm the greatest villain"
    assertEquals("I just beat a hero, looks like I'm good, I think I can beat more heroes, I'm the greatest villain",
        RegexValidator.splitSentences(sentence).joinToString())
  }
}