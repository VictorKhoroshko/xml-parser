package com.agileengine;

/**
 * Contains methods to work with Longest Common Substring algorithm.
 * <p>
 * Created by User on 02-Dec-18.
 */
public class LongestCommonSubstringAlgorithm {

   /**
    * Calculates similarity of two strings using Longest Common Substring algorithm.
    *
    * @return similarity in range [0, 1], where 1 is absolutely similar.
    */
   public static double calculateSimilarity(String value1, String value2) {
      char[] row = value1.toCharArray();
      char[] column = value2.toCharArray();

      int rowLength = row.length;
      int columnLength = column.length;

      int matrix[][] = new int[rowLength + 1][columnLength + 1];

      //populating the matrix
      //x - row
      //y - column
      for (int x = 0; x <= rowLength; x++) {

         for (int y = 0; y <= columnLength; y++) {

            if (x == 0 || y == 0)
               matrix[x][y] = 0;

            else if (row[x - 1] == column[y - 1])
               matrix[x][y] = matrix[x - 1][y - 1] + 1;

            else
               matrix[x][y] = Integer.max(matrix[x - 1][y], matrix[x][y - 1]);
         }
      }

      return (double) matrix[rowLength][columnLength] /
            (double) Integer.max(row.length, column.length); //take the longest value
   }
}
