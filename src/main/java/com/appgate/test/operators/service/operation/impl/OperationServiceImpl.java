package com.appgate.test.operators.service.operation.impl;

import com.appgate.test.operators.service.operation.OperationService;
import com.appgate.test.operators.service.operation.OperationSession;
import com.appgate.test.operators.service.operation.Operator;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author enavarro
 * @since 2021/09/15
 */
@Service
@Slf4j
public class OperationServiceImpl implements OperationService {

  private Map<String, OperationSession> operationSessionMap;

  @Autowired
  private RateLimiterRegistry registry;

  public OperationServiceImpl() {
    operationSessionMap = new ConcurrentHashMap<>();
  }

  @Override
  @RateLimiter(name = "default")
  public String newSession() {
    OperationSession newSession = new OperationSession();
    operationSessionMap.put(newSession.getId(), newSession);
    log.info(String.format("The session [%s] has been created correctly.", newSession.getId()));
    return newSession.getId();
  }

  @Override
  @RateLimiter(name = "default")
  public void addOperand(Double operand, String idSession) {
    try {
      validateSession(idSession);
      operationSessionMap.get(idSession).addOperand(operand);
      log.info(String.format("The operand [%s] has been added correctly to the session [%s].", operand, idSession));
    }
    catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @RateLimiter(name = "default")
  public Double executeOperation(Operator operator, String idSession) {
    try {
      validateSession(idSession);
      return operationSessionMap.get(idSession)
          .executeOperation(operator);
    }
    catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  @Override
  @RateLimiter(name = "default")
  public void removeSession(String idSession) {
    try {
      validateSession(idSession);
      operationSessionMap.remove(idSession);
      log.info(String.format("The session [%s] has been removed correctly.", idSession));
    }
    catch (Exception e) {
      log.error(e.getMessage(), e);
      throw e;
    }
  }

  private void validateSession(String idSession) {
    if (!operationSessionMap.containsKey(idSession)) {
      throw new IllegalArgumentException(String.format("Not exist session with Id: %s", idSession));
    }
  }

}
