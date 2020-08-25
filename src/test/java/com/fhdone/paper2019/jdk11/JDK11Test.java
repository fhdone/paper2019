package com.fhdone.paper2019.jdk11;

import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nonnull;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://jimolonely.github.io/2018/09/08/java/005-jdk11-new-feature/
 */
public class JDK11Test {

    @Test
    public void testString() {
        String s = " Jimo ";
        Assert.assertEquals("Jimo", s.strip());
        Assert.assertEquals("Jimo ", s.stripLeading());
        Assert.assertEquals(" Jimo", s.stripTrailing());
        Assert.assertFalse(s.isBlank());
        Assert.assertEquals(" Jimo  Jimo  Jimo ", s.repeat(3));

        var lines = "I'm a super man\ndo you know me?\n";
        final Stream<String> stringStream = lines.lines();
        System.out.println(stringStream.collect(Collectors.toList()));
        /*[I'm a super man, do you know me?]*/
    }

    @Test
    public void testFile() throws IOException {
        final Path path = Path.of("test.txt");
        Files.writeString(path, "Ha jimo");
        Assert.assertEquals("Ha jimo", Files.readString(path));

        Assert.assertTrue(Files.isSameFile(path, path));
    }

    @Test
    public void testPredicate() {
        final long count = Stream.of("a", "b", "")
                .filter(Predicate.not(String::isEmpty))
                .count();
        Assert.assertEquals(2, count);

        final Predicate<String> predicate =
                Pattern.compile("jimo.").asMatchPredicate();
        Assert.assertTrue(predicate.test("jimo1"));
    }

    @Test
    public void testParam() {
        final long count = Arrays
                .stream(new int[]{1, 2, 3, 4})
                .filter((@Nonnull var x) -> x > 2)
                .count();
        System.out.println(count);
    }


    @Test
    public void testGet() {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.baidu.com"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }


    @Test
    public void useTLS() throws IOException {
        String host = "www.baidu.com";
        int port = 443;
        SocketFactory basicSocketFactory = SocketFactory.getDefault();
        Socket s = basicSocketFactory.createSocket(host, port);
        /*s 是一个 TCP socket*/
        SSLSocketFactory tlsSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ss = (SSLSocket)tlsSocketFactory.createSocket(s, host, port, true);
        /*s 现在是一个 基于TCP的TLS socket*/
    }


    @Test
    public void test(){
        String s = "test";
        String s2 = "test";
        System.out.println(s.equals(s2));
        System.out.println(s.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s==s2);
    }

}
