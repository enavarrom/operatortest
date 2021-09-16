package com.appgate.test.operators.controller;

import org.springframework.http.ResponseEntity;

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
  ResponseEntity<Void> addOperand(Double operand, String idSession);

  /**
   * Method to perform the operation
   * @param operation A String value with the operation to perform. (Values: sum, substraction,
   *                  multiplication, divition, potentiation).
   * @param idSession String with the session id
   * @return A ResponseEntity<Double> with the result of the operation performed.
   */
  ResponseEntity<Double> executeOperation(String operation, String idSession);

}
