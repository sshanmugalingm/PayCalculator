package au.com.payroll.service.handler;

import au.com.payroll.commons.PayrollConstants;
import au.com.payroll.domain.IncomeTaxChart;
import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import au.com.payroll.repository.IncomeTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public class IncomeTaxCalculationHandler implements PayCalculationHandler {

    @Autowired
    private IncomeTaxRepository incomeTaxRepository;

    @Autowired
    private PayCalculationHandler netIncomeCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        IncomeTaxChart incomeTaxChart = incomeTaxRepository.findByTaxBracket(payDetail.getAnnualSalary());

        long monthlyIncomeTax = Math.round((incomeTaxChart.getTaxableAmount() + (payDetail.getAnnualSalary() - incomeTaxChart.getTaxableThreshold()) * incomeTaxChart.getTaxUnitRate()) / PayrollConstants.MONTHS_OF_YEAR);
        paySlip.setIncomeTax(monthlyIncomeTax);
        return netIncomeCalculationHandler.calculate(payDetail, paySlip);
    }
}
