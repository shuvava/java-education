package org.shuvava.syntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceLoader {
  private final String resourceFile = "ResourceLoader.txt";

  public String loadResource(String name) throws IOException {
      InputStream stream = ResourceLoader.class.getResourceAsStream("/"+resourceFile);
      String data = readFromInputStream(stream);
      return data;
  }

  private String readFromInputStream(InputStream inputStream) throws IOException {
    if (inputStream == null) {
      return "";
    }
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

  public String loadResourceV2() throws IOException, URISyntaxException {
    Path path = Paths.get(getClass().getClassLoader()
        .getResource(resourceFile).toURI());

    Stream<String> lines = Files.lines(path);
    String data = lines.collect(Collectors.joining("\n"));
    lines.close();
    return data;
  }
}
