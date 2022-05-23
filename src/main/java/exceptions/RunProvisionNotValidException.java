package exceptions;

public class RunProvisionNotValidException extends  IllegalStateException {
    public RunProvisionNotValidException(String runProvision) {

            super(String.format("Run provision %s not supported. Use either local or remote", runProvision));
        }

    }

