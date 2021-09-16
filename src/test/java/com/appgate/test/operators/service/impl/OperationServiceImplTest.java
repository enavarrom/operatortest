package com.appgate.test.operators.service.impl;

import com.appgate.test.operators.service.OperationService;
import com.appgate.test.operators.utilities.Operation;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OperationServiceImplTest {

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
    String[] operations = {
        "sum",
        "substraction",
        "multiplication",
        "divition",
        "potentiation"
    };

    Arrays.stream(operations)
        .forEach(operation -> Assertions.assertNotNull(operationService
            .executeOperation(Operation.valueOf(operation), idSession)));

  }
}