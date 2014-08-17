package com.pavs.rest;

import org.junit.Test;

/**
 * Created by psachidananda on 8/15/14.
 */
public class ColorSureTest {

    private String inputUrl = "http://172.29.163.85/colorSureService/getTarget?deviceName=indigo-221&mediaType=mohawk&sheetSize=30.24x13.5";
    private String hotFolder = "/Users/psachidananda";


    public void testServiceQuery(){
        ColorSureJob colorSureJob = new ColorSureJob(inputUrl, hotFolder);
        colorSureJob.fetchColorSureFileToHotFolder();
    }
}
