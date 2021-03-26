package one.digitalinnovation.beerstock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.enums.BeerType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    private String brand;

    @NotNull
    @Max(500)
    private Integer max;

    @NotNull
    @Max(100)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private BeerType type;

	public String getName() {return name;}

	public BeerDTO(Long id, @NotNull @Size(min = 1, max = 200) String name,
			@NotNull @Size(min = 1, max = 200) String brand, @NotNull @Max(500) Integer max,
			@NotNull @Max(100) Integer quantity, @NotNull BeerType type) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.max = max;
		this.quantity = quantity;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BeerType getType() {
		return type;
	}

	public void setType(BeerType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}
}