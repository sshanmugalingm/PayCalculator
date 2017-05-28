package au.com.payroll.service.handler

import au.com.payroll.dto.EmployeePayDetail
import au.com.payroll.dto.PaySlip
import spock.lang.Specification

/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class PayCalculationHandlerImplSpec extends Specification {

    PayCalculationHandler payCalculationHandler

    def setup() {
        payCalculationHandler = new PayCalculationHandlerImpl()
    }

    def "calculate, should create a new PaySlip object, call the Pay Period Calculator and return a pay slip object, when valid employee details and null pay slip is passed"() {
        given :
        String[] employeeParams = ['FirstName', 'LastName', '8000', '9.0', '12/2015']

        and :
        payCalculationHandler.payPeriodCalculationHandler = Mock(PayCalculationHandler) {
            1 * calculate(*_) >> { EmployeePayDetail payDetail, PaySlip paySlip ->
                return new PaySlip(firstName: payDetail.getFirstName(), lastName: payDetail.getLastName(), grossIncome: 1, netIncome: 1, incomeTax: 1, superAnnuation: 1)
            }
        }

        when :
        EmployeePayDetail payDetail = new EmployeePayDetail(employeeParams)
        PaySlip paySlip = payCalculationHandler.calculate(payDetail, null)

        then :
        paySlip
    }
}
