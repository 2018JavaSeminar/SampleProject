package models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;

import models.BaseTable;
import models.util.find.All;
import models.util.find.Equal;
import models.util.find.Unique;
import play.data.validation.Constraints.Required;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseTable {

	public static class Find implements All<ProductEntity>, Unique<ProductEntity>, Equal<ProductEntity> {

		private Model.Finder<String, ProductEntity> finder = new Model.Finder<>(ProductEntity.class);

		@Override
		public ExpressionList<ProductEntity> where() {
			return finder.where();
		}
	}

	public static Find find = new Find();

	@Required
	@Id
	/** 商品番号 */
	public String prodNo;

	/** 商品名 */
	public String prodName;

	/** 価格 */
	public Long price;

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
