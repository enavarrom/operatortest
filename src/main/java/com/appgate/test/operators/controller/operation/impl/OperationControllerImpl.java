package com.appgate.test.operators.controller.operation.impl;

import com.appgate.test.operators.controller.operation.OperationController;
import com.appgate.test.operators.service.operation.OperationService;
import com.appgate.test.operators.service.operation.Operator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/operation", produces = "application/hal+json")
public class OperationControllerImpl implements OperationController {

  @Autowired
  private OperationService operationService;

  @Override
  @GetMapping(value = "/session")
  public ResponseEntity<String> newSession() {
    return ResponseEntity.ok(operationService.newSession());
  }

  @Override
  @PostMapping(value = "/operand")
  public ResponseEntity<Void> addOperand(@RequestParam Double operand, @RequestParam String idSession) {
    operationService.addOperand(operand, idSession);
    return ResponseEntity.ok().build();
  }

  @Override
  @GetMapping(value = "/execute/{idSession}/{operator}")
  public ResponseEntity<Double> executeOperation(@PathVariable String operator, @PathVariable String idSession) {
    return ResponseEntity.ok(operationService.executeOperation(Operator.valueOf(operator.toUpperCase()),
        idSession
    ));
  }
}
