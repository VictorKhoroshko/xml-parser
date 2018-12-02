package com.agileengine;

import lombok.extern.slf4j.*;

import java.util.*;

/**
 * Application that compares two XML files to find similar elements defined by
 * id attribute in the source file.
 *
 * Created by User on 02-Dec-18.
 */
@Slf4j
public class XmlParserApplication {

   /**
    * Default source element id
    */
   public static final String DEFAULT_ELEMENT_ID = "make-everything-ok-button";

   public static void main(String[] args) {
      log.info("Command line args {}", Arrays.toString(args));

      if (args.length < 2 || args.length > 3) {
         throw new IllegalArgumentException("Invalid number of input parameters");
      }

      String sourceFile = args[0];
      String modifiedFile = args[1];

      String elementId;

      if (args.length == 2) {
         log.info("Element id command line argument is not specified, using a default value {}", DEFAULT_ELEMENT_ID);

         elementId = DEFAULT_ELEMENT_ID;
      } else {
         elementId = args[2];
      }

      XmlParser xmlParser = XmlParser.builder()
            .sourceFile(sourceFile)
            .modifiedFile(modifiedFile)
            .elementId(elementId)
            .build();

      AttributeProcessor result = xmlParser.findElement();

      log.debug("Processing result {}", result);

      System.out.println(String.format("Target element path %s", result.buildPath()));
      System.out.println(String.format("Overall similarity is %.2f percent", result.getSimilarityValue() * 100));
      System.out.println(String.format("Attributes similarity %s ", result.getAttributeComparisonResult()));
   }

}
