package spring_reactive.com.exception;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }


}
