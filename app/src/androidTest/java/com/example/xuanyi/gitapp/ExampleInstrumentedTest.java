package com.example.xuanyi.gitapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.xuanyi.gitapp", appContext.getPackageName());
    }

    @Test
    public void testNonNull() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertNotNull(appContext.fileList());
    }


    @Rule
    public ActivityTestRule<ProfileActivity> prA = new ActivityTestRule<ProfileActivity>(ProfileActivity.class);


    private ProfileActivity pActivity = null;


    @Before
    public void setUP() throws Exception{

        pActivity = prA.getActivity();

    }


    @Test
    public void testImage(){
        View view = pActivity.findViewById(R.id.headImage);
        assertNotNull(view);
    }

    @Test
    public void testmatch1(){
        TextView text = (TextView) pActivity.findViewById(R.id.nameText);
        assertEquals(text.getText(),"Xuanyi Zhu");
    }

    @Test
    public void testmatch2(){
        TextView text = (TextView) pActivity.findViewById(R.id.info_view2);
        assertEquals(text.getText(),"Followings");
    }

    @Test
    public void testmatch3(){
        Button text = (Button) pActivity.findViewById(R.id.info_but2);
        assertEquals(text.getText(),"17");
    }


    @After
    public void tearDown() throws Exception{

        pActivity = null;

    }


}
