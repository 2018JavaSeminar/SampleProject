package models.util.find;

import java.util.List;

import com.avaje.ebean.ExpressionList;

public interface Unique<TABLE> extends Where<TABLE> {

	/**
	 * あいまい検索処理を行う。
	 *
	 * @param value 検索ワード
	 * @param columns 検索カラム(複数可)
	 * @return 検索結果
	 */
	default List<TABLE> unique(String value, String... columns) {
		ExpressionList<TABLE> ex = where().disjunction();
		for (String column : columns) {
			ex = ex.like(column, "%" + value + "%");
		}
		return ex.findList();
	}

}
