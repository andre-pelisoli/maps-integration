package br.com.pelisoli.mapsintegration;

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
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by pelisoli on 06/12/15.
 */
@RunWith(AndroidJUnit4.class)
public class MapsActivityIntentTest {

	private static final String ADDRESS = "Avenida Nações Unidas";

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

}
