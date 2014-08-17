package com.pavs.rest;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by psachidananda on 8/15/14.
 */
public class ColorSureJob {

    private final String colorSureServiceUrl;

    private final String hotFolderDir;

    public ColorSureJob(String url, String hotFolder){
        this.colorSureServiceUrl = url;
        this.hotFolderDir = hotFolder;
    }

    public void fetchColorSureFileToHotFolder(){
        try {

            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(colorSureServiceUrl);
            httpGet.addHeader("Accept", "application/json");

            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(statusCode == 200) {
                System.out.println("500");
                readPDF(httpResponse.getEntity().getContent());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private void readPDF(InputStream urlInputStream){
        byte[] bytes = new byte[1024];
        int byteLength;
        FileOutputStream fileOutputStream;
        try{
            String colorSureFileName = (hotFolderDir) + "/colorSureJob.pdf";
            fileOutputStream = new FileOutputStream(colorSureFileName);
            while ((byteLength = urlInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, byteLength);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
