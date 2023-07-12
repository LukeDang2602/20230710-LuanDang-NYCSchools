package com.example.a20230710_luandang_nycschools

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.a20230710_luandang_nycschools.ui.theme.LuanDangNYCSchoolsTheme
import org.junit.Rule
import org.junit.Test

class SchoolScreenComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMySchoolCard(){
        composeTestRule.setContent {
            LuanDangNYCSchoolsTheme {
                MainApp()
            }
        }
        composeTestRule.onNodeWithTag("schoolCard").performClick()
        composeTestRule.onNodeWithTag("SAT Column").assertIsDisplayed()
    }
}