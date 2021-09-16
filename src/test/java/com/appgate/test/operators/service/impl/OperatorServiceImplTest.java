package com.appgate.test.operators.service.impl;

import com.appgate.test.operators.service.OperationService;
import com.appgate.test.operators.service.operation.Operator;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperatorServiceImplTest {

  private OperationService operationService;

  @BeforeEach
  void setUp() {
    operationService = new OperationServiceImpl();
  }

  @Test
  public void whenNewSessionThenReturnNewIdTest() {
    Assertions.assertNotNull(operationService.newSession());
  }

  @Test
  public void whenAddOperandThenNotThrowExceptionTest() {
    Double operand = 4D;
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(operand, idSession));
  }

  @Test
  public void whenExecuteOperationThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(4D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));

    String[] operations = Arrays.stream(Operator.values())
        .map(operator -> operator.name().toLowerCase())
        .toArray(String[]::new);

    Arrays.stream(operations)
        .forEach(operation -> Assertions.assertNotNull(operationService
            .executeOperation(Operator.valueOf(operation.toUpperCase()), idSession)));
  }

  @Test
  public void whenExecuteOperationSumThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(4D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));
    Assertions.assertEquals(9D, operationService
        .executeOperation(Operator.SUM, idSession));
  }

  @Test
  public void whenExecuteOperationSubtractThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(20D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(20D, idSession));
    Assertions.assertEquals(-5D, operationService
        .executeOperation(Operator.SUBTRACT, idSession));
  }

  @Test
  public void whenExecuteOperationMultiplyThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(4D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));
    Assertions.assertEquals(20D, operationService
        .executeOperation(Operator.MULTIPLY, idSession));
  }

  @Test
  public void whenExecuteOperationDivitionThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(20D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));
    Assertions.assertEquals(4D, operationService
        .executeOperation(Operator.DIVIDE, idSession));
  }

  @Test
  public void whenExecuteOperationPotentiationThenReturnResultTest() {
    String idSession = operationService.newSession();
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(2D, idSession));
    Assertions.assertDoesNotThrow(() -> operationService.addOperand(5D, idSession));
    Assertions.assertEquals(32D, operationService
        .executeOperation(Operator.POWER, idSession));
  }
}