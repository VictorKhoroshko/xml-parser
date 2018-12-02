package com.agileengine;

import lombok.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;

import java.util.*;
import java.util.stream.*;

/**
 * Deals with comparison of two {@link Element}s using their attributes.
 * <p>
 * Uses Longest Common Substring algorithm to calculate similarity. Similarity value is in range [0, 1],
 * where 1 is absolute match.
 * <p>
 * Created by User on 02-Dec-18.
 */
@Getter
@ToString
public class AttributeProcessor {

   /**
    * Source element
    */
   private final Element sourceElement;

   /**
    * Target element
    */
   private final Element targetElement;

   /**
    * Keeps result for a particular attributes that match
    */
   private final Map<String, Double> attributeComparisonResult = new HashMap<>();

   /**
    * Overall similarity
    */
   private Double similarityValue;


   /**
    * Constructor
    *
    * @param sourceElement source file element
    * @param targetElement target file element
    */
   public AttributeProcessor(Element sourceElement, Element targetElement) {
      this.sourceElement = sourceElement;
      this.targetElement = targetElement;
   }

   /**
    * Compares element attributes using the similarity-based
    * approach by the following steps:
    * <p>
    * Attributes are compared:
    * <p>
    * 1. Extract the complete set of attributes names used in both elements under
    * comparison;
    * <p>
    * 2. For each identified attribute, compare its value in the original element with
    * its value in the target element using the LCS algorithm, and keep the resulting similarity;
    * if the attribute is not present in one of the elements, this similarity is set to zero.
    * <p>
    * 3. Sum all the similarities calculated in the previous step and divide by the
    * number of attributes in the complete set;
    * <p>
    * 4. The resulting value is the attributes similarity component.
    */
   public void calculateSimilarity() {
      Map<String, String> originalAttrs = mapAttributes(sourceElement);
      Map<String, String> targetAttrs = mapAttributes(targetElement);

      calculateImpl(originalAttrs, targetAttrs);
   }

   void calculateImpl(Map<String, String> originalAttrs, Map<String, String> targetAttrs) {
      int attributeNumber = getAttributeNumber(originalAttrs, targetAttrs);

      Double accumulatedResult = 0d;

      for (Map.Entry<String, String> entry : originalAttrs.entrySet()) {
         String key = entry.getKey();

         String valueTarget = targetAttrs.get(key);

         Double attrResult;
         if (valueTarget == null) {
            attrResult = 0d;
         } else {
            attrResult = LongestCommonSubstringAlgorithm.calculateSimilarity(entry.getValue(), valueTarget);
         }

         accumulatedResult = accumulatedResult + attrResult;

         attributeComparisonResult.put(key, attrResult);
      }

      similarityValue = accumulatedResult / attributeNumber;
   }

   /**
    * @return number of attributes in both sets
    */
   private int getAttributeNumber(Map<String, String> originalAttrs, Map<String, String> targetAttrs) {
      Set<String> result = new HashSet<>(originalAttrs.keySet());
      result.addAll(targetAttrs.keySet());
      return result.size();
   }

   /**
    * @return map with element's attributes
    */
   private Map<String, String> mapAttributes(Element el) {
      return el.attributes().asList().stream()
            .collect(Collectors.toMap(
                  Attribute::getKey,
                  Attribute::getValue,
                  (o, o2) -> o
            ));
   }

   /**
    * @return Path of the target element. Value in brackets is element sibling index
    */
   public String buildPath() {
      List<String> path = new ArrayList<>();

      Element el = targetElement;

      while (el != null) {
         path.add(el.tag().toString() + "[" + el.elementSiblingIndex() + "]");

         el = el.parent();
      }

      Collections.reverse(path);
      return StringUtil.join(path, " > ");
   }
}
