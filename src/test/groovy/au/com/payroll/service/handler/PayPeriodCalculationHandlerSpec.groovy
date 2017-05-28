package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class PayPeriodCalculationHandlerSpec extends Specification {

    PayCalculationHandler payPeriodCalculationHandler

    def setup() {
        payPeriodCalculationHandler = new PayPeriodCalculationHandler()
    }

    def "calculate, should calculate the pay period and call Gross Income Calculator and return a valid Pay Slip, when valid Employee Details are passed"() {
        given :
        given :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']

        and :
        payPeriodCalculationHandler.grossIncomeCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return paySlip
            }
        }

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = payPeriodCalculationHandler.calculate(payDetail, new PaySlip())

        then :
        paySlip
        paySlip.payPeriod == '01 December 2015 - 31 December 2015'
    }

}
