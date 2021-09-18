package com.appgate.test.operators.service.operation;

import static com.appgate.test.operators.service.operation.Operator.DIVIDE;
import static com.appgate.test.operators.service.operation.Operator.MULTIPLY;
import static com.appgate.test.operators.service.operation.Operator.POW;
import static com.appgate.test.operators.service.operation.Operator.SUBTRACT;
import static com.appgate.test.operators.service.operation.Operator.SUM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;

/**
 * @author enavarrom
 * @since 2021/09/17
 */
public class OperationSession {

  @Getter
  private String id;

  private List<Double> operands = Collections.synchronizedList(new ArrayList<>());

  private static final Map<Operator, ExecuteOperation> OPERATION_FUNCTION_MAP
      = new EnumMap<>(Operator.class);

  static {
    OPERATION_FUNCTION_MAP.put(SUM, ExecuteOperation::executeSum);
    OPERATION_FUNCTION_MAP.put(SUBTRACT, ExecuteOperation::executeSubtraction);
    OPERATION_FUNCTION_MAP.put(MULTIPLY, ExecuteOperation::executeMultiplication);
    OPERATION_FUNCTION_MAP.put(DIVIDE, ExecuteOperation::executeDivition);
    OPERATION_FUNCTION_MAP.put(POW, ExecuteOperation::executePow);
  }

  public OperationSession() {
    this.id = UUID.randomUUID().toString();
  }

  /**
   * Add a operand to operation session
   * @param operand
   */
  public void addOperand(Double operand) {
    operands.add(operand);
  }

  /**
   * Apply the operation indicate in the param operator
   * @param operator
   * @return result of operation
   */
  public synchronized Double executeOperation(Operator operator) {
    return OPERATION_FUNCTION_MAP.get(operator)
        .executeOperation(operands);
  }

}
