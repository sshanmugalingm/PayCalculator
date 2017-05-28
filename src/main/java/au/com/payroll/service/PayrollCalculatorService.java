package au.com.payroll.service;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public interface PayrollCalculatorService {

    PaySlip processPay(EmployeePayDetail payDetail);

}
