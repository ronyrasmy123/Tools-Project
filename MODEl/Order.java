package MODEl;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import EnumS.OrderStatus;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany
    private List<Meal> items;

    private Double total_price;

    @ManyToOne
    @JoinColumn(name="fk_runnerId", nullable=false)
    private Runner runner;

    public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus preparing) {
		this.order_status = preparing;
	}

	@ManyToOne
    @JoinColumn(name="fk_restaurantId", nullable=false)
    private Restaurant restaurant;

    private OrderStatus order_status;

    public Restaurant getRestaurant() {
        return restaurant;
    }

	
}