package br.com.pelisoli.mapsintegration;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.pelisoli.mapsintegration.activities.SearchActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by pelisoli on 05/12/15.
 */
@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {
	private static final String ADDRESS = "Avenida Nações Unidas";

	@Rule
	public ActivityTestRule<SearchActivity> mActivityRule = new ActivityTestRule(SearchActivity.class);

	@Test
	public void informationDisplayedTest(){
		onView(withId(R.id.edtSearch)).perform(replaceText(ADDRESS), closeSoftKeyboard());
		onView(withId(R.id.btnSearch)).perform(click());
		onView(withId(R.id.txtInfo)).check(matches(isDisplayed()));
	}

	@Test
	public void informationNotDisplayedTest(){
		onView(withId(R.id.edtSearch)).perform(replaceText(""), closeSoftKeyboard());
		onView(withId(R.id.btnSearch)).perform(click());
		onView(withText(R.string.empty_search)).check(matches(isDisplayed()));
	}

	@Test
	public void textInformationTest(){
		onView(withId(R.id.edtSearch)).perform(replaceText(ADDRESS));
		onView(withId(R.id.btnSearch)).perform(click());
		onView(withId(R.id.txtInfo)).check(matches(withText(mActivityRule.getActivity().
				getString(R.string.infoMessage) + " " + ADDRESS)));
	}
}
