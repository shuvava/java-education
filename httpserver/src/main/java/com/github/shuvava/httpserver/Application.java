package com.github.shuvava.httpserver;

import static com.github.shuvava.httpserver.Configuration.getErrorHandler;
import static com.github.shuvava.httpserver.Configuration.getObjectMapper;
import static com.github.shuvava.httpserver.Configuration.getUserService;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import com.github.shuvava.httpserver.api.user.RegistrationHandler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/// mode examples can be found in https://github.com/Simonwep/java-express

/**
 * REQUESTS example
 * curl -v -X POST localhost:8000/api/hello
 * curl localhost:8000/api/hello?name=Marcin
 * curl -v localhost:8000/api/hello?name=Marcin -H 'Authorization: Basic YWRtaW46YWRtaW4='
 * curl -X POST localhost:8000/api/users/register -d '{"login": "test" , "password" : "test"}'
 */
public class Application {
  public static void main(String[] args) throws IOException {
    int serverPort = 8000;
    HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

    RegistrationHandler registrationHandler = new RegistrationHandler(
        getUserService(),
        getObjectMapper(),
        getErrorHandler()
    );
    server.createContext("/api/users/register", registrationHandler::handle);

    HttpContext context = server.createContext("/api/hello", (exchange -> {

      if ("GET".equals(exchange.getRequestMethod())) {
        Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
        String noNameText = "Anonymous";
        String name = params.getOrDefault("name", List.of(noNameText)).stream().findFirst().orElse(noNameText);
        String respText = String.format("Hello %s!", name);
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
      } else {
        exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
      }
      exchange.close();

    }));

    context.setAuthenticator(new BasicAuthenticator("myrealm") {
      @Override
      public boolean checkCredentials(String user, String pwd) {
        return user.equals("admin") && pwd.equals("admin");
      }
    });

    server.setExecutor(null); // creates a default executor
    server.start();
  }

  public static Map<String, List<String>> splitQuery(String query) {
    if (query == null || "".equals(query)) {
      return Collections.emptyMap();
    }

    return Pattern.compile("&").splitAsStream(query)
        .map(s -> Arrays.copyOf(s.split("="), 2))
        .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));
  }

  private static String decode(final String encoded) {
    try {
      return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
    } catch (final UnsupportedEncodingException e) {
      throw new RuntimeException("UTF-8 is a required encoding", e);
    }
  }
}
