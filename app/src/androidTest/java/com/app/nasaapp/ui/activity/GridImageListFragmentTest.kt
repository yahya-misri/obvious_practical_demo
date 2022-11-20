package com.app.nasaapp.ui.activity


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import com.app.nasaapp.MainActivity
import com.app.nasaapp.R
import org.hamcrest.BaseMatcher
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit.rule


@LargeTest
@RunWith(AndroidJUnit4::class)
class GridImageListFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainGridFragmentTest() {
        Thread.sleep(2000)

        val recyclerView = onView(
            allOf(
                withId(R.id.rvRecyclerView),
                withParent(withParent(withId(R.id.frameLayout))),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        onView(allOf(isDisplayed(),
            first(withParent(withId(R.id.rvRecyclerView)))))
            .perform(click())

    }
    fun <T> first(matcher: Matcher<T>): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("first item")
            }

            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }


        }
    }
}
