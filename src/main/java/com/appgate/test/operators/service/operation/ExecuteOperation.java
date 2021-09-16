package com.appgate.test.operators.service.operation;

import java.util.List;
import java.util.Objects;
import org.springframework.util.CollectionUtils;

@FunctionalInterface
public interface ExecuteOperation {

  Double executeOperation(List<Double> operands);

  static Double executeSum(List<Double> operands) {
    if (!CollectionUtils.isEmpty(operands)) {
      return operands.stream().reduce(0D, Double::sum);
    }
    return null;
  }

  static Double executeSubstraction(List<Double> operands) {
    Double result = null;
    if (!CollectionUtils.isEmpty(operands)) {
      Double firstElement = operands.get(0);
      result = (firstElement*2) - operands.stream().reduce(0D, Double::sum);
    }
    return result;
  }

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

  static Double executePotentiation(List<Double> operands) {
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
