package com.axinalis;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class JpaAppTest extends TestCase {

    public JpaAppTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( JpaAppTest.class );
    }

    public void testApp()
    {
        assertTrue( true );
    }
}
