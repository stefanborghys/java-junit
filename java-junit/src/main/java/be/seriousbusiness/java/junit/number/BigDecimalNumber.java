package be.seriousbusiness.java.junit.number;

import java.math.BigDecimal;

/**
 * Number implementation for BigDecimal.
 * @author seriousbusiness
 *
 */
public class BigDecimalNumber implements Number<BigDecimal> {

	public boolean isBetweenOrEqual(final BigDecimal number,
			final BigDecimal minimum,
			final BigDecimal maximum) {
		if(number==null){
			return false;
		}else if(minimum==null){
			return number.compareTo(maximum)<=0;
		}else if(maximum==null){
			return number.compareTo(minimum)>=0;
		}else{
			return number.compareTo(maximum)<=0 && number.compareTo(minimum)>=0;
		}
	}

}
