package logic.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import models.entity.ProductEntity;
import views.form.ProductListDisplayInfo;

public class ProductListLogic {

	/**
	 * 検索処理を行うためのロジッククラス
	 *
	 * @param word
	 * @return
	 */
	public List<ProductListDisplayInfo> search(String word) {

		List<ProductEntity> list = new ArrayList<ProductEntity>();

		if (StringUtils.isBlank(word)) {
			list = ProductEntity.find.all();
		} else {
			list = ProductEntity.find.unique(word, "prodName", "caption");
		}

		return list.stream().map(ProductListDisplayInfo::new).collect(Collectors.toList());

	}

}
