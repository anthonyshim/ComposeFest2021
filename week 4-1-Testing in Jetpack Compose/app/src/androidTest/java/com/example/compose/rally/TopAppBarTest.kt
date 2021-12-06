package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.overview.OverviewBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(RallyActivity::class.java)

//    @Test
//    fun rallyTopAppBarTest() {
//        val allScreen = RallyScreen.values().toList()
//        composeTestRule.setContent {
//            RallyTopAppBar(allScreens = allScreen, onTabSelected = {}, currentScreen = RallyScreen.Accounts)
//        }
//        Thread.sleep(5000)
//    }
//
//    @Test
//    fun rallyTopAppBarTest_currentTabSelected() {
//        val allScreen = RallyScreen.values().toList()
//        composeTestRule.setContent {
//            RallyTopAppBar(allScreens = allScreen, onTabSelected = {}, currentScreen = RallyScreen.Accounts)
//        }
//        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()
//    }
//
//    @Test
//    fun rallyTopAppBarTest_currentLabelExists() {
//        val allScreen = RallyScreen.values().toList()
//        composeTestRule.setContent {
//            RallyTopAppBar(allScreens = allScreen, onTabSelected = {}, currentScreen = RallyScreen.Accounts)
//        }
//        composeTestRule.onRoot().printToLog("currentLabelExists")
//        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertExists()
//    }
//
//    @Test
//    fun rallyTopAppBarTest_currentLabelExists() {
//        val allScreen = RallyScreen.values().toList()
//        composeTestRule.setContent {
//            RallyTopAppBar(allScreens = allScreen, onTabSelected = {}, currentScreen = RallyScreen.Accounts)
//        }
//        composeTestRule.onNode(hasText(RallyScreen.Accounts.name.uppercase()) and hasParent(hasContentDescription(RallyScreen.Accounts.name)), useUnmergedTree = true).assertExists()
//    }
//
//    @Test
//    fun overviewScreen_alertDisplayed() {
//        composeTestRule.setContent {
//            OverviewBody()
//        }
//        composeTestRule.onNodeWithText("Alerts").assertIsDisplayed()
//    }

    @Test
    fun rallyTopAppBarTest_changesTheSelection() {
        val allScreen = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(allScreens = allScreen, onTabSelected = {}, currentScreen = RallyScreen.Overview)
        }
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).performClick()
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsDisplayed()
    }
}