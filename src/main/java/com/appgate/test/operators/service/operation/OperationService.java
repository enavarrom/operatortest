package com.appgate.test.operators.service.operation;

public interface OperationService {

  String newSession();

  void addOperand(Double operand, String idSession);

  Double executeOperation(Operator operator, String idSession);

  void removeSession(String idSession);

}
