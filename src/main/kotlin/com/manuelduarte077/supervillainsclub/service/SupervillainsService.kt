package com.manuelduarte077.supervillainsclub.service

import com.manuelduarte077.supervillainsclub.model.RegexValidator
import com.manuelduarte077.supervillainsclub.model.Supervillain

class SupervillainsService {
  companion object Factory {
    fun createAccount(name: String,
                      description: String,
                      supervillains: MutableList<Supervillain>): Boolean {
      if (RegexValidator.validateName(name)) {
        supervillains.add(Supervillain(name, description))
        return true
      }
      return false
    }

    fun filterImpostors(supervillainsWithImpostors: String): String {
      return RegexValidator.filterNames(supervillainsWithImpostors).joinToString()
    }

    fun extractNamesFromHeroes(heroes: String): List<String> {
      return RegexValidator.extractNames(heroes)
    }

    fun replaceNamesForHeroes(heroes: String): String {
      return RegexValidator.replaceNames(heroes)
    }

    fun extractMeals(diet_plan: String): List<String> {
      return RegexValidator.extractMealsFromHtml(diet_plan)
    }

    fun splitSentences(sentences: String): List<String> {
      return RegexValidator.splitSentences(sentences)
    }
  }
}
