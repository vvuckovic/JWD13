package servlet1.webshop;

/** Reprezentuje stavku u korpi. Stavku cine proizvod i kolicina. */
public class ShoppingCartItem {

	private Product product;
	private int count;
	private int id;

	public int getId() {
		return id;
	}


	/*public ShoppingCartItem(Product p, int count) {
		this.product = p;
		this.count = count;
		this.id = idCounter++;
	}*/
	
	public ShoppingCartItem(Product product, int count, int id) {
		super();
		this.product = product;
		this.count = count;
		this.id = id;
	}

	
	public void setProduct(Product p) {
		product = p;
	}

	public Product getProduct() {
		return product;
	}

	public void setCount(int c) {
		count = c;
	}

	public int getCount() {
		return count;
	}


	@Override
	public boolean equals(Object obj) {
		ShoppingCartItem sci = (ShoppingCartItem) obj;
		return sci.getId() == this.id;
	}


	@Override
	public String toString() {
		return "ShoppingCartItem [product=" + product + ", count=" + count
				+ ", id=" + id + "]";
	}

}
