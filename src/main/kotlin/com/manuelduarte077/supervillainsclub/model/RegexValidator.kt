package com.manuelduarte077.supervillainsclub.model

class RegexValidator {
  companion object Factory {
    fun validateName(name: String): Boolean {
      val pattern = Regex("^[bc]at(wo)?man", RegexOption.IGNORE_CASE)
      if (pattern.containsMatchIn(name)) {
        return false
      }

      return true
    }

    fun filterNames(names: String): List<String> {
      val pattern = Regex("""Captain\s[a-zA-Z_0-9]+""")
      return pattern.findAll(names).map {
        it.value
      }.toList()
    }

    fun extractNames(names: String): List<String> {
      val pattern = Regex("""Captain\s(\w+)""")
      val results = pattern.findAll(names)
      return results.map {
        it.groupValues[1]
      }.toList()
    }

    fun replaceNames(names: String): String {
      val pattern = Regex("""Captain\s(\w+)""")
      return pattern.replace(names, "Super Evil $1")
    }

    fun extractMealsFromHtml(names: String): List<String> {
      val pattern = Regex("""<li>(.*?)</li>""")
      val results = pattern.findAll(names)
      return results.map {
        it.groupValues[1]
      }.toList()
    }

    fun splitSentences(sentences: String): List<String> {
      val escapedString = Regex.escape(""":)""")
      val pattern = Regex("""(:]|${escapedString})|🤣""")
      return pattern.split(sentences).map {
        it.trim()
      }
    }
  }
}
