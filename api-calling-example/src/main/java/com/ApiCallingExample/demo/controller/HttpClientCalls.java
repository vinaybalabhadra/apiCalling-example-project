package com.ApiCallingExample.demo.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class HttpClientCalls {
    public static void main(String[] args) {
        callGetAllStudentsAPI();
        callGetStudentByIdAPI();
        //callUpdateStudentAPI();
        //callSaveStudentAPI();
        //callDeleteStudentAPI();
    }
    public static void callGetAllStudentsAPI(){
        String url = "http://localhost:8080/api/student";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                    .uri(URI.create(url))
                                    .GET()
                                    .version(java.net.http.HttpClient.Version.HTTP_2)
                                    .build();
        HttpClient httpClient = HttpClient.newBuilder()
                                    .build();
        // HttpClient httpClient = HttpClient.newHttpClient();  -- this also works fine. Can use newbuilder or newHttpClient. Anyone works fine

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void callGetStudentByIdAPI(){
        long student_id = 1;
        String url = "http://localhost:8080/api/student/"+student_id;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());
            System.out.println("\nStudent details with id=1 are:");
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void callSaveStudentAPI(){
        String url = "http://localhost:8080/api/student";
        String jsonFilePath = "/Users/balabhadravinay/Documents/Spring Boot Projects/API Calling using RestTemplate example/api-calling-example/src/main/java/com/ApiCallingExample/demo/controller/HttpClientPostCallJsonFile.json";
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of(jsonFilePath)))
                    .header("Content-Type","application/json")
                    .uri(URI.create(url))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,BodyHandlers.ofString());
            System.out.println(httpResponse.body());
            System.out.println(httpResponse.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void callUpdateStudentAPI(){
        long student_id = 8;
        String url = "http://localhost:8080/api/student/"+student_id;
        String jsonFilePath = "/Users/balabhadravinay/Documents/Spring Boot Projects/API Calling using RestTemplate example/api-calling-example/src/main/java/com/ApiCallingExample/demo/controller/HttpClientPostCallJsonFile.json";
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofFile(Path.of(jsonFilePath)))
                    .header("Content-Type","application/json")
                    .uri(URI.create(url))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,BodyHandlers.ofString());
            System.out.println(httpResponse.body());
            System.out.println(httpResponse.statusCode());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void callDeleteStudentAPI(){
        long student_id = 12;
        String url = "http://localhost:8080/api/student/"+student_id;
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpClient httpClient = HttpClient.newBuilder().build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest,BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
