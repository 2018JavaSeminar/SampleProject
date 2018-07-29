package logic;

import org.apache.commons.lang3.StringUtils;

import models.entity.ProductEntity;
import views.form.ProductInfoManageForm;

public class ProductInfoManageLogic {

	public static final int PROC_DIV_INSERT = 0;
	public static final int PROC_DIV_UPDATE = 1;

	public void insertOrUpdate(ProductInfoManageForm form, int procDiv) {

		ProductEntity product = convertForm2Entity(form);
		if (procDiv == PROC_DIV_INSERT) {
			product.save();
		} else {
			product.update();
		}
	}

	public ProductInfoManageForm searchByKey(String prodNo) {
		return convertEntity2Form(ProductEntity.find.equal(prodNo, "prodNo"));
	}

	public String delete(String prodNo) {

		if (StringUtils.isBlank(prodNo)) {
			System.out.println("not key delete error:" + prodNo);
			return "1";
		}

		ProductEntity product = ProductEntity.find.equal(prodNo, "prodNo");
		if (StringUtils.isBlank(product.getProdNo())) {
			System.out.println("no data:" + prodNo);
			return "1";
		}

		product.delete();

		return "0";

	}

	/**
	 * Formの値をEntityに詰め替えます
	 *
	 * @param form
	 * @return
	 */
	private ProductEntity convertForm2Entity(ProductInfoManageForm form) {
		ProductEntity entity = new ProductEntity();
		entity.setProdNo(form.getProdNo());
		entity.setProdName(form.getProdName());
		entity.setPrice(form.getPrice());
		entity.setCaption(form.getCaption());
		return entity;
	}

	/**
	 * Entityの値をFormに詰め替えます
	 *
	 * @param entity
	 * @return
	 */
	private ProductInfoManageForm convertEntity2Form(ProductEntity entity) {
		ProductInfoManageForm form = new ProductInfoManageForm();
		form.setProdNo(entity.getProdNo());
		form.setProdName(entity.getProdName());
		form.setPrice(entity.getPrice());
		form.setCaption(entity.getCaption());
		return form;
	}

}
