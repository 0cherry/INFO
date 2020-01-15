package combination_testing;

public class PizzaShop {
	int order_pizza(String dough, String topping, String order) {
		int pizza_price = 0;
		if(dough.equals("곡물"))
			pizza_price = 10000;
		if(dough.equals("나폴리"))
			pizza_price = 12000;
		if(dough.equals("씬 크러스트"))
			pizza_price = 14000;
		if(topping.equals("기본"))
			pizza_price += 3000;
		if(topping.equals("프리미엄")) {
			pizza_price += 5000;
			if(order.equals("온라인")) {
				pizza_price -= 1500;
				if(dough.equals("씬 크러스트"))
					pizza_price -= 500;
			}
		}
		
		return pizza_price;
	}
}
