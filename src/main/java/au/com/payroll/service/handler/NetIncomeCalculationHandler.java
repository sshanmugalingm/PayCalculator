package au.com.payroll.service.handler;

import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public class NetIncomeCalculationHandler implements PayCalculationHandler {

    @Autowired
    private PayCalculationHandler superCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        long monthlyNetIncome = paySlip.getGrossIncome() - paySlip.getIncomeTax();
        paySlip.setNetIncome(monthlyNetIncome);
        return superCalculationHandler.calculate(payDetail, paySlip);
    }
}
