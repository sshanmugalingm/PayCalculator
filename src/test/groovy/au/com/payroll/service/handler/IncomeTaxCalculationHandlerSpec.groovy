package au.com.payroll.service.handler

import au.com.payroll.domain.IncomeTaxChart
import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import au.com.payroll.repository.IncomeTaxRepository
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class IncomeTaxCalculationHandlerSpec extends Specification {

    PayCalculationHandler incomeTaxCalculationHandler

    def setup() {
        incomeTaxCalculationHandler = new IncomeTaxCalculationHandler()
    }

    def "calculate, should calculate the Income Tax and call Net Income Calculator and return a valid pay slip object, when valid Employee Annual Salary provided"() {
        given :
        String annualSalary = '83000'
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']

        and :
        incomeTaxCalculationHandler.netIncomeCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }

        and :
        incomeTaxCalculationHandler.incomeTaxRepository = Mock(IncomeTaxRepository) {
            1 * findByTaxBracket(*_) >> {Long income ->
                return new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)

            }
        }

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = incomeTaxCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.incomeTax == 1555
    }

    @Unroll
    def "calculate, should calculate the Income Tax and #testFor to whole number(#incomeTax), when Annual Salary is #annualSalary"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', annualSalary, '9.0', '12/2015']

        and :
        incomeTaxCalculationHandler.netIncomeCalculationHandler = Stub(PayCalculationHandler) {
            calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }

        and :
        incomeTaxCalculationHandler.incomeTaxRepository = Stub(IncomeTaxRepository) {
            findByTaxBracket(*_) >> {Long income ->
                return new IncomeTaxChart(incomeStart: 80001, incomeEnd: 180000, taxableAmount: 17547, taxUnitRate: 0.37D, taxableThreshold: 80000)

            }
        }

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = incomeTaxCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.incomeTax == incomeTax

        where :
        annualSalary | incomeTax | testFor
        '100000'     | 2079      | 'Round Up'
        '88630'      | 1728      | 'Round Down'
    }
}
