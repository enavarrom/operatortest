package com.appgate.test.operators.service.operation;

import static com.appgate.test.operators.service.operation.Operator.DIVIDE;
import static com.appgate.test.operators.service.operation.Operator.MULTIPLY;
import static com.appgate.test.operators.service.operation.Operator.POWER;
import static com.appgate.test.operators.service.operation.Operator.SUBTRACT;
import static com.appgate.test.operators.service.operation.Operator.SUM;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;

public class OperationSession {

  @Getter
  private String id;

  private List<Double> operands = new ArrayList<>();

  private static final Map<Operator, ExecuteOperation> OPERATION_FUNCTION_MAP
      = new EnumMap<>(Operator.class);

  static {
    OPERATION_FUNCTION_MAP.put(SUM, ExecuteOperation::executeSum);
    OPERATION_FUNCTION_MAP.put(SUBTRACT, ExecuteOperation::executeSubstraction);
    OPERATION_FUNCTION_MAP.put(MULTIPLY, ExecuteOperation::executeMultiplication);
    OPERATION_FUNCTION_MAP.put(DIVIDE, ExecuteOperation::executeDivition);
    OPERATION_FUNCTION_MAP.put(POWER, ExecuteOperation::executePotentiation);
  }

  public OperationSession() {
    this.id = UUID.randomUUID().toString();
  }

  public void addOperand(Double operand) {
    operands.add(operand);
  }

  public Double executeOperation(Operator operator) {
    return OPERATION_FUNCTION_MAP.get(operator)
        .executeOperation(operands);
  }

}
