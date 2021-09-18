package com.appgate.test.operators.controller.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

  @ExceptionHandler({IllegalArgumentException.class, TypeMismatchException.class})
  public void handleBadRequests(HttpServletResponse response, Exception ex) throws IOException {
    log.error(ex.getMessage(), ex);
    response.addHeader("errMsg", ex.getMessage());
    response.sendError(PRECONDITION_FAILED.value(), ex.getMessage());
  }

  @ExceptionHandler({RuntimeException.class, NullPointerException.class})
  @ResponseBody
  public ResponseEntity<Void> internalServerError(Exception ex) {
    log.error(ex.getMessage(), ex);
    String exceptionName = ex.getClass().getCanonicalName();
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .header("error", "The: " + exceptionName + ", was thrown by the server when processing the request").header("requestUri", "GENEX").build();
  }

}
