package ku.cs.RPS.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SomeThingWrongException extends Exception {
    public SomeThingWrongException(String message) {
        super(message);
    }
}
