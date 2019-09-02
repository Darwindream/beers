package ferran.com.brewdog.util;

import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

/**
 *
 * Created by Ferran Peña on 12-Jan-19.
 */

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
