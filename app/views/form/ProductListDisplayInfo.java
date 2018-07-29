package views.form;

import java.util.Date;

import models.entity.ProductEntity;
import play.data.format.Formats;

public class ProductListDisplayInfo {

	public ProductListDisplayInfo() {
	}

	String prodNo;

	String prodName;

	Long price;

	String caption;

	@Formats.DateTime(pattern = "yyyy/MM/dd HH:mm:SS")
	Date createdAt;

	@Formats.DateTime(pattern = "yyyy/MM/dd HH:mm:SS")
	Date updatedAt;

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ProductListDisplayInfo(ProductEntity product) {
		this.prodNo = product.getProdNo();
		this.prodName = product.getProdName();
		this.price = product.getPrice();
		this.caption = product.getCaption();
		this.createdAt = product.getCreatedAt();
		this.updatedAt = product.getUpdatedAt();
	}
}
