package com.ApiCallingExample.demo.controller;

import com.ApiCallingExample.demo.model.Student;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClient2 {

    public static void main(String[] args) {
        //callGetAllStudents();
        callSaveStudent();
    }

    public static String callGetAllStudents(){
        DefaultHttpClient httpClient = new DefaultHttpClient();

        String url = "http://localhost:8080/api/student";
        HttpGet getRequest = new HttpGet(url);

        try{
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();

            HttpEntity httpEntity = response.getEntity();
            String result = EntityUtils.toString(httpEntity);

            if(statusCode==201){
                System.out.println(result);
            }
            else{
                System.out.println(response);
            }
            return result;
        }
        catch (Exception e){
            return e.toString();
        }
    }

    public static void callSaveStudent(){
        DefaultHttpClient httpClient = new DefaultHttpClient();

        String url = "http://localhost:8080/api/student";
        HttpPost postRequest = new HttpPost(url);

        Student student = new Student("Raju","Rani","raju@gmail.com");
        System.out.println(student.toString());
    }
}
