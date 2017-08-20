package org.hongjie.transformer.socle.util;

import lombok.val;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceUtils {

    public static Path getResource(String pathName) throws URISyntaxException {
        val resource = ResourceUtils.class.getClassLoader().getResource(pathName);

        val uri = resource.toURI();
        return Paths.get(uri);
    }

    public static String readResource(String pathName) throws URISyntaxException, IOException {
        Path path = getResource(pathName);
        return new String(Files.readAllBytes(path));
    }

}
