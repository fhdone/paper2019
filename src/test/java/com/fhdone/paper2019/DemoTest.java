package com.fhdone.paper2019;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class DemoTest {

    @Test
    public void test(){
        Set<Integer> set = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println(set);
    }
    
    
    @Test
    public void testFile() throws Exception {
        final Path path = Path.of("testFile.txt");
        Files.writeString(path, "Ha jimo");
        Assert.assertEquals("Ha jimo", Files.readString(path));
    
        Assert.assertTrue(Files.isSameFile(path, path));
    }
}
