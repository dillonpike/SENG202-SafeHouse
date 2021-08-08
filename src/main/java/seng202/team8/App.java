package seng202.team8;

import com.google.gson.Gson;

/**
 * An application class aimed to test the Gson library.
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello SENG202 Team 8" );
        
        MyObject myObject = new MyObject("chair", 3);
        Gson gson = new Gson();
        String jsonString = gson.toJson(myObject);
        
        System.out.println("myObject = " + myObject);
        System.out.println("myObject stringified = " + jsonString);
    }
}
