package MODEl;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import EnumS.Status;

@Entity
public class Runner {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Status status;

    public Status getStatus() {
		return status;
	}

	public void setStatus(Status busy) {
		this.status = busy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDelivery_fees() {
		return delivery_fees;
	}

	public void setDelivery_fees(Double delivery_fees) {
		this.delivery_fees = delivery_fees;
	}

	private Double delivery_fees;

	
}