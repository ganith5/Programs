package com.pavs.rest;

import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by psachidananda on 8/13/14.
 */
public class CallColorSureServiceTest{

    private static final String inputUrl = "http://172.29.163.85/colorSureService/getTarget?deviceName=indigo-221&mediaType=mohawk&sheetSize=30.24x13.5";

    public static void main(String[] args) {

        URL url = null;

        try {
            url = new URL(inputUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            if(urlConnection.getResponseCode() == 200) {
                System.out.println("Success!!");

                readPDF(urlConnection.getInputStream());

                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private static void readPDF(InputStream urlInputStream){
        byte[] ba1 = new byte[1024];
        int baLength;
        FileOutputStream fos1 = null;
        try{
            fos1 = new FileOutputStream("download.pdf");
            while ((baLength = urlInputStream.read(ba1)) != -1) {
                fos1.write(ba1, 0, baLength);
            }
            fos1.flush();
            fos1.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

//    private static void readPDF(InputStream urlInputStream){
//        BufferedInputStream urlin = null;
//        BufferedOutputStream fileOutput = null;
//        try {
//            int bufSize = 8 * 1024;
//            urlin = new BufferedInputStream(
//                    urlInputStream,
//                    bufSize);
//            fileOutput = new BufferedOutputStream(new FileOutputStream(new File("colosure.pdf")), bufSize);
//            copyPipe(urlin, fileOutput, bufSize);
//        }
//        catch (IOException ioex) {
//            ioex.printStackTrace();
//        }
//        finally {
//            if (urlin != null) {
//                try {
//                    urlin.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//            if (fileOutput != null) {
//                try {
//                    fileOutput.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//        }
//
//    }


    public static void copyPipe(InputStream in, OutputStream out, int bufSizeHint)
            throws IOException {
        int read = -1;
        byte[] buf = new byte[bufSizeHint];
        while ((read = in.read(buf, 0, bufSizeHint)) >= 0) {
            out.write(buf, 0, read);
        }
        out.flush();
    }
}
