package exceptions;

public class BroswerInfoNotValidException extends  IllegalStateException {
    public BroswerInfoNotValidException(String browser) {

            super(String.format("Browser %s not supported. Use either chrome,firefox or safari", browser));
        }

    }

