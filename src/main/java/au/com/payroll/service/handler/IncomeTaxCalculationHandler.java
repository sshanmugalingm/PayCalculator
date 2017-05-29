package au.com.payroll.service.handler;

import au.com.payroll.commons.PayrollConstants;
import au.com.payroll.domain.IncomeTaxChart;
import au.com.payroll.dto.EmployeePayDetail;
import au.com.payroll.dto.PaySlip;
import au.com.payroll.repository.IncomeTaxRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A Class that is responsible for Calculating the Income Tax
 *
 * @author Senthur Shanmugalingm
 */
public class IncomeTaxCalculationHandler implements PayCalculationHandler {

    @Autowired
    private IncomeTaxRepository incomeTaxRepository;

    @Autowired
    private PayCalculationHandler netIncomeCalculationHandler;

    @Override
    public PaySlip calculate(EmployeePayDetail payDetail, PaySlip paySlip) {

        IncomeTaxChart incomeTaxChart = incomeTaxRepository.findByTaxBracket(payDetail.getAnnualSalary());

        long monthlyIncomeTax = isTaxable(incomeTaxChart.getTaxableAmount(), incomeTaxChart.getTaxUnitRate()) ?
                calculateTax(incomeTaxChart.getTaxableAmount(), payDetail.getAnnualSalary(), incomeTaxChart.getTaxableThreshold(), incomeTaxChart.getTaxUnitRate()) : 0;

        paySlip.setIncomeTax(monthlyIncomeTax);
        return netIncomeCalculationHandler.calculate(payDetail, paySlip);
    }

    private boolean isTaxable(long taxableAmount, double taxUnitRate) {
       return (taxableAmount > 0 || taxUnitRate > 0);
    }

    private long calculateTax(long taxableAmount, long annualSalary, double taxThreshold, double taxUnitRate) {
        return Math.round((taxableAmount + (annualSalary - taxThreshold) * taxUnitRate) / PayrollConstants.MONTHS_OF_YEAR);
    }
}
