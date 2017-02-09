package com.warrantix.main.modules.convertor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class IntercomObjectConvertor {

    private class TestClass {

    };

    public static Object getObject(String typeMessage, String jsonMessage)
    {
        if (typeMessage.equalsIgnoreCase(TestClass.class.toString()))
        {
            // for testing :

            Gson gson = new Gson();
            Type type = new TypeToken<TestClass>() {}.getType();
            TestClass fromJson = gson.fromJson(jsonMessage, type);
            return fromJson;
        }
        else {
            ;
        }

        return new Object();
    }

    public static String getMessage(Object obj)
    {
        if (obj instanceof TestClass) {
            Gson gson = new Gson();
            Type type = new TypeToken<TestClass>() {}.getType();
            TestClass testClass = (TestClass) obj;
            String jsonMessage = gson.toJson(testClass, type);
            return jsonMessage;
        }
        else {
            ;
        }

        return "";
    }

    public static String getType(Object obj)
    {
        if (obj instanceof TestClass) {
            return TestClass.class.toString();
        }

        return "";
    }

}