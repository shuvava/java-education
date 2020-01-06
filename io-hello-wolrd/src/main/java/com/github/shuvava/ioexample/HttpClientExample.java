package com.github.shuvava.ioexample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;

public class HttpClientExample {

  public static void main(String[] args) throws IOException, InterruptedException {
    // example from https://openjdk.java.net/groups/net/httpclient/intro.html

    GetExample();
    PostExample();
  }

  public static void GetExample() {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://openjdk.java.net/"))
        .build();
    client.sendAsync(request, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();
  }

  public static void PostExample() throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://openjdk.java.net/"))
        .timeout(Duration.ofMinutes(1))
        .header("Content-Type", "application/json")
        //.POST(BodyPublishers.ofFile(Paths.get("file.json")))
        .POST(BodyPublishers.ofString("test"))
        .build();

    //async
//    client.sendAsync(request, BodyHandlers.ofString())
//        .thenApply(response -> { System.out.println(response.statusCode());
//          return response; } )
//        .thenApply(HttpResponse::body)
//        .thenAccept(System.out::println);


    // sync
    HttpResponse<String> response =
      client.send(request, BodyHandlers.ofString());
      System.out.println(response.statusCode());
      System.out.println(response.body());

  }

}
