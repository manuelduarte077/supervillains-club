
package com.manuelduarte077.supervillainsclub.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class SupervillainsClubControllerTest(@Autowired val mockMvc: MockMvc) {

  @Test
  fun register() {
    mockMvc.get("/register")
        .andExpect {
          status { isOk() }
        }
    mockMvc.post("/register") {
      contentType = MediaType.APPLICATION_FORM_URLENCODED
      param("name", "superman")
      param("description", "hero")
    }
        .andExpect {
          status { is3xxRedirection() }
          redirectedUrl("/list")
        }
        .andReturn()
        .response
        .contentAsString
        .matches(Regex("superman"))
  }

  @Test
  fun impostors() {
    mockMvc.get("/impostors")
        .andExpect {
          status { isOk() }
        }
    mockMvc.post("/impostors")
        .andExpect {
          status { isOk() }
        }
        .andReturn()
        .response
        .contentAsString
        .matches(Regex("Captain Marvel, Captain America"))
  }

  @Test
  fun extract() {
    mockMvc.get("/extract")
        .andExpect {
          status { isOk() }
        }
    val content = mockMvc.post("/extract")
        .andExpect {
          status { isOk() }
        }
        .andReturn()
        .response
        .contentAsString
    content.matches(Regex("Marvel"))
    content.matches(Regex("Amazing"))
    content.matches(Regex("Chocolate"))
    content.matches(Regex("Perfect"))
  }

  @Test
  fun replace() {
    mockMvc.get("/replace")
        .andExpect {
          status { isOk() }
        }
    mockMvc.post("/replace")
        .andExpect {
          status { isOk() }
        }
        .andReturn()
        .response
        .contentAsString
        .matches(Regex("Super Evil Marvel, Super Evil Amazing, Super Evil Chocolate, Super Evil Perfect"))
  }

  @Test
  fun extractMealsFromHtml() {
    mockMvc.get("/diet")
        .andExpect {
          status { isOk() }
        }
    mockMvc.post("/diet")
        .andExpect {
          status { isOk() }
        }
        .andReturn()
        .response
        .contentAsString
        .matches(Regex("<li>2L Lava</li>"))
  }

  @Test
  fun split() {
    mockMvc.get("/split")
        .andExpect {
          status { isOk() }
        }
    val content = mockMvc.post("/split")
        .andExpect {
          status { isOk() }
        }
        .andReturn()
        .response
        .contentAsString
    content.matches(Regex("I just beat a hero"))
    content.matches(Regex("looks like I'm good"))
    content.matches(Regex("I think I can beat more heroes"))
    content.matches(Regex("I'm the greatest villain"))
  }

}