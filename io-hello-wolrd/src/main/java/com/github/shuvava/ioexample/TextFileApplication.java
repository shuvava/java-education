package com.github.shuvava.ioexample;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TextFileApplication {

  public static void main(String[] args) throws IOException {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));

    String content = getFileContent(null);
    System.out.println("Characters: " + content.length());

    List<String> lines = getFileLines(null);
    System.out.println("Lines: " + lines.size());

    Path path = getFilePath();
    try (Stream<String> lineStream = Files.lines(path, StandardCharsets.UTF_8)) {
      System.out.println("Average line length: " + lineStream.mapToInt(String::length).average().orElse(0));
    }
  }

  public static Path getFilePath() {
    final Path path = Paths.get("./data/alice.txt");
    return path;
  }

  public static String getFileContent(Path path) throws IOException {
    if (path == null) {
      path = getFilePath();
    }
    String content = Files.readString(path);
    return content;
  }

  public static List<String> getFileLines(Path path) throws IOException {
    if (path == null) {
      path = getFilePath();
    }
    List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    return lines;
  }
}
