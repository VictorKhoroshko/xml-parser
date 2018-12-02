package com.agileengine;

import org.junit.*;

import static com.agileengine.TestUtils.*;

/**
 * Created by User on 02-Dec-18.
 */
public class XmlParserApplicationTest {

   @Test
   public void testMain() throws Exception {
      //PREDICATE

      //FUNCTIONALITY
      XmlParserApplication.main(new String[]{
            findFile("sample-0-origin.html").toString(),
            findFile("sample-1-evil-gemini.html").toString()}
      );

      //TESTS
   }

}