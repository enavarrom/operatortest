package com.appgate.test.operators.service;

import com.appgate.test.operators.service.operation.Operator;

public interface OperationService {

  String newSession();

  void addOperand(Double operand, String idSession) throws Exception;

  Double executeOperation(Operator operator, String idSession);

  void removeSession(String idSession);

}
