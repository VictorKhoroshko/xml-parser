package com.agileengine;

import org.junit.*;

import static com.agileengine.LongestCommonSubstringAlgorithm.calculateSimilarity;
import static org.junit.Assert.*;

/**
 * Created by User on 02-Dec-18.
 */
public class LongestCommonSubstringAlgorithmTest {
   
   @Test
   public void testLcs() throws Exception {
      double delta = 0.01d;

      assertEquals(1, calculateSimilarity("4 Briars Lane", "4 Briars Lane"), delta);
      assertEquals(0.77, calculateSimilarity("10 Beckenham Drive", "10 Beckenham Lane"), delta);
      assertEquals(0.68, calculateSimilarity("Church Farm Cottage", "Church Farm Flat 2"), delta);
      assertEquals(0.54, calculateSimilarity("Broomfield House", "Broomfield Court Flat 14"), delta);
      assertEquals(0, calculateSimilarity("abc", "efg"), delta);

   }

}