/*
 * Copyright SoftInstigate srl. All Rights Reserved.
 *
 *
 * The copyright to the computer program(s) herein is the property of
 * SoftInstigate srl, Italy. The program(s) may be used and/or copied only
 * with the written permission of SoftInstigate srl or in accordance with the
 * terms and conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied. This copyright notice must not be removed.
 */
package com.softinstigate.test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.softinstigate.undertowissue1317.Server;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Andrea Di Cesare <andrea@softinstigate.com>
 */
public class TestIssue1317 {
    Server m; 
    
    @Before
    public void start() throws InterruptedException {
        m = new Server();
        Thread.sleep(1000);
    }
    
    @After
    public void stop() {
        if (m != null) {
            m.stop();
        }
    }
    
    @Test
    public void testIssue1317() throws UnirestException {
        HttpResponse resp = Unirest
                .get("http://localhost:8080/one/foo")
                .asString();

        Assert.assertEquals("check response", 200, resp.getStatus());
    }
    
    
    @Test
    public void testIssue1317Relative() throws UnirestException {
        HttpResponse resp = Unirest
                .get("http://localhost:8080/two/foo")
                .asString();

        Assert.assertEquals("check response", 200, resp.getStatus());
    }
}
