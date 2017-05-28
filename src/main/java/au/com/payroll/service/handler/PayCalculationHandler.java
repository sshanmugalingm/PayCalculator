package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public interface PayCalculationHandler {

    PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip);

}
