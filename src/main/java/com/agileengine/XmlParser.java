package com.agileengine;

import lombok.*;
import lombok.extern.slf4j.*;
import org.jsoup.*;
import org.jsoup.nodes.*;

import java.io.*;
import java.util.*;

/**
 * Works with XML/HTML documents to find similar elements by id in the source file.
 * Takes source and modified files, source element id.
 * <p>
 * Created by User on 02-Dec-18.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class XmlParser {

   /**
    * Default charset
    */
   private static String CHARSET_NAME = "utf8";

   /**
    * Name of the source file
    */
   private String sourceFile;

   /**
    * Name of the modified file
    */
   private String modifiedFile;

   /**
    * Element id value
    */
   private String elementId;


   /**
    * Finds element in {@link #modifiedFile} by comparing element attributes took from {@link #sourceFile}.
    * Uses {@link #elementId} as element id attribute value in the source file.
    *
    * @return result of the comparison
    */
   public AttributeProcessor findElement() {
      Element originalElement = findElementById(new File(sourceFile), elementId);

      if (originalElement == null) {
         throw new IllegalArgumentException("Original element is not found by id");
      }

      Attributes attributes = originalElement.attributes();

      if (attributes.size() == 0) {
         throw new IllegalStateException("Target element doesn't contain any attributes");
      }

      Document targetDoc = newDocument(new File(modifiedFile));

      AttributeProcessor comparisonResult = findElementImpl(originalElement, targetDoc);

      if (comparisonResult.getSimilarityValue().equals(0d)) {
         throw new RuntimeException("No element matches the original attributes");
      }

      return comparisonResult;
   }

   /**
    * Iterates over <code>targetDoc</code> to find elements similar to <code>originalElement</code>
    *
    * @return result in {@link AttributeProcessor}
    */
   private AttributeProcessor findElementImpl(Element originalElement, Document targetDoc) {
      return targetDoc.body().select("*").stream()
            .map(e -> new AttributeProcessor(originalElement, e))
            .peek(AttributeProcessor::calculateSimilarity)
            .max(Comparator.comparingDouble(AttributeProcessor::getSimilarityValue))
            .orElseThrow(() -> new IllegalStateException("Failed to find any relevant element"));
   }

   private Element findElementById(File htmlFile, String elementId) {
      Document doc = newDocument(htmlFile);

      return doc.getElementById(elementId);
   }

   @SneakyThrows
   private Document newDocument(File htmlFile) {
      return Jsoup.parse(
            htmlFile,
            CHARSET_NAME,
            htmlFile.getAbsolutePath());
   }
}
