package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public class SuperCalculationHandler implements PayCalculationHandler {

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {
        paySlip.setSuperAnnuation(Math.round(new Double((paySlip.getGrossIncome() * payDetail.getSuperRate()) / 100)));
        return paySlip;
    }
}
