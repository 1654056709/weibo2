package com.sina.weibo.sdk.simple.weibo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.orhanobut.logger.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.sina.weibo.sdk.simple.weibo", appContext.getPackageName());
    }

    @Test
    public void testOne() {
        int count = 0;
        Logger.d("tian%n", count);
        Logger.d(count);
    }
}
