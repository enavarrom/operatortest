package com.appgate.test.operators.controller.operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author enavarro
 * @since 2021/09/15
 */
public interface OperationController {

  /**
   * Method to create a new session to perform the operation
   * @return A ResponseEntity<String> value with a new id session object
   */
  ResponseEntity<String> newSession();

  /**
   * This method permit add a new operand value for to do the operation
   * @param operand number as Double type
   * @param idSession String with the session id
   * @return ResponseEntity<Void> Empty response
   */
  ResponseEntity<Void> addOperand(@RequestParam Double operand, @RequestParam String idSession);

  /**
   * Method to perform the operation
   * @param operator A String value with the operation to perform. (Values: sum, substraction,
   *                  multiplication, divition, potentiation).
   * @param idSession String with the session id
   * @return A ResponseEntity<Double> with the result of the operation performed.
   */
  ResponseEntity<Double> executeOperation(@PathVariable String operator, @PathVariable String idSession);

}
