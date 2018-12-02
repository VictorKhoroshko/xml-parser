package com.agileengine;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by User on 02-Dec-18.
 */
public class AttributeProcessorTest {

   @Test
   public void testPercent() throws Exception {
      //PREDICATE
      Map<String, String> map1 = new HashMap<>();
      map1.put("a", "a");//similar
      map1.put("b", "b");//dif

      Map<String, String> map2 = new HashMap<>();
      map2.put("a", "a");//similar
      map2.put("c", "c");//dif

      AttributeProcessor attributeProcessor = new AttributeProcessor(null, null);

      //FUNCTIONALITY
      attributeProcessor.calculateImpl(map1, map2);

      //TESTS
      double delta = 0.0001d;

      assertEquals(0.3333d, attributeProcessor.getSimilarityValue(), delta);
      Map<String, Double> result = attributeProcessor.getAttributeComparisonResult();
      assertEquals(1d, result.get("a"), delta);
      assertEquals(0d, result.get("b"), delta);

   }
}