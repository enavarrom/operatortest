package com.appgate.test.operators.service.operation;

import java.util.List;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

/**
 * @author enavarrom
 * @since 2021/09/17
 */
@FunctionalInterface
public interface ExecuteOperation {

  /**
   * Functional Interface for apply the operation
   * @param operands
   * @return the result of operation
   */
  Double executeOperation(List<Double> operands);

  /**
   * Represent sum execute operation
   * @param operands
   * @return the result of operation
   */
  static Double executeSum(List<Double> operands) {
    if (!CollectionUtils.isEmpty(operands)) {
      return operands.stream().reduce(0D, Double::sum);
    }
    return null;
  }

  /**
   * Represent subtract execute operation
   * @param operands
   * @return the result of operation
   */
  static Double executeSubtraction(List<Double> operands) {
    Double result = null;
    if (!CollectionUtils.isEmpty(operands)) {
      Double firstElement = operands.get(0);
      result = (firstElement*2) - operands.stream().reduce(0D, Double::sum);
    }
    return result;
  }

  /**
   * Represent multiplication execute operation
   * @param operands
   * @return the result of operation
   */
  static Double executeMultiplication(List<Double> operands) {
    Double result = null;
    for (Double operand : operands) {
      if (Objects.isNull(result)) {
        result = operand;
      }
      else {
        result = result * operand;
      }
    }
    return result;
  }

  /**
   * Represent divition execute operation
   * @param operands
   * @return the result of operation
   */
  static Double executeDivition(List<Double> operands) {
    Double result = null;
    for (Double operand : operands) {
      if (Objects.isNull(result)) {
        result = operand;
      }
      else {
        result = result / operand;
      }
    }
    return result;
  }

  /**
   * Represent pow execute operation
   * @param operands
   * @return the result of operation
   */
  static Double executePow(List<Double> operands) {
    Double result = null;
    for (Double operand : operands) {
      if (Objects.isNull(result)) {
        result = operand;
      }
      else {
        result = Math.pow(result, operand);
      }
    }
    return result;
  }

}
