package com.appgate.test.operators.service;

import com.appgate.test.operators.utilities.Operation;

public interface OperationService {

  String newSession();

  void addOperand(Double operand, String idSession) throws Exception;

  Double executeOperation(Operation operation, String idSession);

}
