package com.appgate.test.operators.service.operation.impl;

import com.appgate.test.operators.service.operation.OperationService;
import com.appgate.test.operators.service.operation.OperationSession;
import com.appgate.test.operators.service.operation.Operator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * @author enavarro
 * @since 2021/09/15
 */
@Service
public class OperationServiceImpl implements OperationService {

  private Map<String, OperationSession> operationSessionMap;

  public OperationServiceImpl() {
    operationSessionMap = new ConcurrentHashMap<>();
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
      throw new IllegalArgumentException(String.format("Not exist session with Id: %s", idSession));
    }
  }

}
