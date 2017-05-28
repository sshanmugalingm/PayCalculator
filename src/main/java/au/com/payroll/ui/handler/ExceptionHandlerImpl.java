package au.com.payroll.ui.handler;

import org.apache.log4j.Logger;

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
public class ExceptionHandlerImpl implements ExceptionHandler {

    final static Logger logger = Logger.getLogger(ExceptionHandlerImpl.class);

    @Override
    public String translate(Exception e) {
        String message;
        if (e instanceof IllegalArgumentException) {
            message = e.getMessage();
        } else {
            logger.error("Something went wrong!", e);
            message = "Something went wrong.  Please contact Administrator";
        }
        return message;
    }

}
