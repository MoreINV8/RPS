package ku.cs.RPS.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ErrorHandleController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus                             // you can specify specific status code here
    public String exception(final Exception throwable, final Model model) {
        return "error-view";                         // go to error.html
    }

    @ExceptionHandler(TemplateInputException.class)
    @ResponseStatus                             // you can specify specific status code here
    public String templateException(final TemplateInputException throwable, final Model model) {
        return "error-view";                         // go to error.html
    }
}

