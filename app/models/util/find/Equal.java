package models.util.find;

import com.avaje.ebean.ExpressionList;

public interface Equal<TABLE> extends Where<TABLE> {

	/**
	 * あいまい検索処理を行う。
	 *
	 * @param value 検索ワード
	 * @param columns 検索カラム(複数可)
	 * @return 検索結果
	 */
	default TABLE equal(String value, String column) {
		ExpressionList<TABLE> ex = where();
		return ex.eq(column, value).findList().get(0);
	}

}
