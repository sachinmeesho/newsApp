package com.example.sachinchandil.newsapp.modules.headlines


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.example.sachinchandil.newsapp.NewsIdlingResource
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.database.entities.ArticlesItem
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HeadLinesActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HeadLinesActivity::class.java)
    private lateinit var idlingResource: NewsIdlingResource<List<ArticlesItem>>

    @Before
    fun setUp() {
        idlingResource = NewsIdlingResource(
            "headlines",
            mActivityTestRule.activity.mViewModel.viewState, mActivityTestRule.activity
        )
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun headLinesActivityTest_recyclerViewExists() {
        val recyclerView = onView(allOf(withId(R.id.rvNewsList), isDisplayed()))
        recyclerView.check(matches(isDisplayed()))

    }

//    @Test
//    fun headLinesActivityTest_shareNews() {
//        val recyclerView = onView(allOf(withId(R.id.rvNewsList), isDisplayed()))
//        recyclerView.check(matches(isDisplayed()))
//
//        //onView(withId(R.id.rvNewsList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.ivShare)))
//    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }


    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

}
