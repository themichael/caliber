package com.revature.caliber.security.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Project: caliber
 *
 * @author d4k1d23
 * @date 1/25/17
 */
@Component
public abstract class Helper {
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private static final Logger log = Logger.getLogger(Helper.class);

    public String toJsonString (InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        stringBuilder = new StringBuilder();
        String inputString;

        try {
            while ((inputString = bufferedReader.readLine()) != null)
                stringBuilder.append(inputString);
        } catch (IOException e) {
        	log.error("Unable to read input String: " + e + " " + e.getClass() + " " + e.getMessage());	
            return null;
        }
        closeStream();
        return stringBuilder.toString();
    }

    public void closeStream(){
        try {
            bufferedReader.close();
        } catch (IOException e) {
        	log.error("Unable to close reader: " + e + " " + e.getClass() + " " + e.getMessage());
        }
    }
}
