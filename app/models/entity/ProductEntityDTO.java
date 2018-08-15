package models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import models.BaseEntity;

public class ProductEntityDTO extends BaseEntity {

	@JsonProperty("prod_no")
	/** 商品番号 */
	public String prodNo;

	@JsonProperty("prod_name")
	/** 商品名 */
	public String prodName;

	@JsonProperty("price")
	/** 価格 */
	public Long price;

	@JsonProperty("caption")
	/** 説明文 */
	public String caption;

	/**
	 * 商品番号を取得します。
	 *
	 * @return prodNo 商品番号
	 */
	public String getProdNo() {
		return prodNo;
	}

	/**
	 * 商品番号を設定します。
	 *
	 * @param prodNo 商品番号
	 */
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

}
