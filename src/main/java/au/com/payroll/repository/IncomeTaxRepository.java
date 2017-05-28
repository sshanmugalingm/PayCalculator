package au.com.payroll.repository;

import au.com.payroll.domain.IncomeTaxChart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by senthurshanmugalingm on 27/05/2017.
 */
public interface IncomeTaxRepository extends CrudRepository<IncomeTaxChart, Long> {

    @Query("select c from IncomeTaxChart c where c.incomeStart <= :income and c.incomeEnd >= :income")
    IncomeTaxChart findByTaxBracket(@Param("income") Long income);

}
