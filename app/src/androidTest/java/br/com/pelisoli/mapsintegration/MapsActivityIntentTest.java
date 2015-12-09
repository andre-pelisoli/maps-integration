package br.com.pelisoli.mapsintegration;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.pelisoli.mapsintegration.activities.MapsActivity;
import br.com.pelisoli.mapsintegration.activities.SearchActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.*;

/**
 * Created by pelisoli on 06/12/15.
 */
@RunWith(AndroidJUnit4.class)
public class MapsActivityIntentTest {

	private static final String ADDRESS = "Avenida Nações Unidas";

	private static final String LOCATION_NAME = "Av. Nações Unidas, Bauru - SP, Brazil";

	private static final double LATITUDE = -22.3317676;

	private static final double LONGITUDE = -49.0566666;

	@Rule
	public IntentsTestRule<SearchActivity> mActivityRule =
			new IntentsTestRule<>(SearchActivity.class);


	@Test
	public void triggerIntentTest() {
		onView(withId(R.id.edtSearch)).perform(replaceText(ADDRESS), closeSoftKeyboard());
		onView(withId(R.id.btnSearch)).perform(click());

		onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
		intended(hasComponent(MapsActivity.class.getName()));
	}

	@Test
	public void bundleTest(){
		onView(withId(R.id.edtSearch)).perform(replaceText(ADDRESS), closeSoftKeyboard());
		onView(withId(R.id.btnSearch)).perform(click());

		onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

		intended(hasExtras(allOf(hasEntry(equalTo("locationName"), equalTo(LOCATION_NAME)),
				hasEntry(equalTo("lat"), equalTo(LATITUDE)),
				hasEntry(equalTo("lng"), equalTo(LONGITUDE)))));
	}

}
