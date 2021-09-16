package com.appgate.test.operators.service.impl;

import com.appgate.test.operators.service.OperationService;
import com.appgate.test.operators.service.operation.Operator;
import com.appgate.test.operators.service.operation.OperationSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * @author enavarro
 * @since 2021/09/15
 */
@Service
public class OperationServiceImpl implements OperationService {

  private Map<String, OperationSession> operationSessionMap;

  public OperationServiceImpl() {
    operationSessionMap = new HashMap<>();
  }

  @Override
  public String newSession() {
    OperationSession newSession = new OperationSession();
    operationSessionMap.put(newSession.getId(), newSession);
    return newSession.getId();
  }

  @Override
  public void addOperand(Double operand, String idSession) {
    validateSession(idSession);
    operationSessionMap.get(idSession)
        .addOperand(operand);
  }

  @Override
  public Double executeOperation(Operator operator, String idSession) {
    validateSession(idSession);
    return operationSessionMap.get(idSession)
          .executeOperation(operator);
  }

  @Override
  public void removeSession(String idSession) {
    validateSession(idSession);
    operationSessionMap.remove(idSession);
  }

  private void validateSession(String idSession) {
    if (!operationSessionMap.containsKey(idSession)) {
      throw new IllegalArgumentException("Not session available. Please you must to create a new session");
    }
  }

}
