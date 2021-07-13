package app;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoreTest {

    private final CoreLogic core = new CoreLogic();

    @Test
    public void testCToF() {
        assertThat(core.convertCToF(10), is(closeTo(50.0)));
        assertThat(core.convertCToF(-100), is(closeTo(-148.0)));
    }

    @Test
    public void testCToK() {
        assertThat(core.convertCToK(10), is(closeTo(283.15)));
        assertThat(core.convertCToK(-100), is(closeTo(173.15)));
        assertThat(core.convertCToK(-1000), is(closeTo(-726.85)));
    }

    @Test
    public void testFToC() {
        assertThat(core.convertFToC(50), is(closeTo(10.0)));
        assertThat(core.convertFToC(-148), is(closeTo(-100.0)));
    }

    @Test
    public void testFToK() {
        assertThat(core.convertFToK(1), is(closeTo(255.927778)));
        assertThat(core.convertFToK(-1000), is(closeTo(-300.183333)));
    }

    private Matcher<Double> closeTo(double value) {
        double precision = 0.001;

        return Matchers.closeTo(value, precision);
    }
}
