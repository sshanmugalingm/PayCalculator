package au.com.payroll.service.handler

import spock.lang.Specification


/**
 * Created by senthurshanmugalingm on 28/05/2017.
 */
class NetIncomeCalculationHandlerSpec extends Specification {

    PayCalculationHandler netIncomeCalculationHandler

    def setup() {
        netIncomeCalculationHandler = new NetIncomeCalculationHandler()
    }

    def "calculate, should calculate Net Income using Gross Income and Income Tax, when a valid Employee Pay Details are provided"() {

    }

}