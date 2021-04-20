package com.jbpark.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.jbpark.webstore.util.ValueFormat;
import com.jbpark.webstore.validator.ProductCategory;
import com.jbpark.webstore.validator.ProductId;

/**
 * 
 * 
 * @author Jongbum Park
 *
 */
@XmlRootElement
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6108530066658600557L;

	@Pattern(regexp = "P[1-9]+", message = "{Pattern.Product.productId.validation}")
	@ProductId
	private String productId;

	@Size(min = 4, max = 50, message = "{Size.Product.name.validation}")
	private String name;

	@Min(value = 0, message = "{Min.Product.unitPrice.validation}")
	@Digits(integer = 8, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
	@NotNull(message = "{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice;
	private String unitPriceStr;

	private String description;
	private String manufacturer;

	@Pattern(regexp = "[a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+", 
			message = "{Pattern.Product.category.validation}")
	@NotNull(message = "{NotNull.Product.catogory.validation}")
	@ProductCategory
	private String category;
	
	@Digits(integer=8, fraction=0,message="{Digits.Product.unitsInStock.validation}")
	@Min(value=0, message="{Min.Product.unitsInStock.validation}")
	@NotNull(message = "{NotNull.Product.unitsInStock.validation}")
	private Long unitsInStock;
	private String unitsInStockStr;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;

	@JsonIgnore
	private MultipartFile productImage;
	@JsonIgnore
	private MultipartFile productManual;

	@XmlTransient
	public MultipartFile getProductManual() {
		return productManual;
	}

	public void setProductManual(MultipartFile productManual) {
		this.productManual = productManual;
	}

	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public String getUnitPriceStr() {
		return unitPriceStr;
	}

	public Product() {
		super();
	}

	public Product(String productId, String pname, int unitPrice) {
		this.productId = productId;
		this.name = pname;
		this.unitPrice = new BigDecimal(unitPrice);
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
		if (unitPrice == null)
			unitPrice = new BigDecimal(0);
		DecimalFormat df = new DecimalFormat("#,###");
		this.unitPriceStr = df.format(unitPrice);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getUnitsInStock() {
		return unitsInStock;
	}

	public String getUnitsInStockStr() {
		return unitsInStockStr;
	}

	public void setUnitsInStock(Long unitsInStock) {
		this.unitsInStock = unitsInStock;
		this.unitsInStockStr = ValueFormat.format(unitsInStock, ValueFormat.COMMAS);
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
}
