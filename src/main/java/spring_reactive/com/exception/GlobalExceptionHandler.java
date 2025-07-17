//package spring_reactive.com.exception;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//    @ExceptionHandler(RuntimeException.class)
//    public Mono<ResponseEntity<ErrorResponse>> handleRuntimeException(RuntimeException ex, ServerWebExchange exchange) {
//        log.error("[RuntimeException] at {} => {}", exchange.getRequest().getPath(), ex.getMessage(), ex);
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Internal Server Error",
//                ex.getMessage(),
//                exchange.getRequest().getPath().value()
//        );
//        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public Mono<ResponseEntity<ErrorResponse>> handleIllegalArgument(IllegalArgumentException ex, ServerWebExchange exchange) {
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                "Bad Request",
//                ex.getMessage(),
//                exchange.getRequest().getPath().value()
//        );
//        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
//    }
//
//    @ExceptionHandler(Exception.class)
//    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex, ServerWebExchange exchange) {
//
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.INTERNAL_SERVER_ERROR.value(),
//                "Unexpected Error",
//                ex.getMessage(),
//                exchange.getRequest().getPath().value()
//        );
//        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public Mono<ResponseEntity<ErrorResponse>> handleNotFound(ResourceNotFoundException ex, ServerWebExchange exchange) {
//        log.error("[ResourceNotFoundException] at {} => {}", exchange.getRequest().getPath(), ex.getMessage());
//        ErrorResponse error = new ErrorResponse(
//                HttpStatus.NOT_FOUND.value(),
//                "Not Found",
//                ex.getMessage(),
//                exchange.getRequest().getPath().value()
//        );
//        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
//    }
//
//}
