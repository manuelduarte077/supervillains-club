
package com.manuelduarte077.supervillainsclub.controller

import com.manuelduarte077.supervillainsclub.model.Supervillain
import com.manuelduarte077.supervillainsclub.service.SupervillainsService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.support.RequestContextUtils
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpServletRequest

@Controller
class SupervillainsClubController {

  val logger = LoggerFactory.getLogger(SupervillainsClubController::class.java)

  val superVillains = mutableListOf(
      Supervillain("Dr. Very Evil", "Creating a missile to destroy the world."),
      Supervillain("Evil Hot", "Burning the world since the beginning of the time."),
      Supervillain("Dark Devil", "Destroying people's hope.")
  )

  val superVillainsWithImpostors = "Captain Marvel, Super Joke, Bad Man, Bad Woman, Captain America, Bad Bear"

  val heroes = "Captain Marvel, Captain Amazing, Captain Chocolate, Captain Perfect"

  val diet_html = """<html>
  <head>
    <title>Diet plan for Villain Numba One</title>
  </head>
  <body>
    <ul>
      <li>5kg Unicorn Meat</li><li>2L Lava</li><li>2kg Meteorite</li>
    </ul>
  </body>
</html>""".trimIndent()

  val zetaGenerationSentences = "I just beat a hero :] looks like I'm good :) I think I can beat more heroes \uD83E\uDD23 I'm the greatest villain"

  @GetMapping("")
  fun index(model: Model): String {
    model["title"] = "Welcome to the Supervillains Club"
    return "index"
  }

  @GetMapping("/register")
  fun register(request: HttpServletRequest,
               model: Model): String {
    val inputFlashMap = RequestContextUtils.getInputFlashMap(request)
    if (inputFlashMap != null) {
      val name = inputFlashMap["name"].toString()
      val description = inputFlashMap["description"].toString()
      val supervillain = Supervillain(name, description)
      model["supervillain"] = supervillain
    } else {
      val supervillain = Supervillain("", "")
      model["supervillain"] = supervillain
    }
    return "register"
  }

  @PostMapping("/register")
  fun registerPost(@RequestParam name: String,
                   @RequestParam description: String,
                   redirectAttributes: RedirectAttributes): RedirectView {
    val success = SupervillainsService.createAccount(name, description, superVillains)
    return if (success) {
      redirectAttributes.addFlashAttribute("message", "Successfully registered as a supervillain")
      RedirectView("/list", true)
    } else {
      redirectAttributes.addFlashAttribute("message", "You're not a supervillain")
      redirectAttributes.addFlashAttribute("name", name)
      redirectAttributes.addFlashAttribute("description", description)
      RedirectView("/register", true)
    }
  }

  @GetMapping("/list")
  fun list(request: HttpServletRequest, model: Model): String {
    model["supervillains"] = superVillains
    return "list"
  }

  @GetMapping("/impostors")
  fun impostor(request: HttpServletRequest, model: Model): String {
    model["supervillains_with_impostors"] = superVillainsWithImpostors
    model["impostors"] = ""
    return "impostors"
  }

  @PostMapping("/impostors")
  fun filterImpostor(request: HttpServletRequest, model: Model): String {
    model["supervillains_with_impostors"] = superVillainsWithImpostors
    model["impostors"] = SupervillainsService.filterImpostors(superVillainsWithImpostors)
    return "impostors"
  }

  @GetMapping("/extract")
  fun extractNames(request: HttpServletRequest, model: Model): String {
    model["heroes"] = heroes
    model["extracted_names"] = emptyArray<String>()
    return "extract"
  }

  @PostMapping("/extract")
  fun extractNamesPost(request: HttpServletRequest, model: Model): String {
    model["heroes"] = heroes
    model["extracted_names"] = SupervillainsService.extractNamesFromHeroes(heroes)
    return "extract"
  }

  @GetMapping("/replace")
  fun replaceNames(request: HttpServletRequest, model: Model): String {
    model["heroes"] = heroes
    model["new_names"] = ""
    return "replace"
  }

  @PostMapping("/replace")
  fun replaceNamesPost(request: HttpServletRequest, model: Model): String {
    model["heroes"] = heroes
    model["new_names"] = SupervillainsService.replaceNamesForHeroes(heroes)
    return "replace"
  }

  @GetMapping("/diet")
  fun extractMeals(request: HttpServletRequest, model: Model): String {
    model["diet_html"] = diet_html
    model["meals"] = emptyArray<String>()
    return "diet"
  }

  @PostMapping("/diet")
  fun extractMealsPost(request: HttpServletRequest, model: Model): String {
    model["diet_html"] = diet_html
    model["meals"] = SupervillainsService.extractMeals(diet_html)
    return "diet"
  }

  @GetMapping("/split")
  fun splitSentences(request: HttpServletRequest, model: Model): String {
    model["sentences_string"] = zetaGenerationSentences
    model["sentences_array"] = emptyArray<String>()
    return "split"
  }

  @PostMapping("/split")
  fun splitSentencesPost(request: HttpServletRequest, model: Model): String {
    model["sentences_string"] = zetaGenerationSentences
    model["sentences_array"] = SupervillainsService.splitSentences(zetaGenerationSentences)
    return "split"
  }
}