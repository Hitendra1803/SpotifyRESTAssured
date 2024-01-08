package com.spotify.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


/*
To run the test cases - pass the following path on the terminal at the root folder location
mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNTS_BASE_URI="https://accounts.spotify.com"
 */
public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TEST: "+m.getName());
        System.out.println("THREAD ID: "+Thread.currentThread().threadId());
    }
        }