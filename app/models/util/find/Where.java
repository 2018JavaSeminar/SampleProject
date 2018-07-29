package models.util.find;

import com.avaje.ebean.ExpressionList;

public interface Where<TABLE> {

	ExpressionList<TABLE> where();

}
