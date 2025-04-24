package com.example.bmicalculatorplus.ui.dashboard;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bmicalculatorplus.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Testy UI dla {@link DashboardFragment}.
 */
@RunWith(AndroidJUnit4.class)
public class DashboardFragmentTest {


    /**
     * Uruchamia fragment {@link DashboardFragment} przed każdym testem.
     */
    @Before
    public void launchFragment() {
        FragmentScenario.launchInContainer(DashboardFragment.class, null, R.style.Theme_BmiCalculatorPlus);
    }


    /**
     * Sprawdza, czy po wprowadzeniu prawidłowych danych i kliknięciu przycisku obliczania,
     * w polu wyniku wyświetlana jest wartość zawierająca "kcal".
     */
    @Test
    public void shouldDisplayCaloriesAfterValidInput() {
        onView(withId(R.id.editWeight)).perform(typeText("70"), closeSoftKeyboard());
        onView(withId(R.id.editHeight)).perform(typeText("175"), closeSoftKeyboard());
        onView(withId(R.id.age)).perform(typeText("25"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.textResult)).check(matches(withText(containsString("kcal"))));
    }
}
