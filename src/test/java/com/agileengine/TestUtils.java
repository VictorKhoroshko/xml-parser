package com.agileengine;

import java.io.*;
import java.nio.file.*;

/**
 * Created by User on 02-Dec-18.
 */
public class TestUtils {
   public static Path findFile(String originalFile) throws IOException {
      return
            Files.find(Paths.get("."),
                  5,
                  (path, basicFileAttributes) -> path.endsWith(originalFile)
            ).findFirst()
                  .orElseThrow(() -> new IllegalArgumentException("File is not found " + originalFile));
   }
}
