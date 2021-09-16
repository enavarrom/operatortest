package com.appgate.test.operators.service.impl;

import com.appgate.test.operators.service.OperationService;
import com.appgate.test.operators.utilities.Operation;
import org.springframework.stereotype.Service;

/**
 * @author enavarro
 * @since 2021/09/15
 */
@Service
public class OperationServiceImpl implements OperationService {

  @Override
  public String newSession() {
    return null;
  }

  @Override
  public void addOperand(Double operand, String idSession) throws Exception {
    throw new NoSuchMethodException("Not Implemented");
  }

  @Override
  public Double executeOperation(Operation operation, String idSession) {
    return null;
  }
}
