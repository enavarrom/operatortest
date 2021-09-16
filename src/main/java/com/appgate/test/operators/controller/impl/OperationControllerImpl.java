package com.appgate.test.operators.controller.impl;

import com.appgate.test.operators.controller.OperationController;
import com.appgate.test.operators.service.OperationService;
import com.appgate.test.operators.service.operation.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationControllerImpl implements OperationController {

  @Autowired
  private OperationService operationService;

  @Override
  public ResponseEntity<String> newSession() {
    return ResponseEntity.ok(operationService.newSession());
  }

  @Override
  public ResponseEntity<Void> addOperand(Double operand, String idSession) {
    try {
      operationService.addOperand(operand, idSession);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Double> executeOperation(String operation, String idSession) {
    return ResponseEntity.ok(operationService.executeOperation(Operator.valueOf(operation.toUpperCase()),
        idSession
        ));
  }
}
