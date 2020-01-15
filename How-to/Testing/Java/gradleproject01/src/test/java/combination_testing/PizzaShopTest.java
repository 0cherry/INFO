package combination_testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class PizzaShopTest {

	@DisplayName("Each Choice 방식")
	@ParameterizedTest(name="도우 {0} 토핑 {1} 주문방법 {2} 가격 {3}")
	@CsvFileSource(resources="/combination_testing/pairwise_testcase.csv")
	void should_give_correct_price_for_pizza_order(String d, String t, String o, int p) throws Exception {
		PizzaShop ps = new PizzaShop();
		int op = ps.order_pizza(d, t, o);
		assertEquals(p, op);
	}

}
