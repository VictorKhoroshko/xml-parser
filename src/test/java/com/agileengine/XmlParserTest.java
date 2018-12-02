package com.agileengine;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by User on 02-Dec-18.
 */
public class XmlParserTest {

   @Test
   public void test1() throws Exception {
      doFindElementTest(
            "sample-0-origin.html",
            "sample-1-evil-gemini.html",
            "make-everything-ok-button",
            "#root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > a[1]");
   }

   @Test
   public void test2() throws Exception {
      doFindElementTest(
            "sample-0-origin.html",
            "sample-2-container-and-clone.html",
            "make-everything-ok-button",
            "#root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > div[0] > a[0]");
   }

   @Test
   public void test3() throws Exception {
      doFindElementTest(
            "sample-0-origin.html",
            "sample-3-the-escape.html",
            "make-everything-ok-button",
            "#root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]");
   }

   @Test
   public void test4() throws Exception {
      doFindElementTest(
            "sample-0-origin.html",
            "sample-4-the-mash.html",
            "make-everything-ok-button",
            "#root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]");
   }

   @Test
   public void test5customId() throws Exception {
      doFindElementTest(
            "sample-5-custom-id.html",
            "sample-1-evil-gemini.html",
            "custom",
            "#root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > a[1]");
   }

   @Test
   public void test6noIdFound() throws Exception {
      try {
         doFindElementTest(
               "sample-0-origin.html",
               "sample-1-evil-gemini.html",
               UUID.randomUUID().toString(),
               "dummy");
         fail();
      } catch (IllegalArgumentException e) {
         //expected
      }
   }

   public void doFindElementTest(String originalFile, String modifiedFile, String elementId, String pathExpected) throws Exception {
      //PREDICATE
      XmlParser xmlParser = XmlParser.builder()
            .sourceFile(TestUtils.findFile(originalFile).toString())
            .modifiedFile(TestUtils.findFile(modifiedFile).toString())
            .elementId(elementId)
            .build();

      //FUNCTIONALITY
      AttributeProcessor result = xmlParser.findElement();

      //TESTS
      assertNotNull(result);

      assertEquals(pathExpected, result.buildPath());
   }

}