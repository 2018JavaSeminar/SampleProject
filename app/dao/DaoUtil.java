package dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.avaje.ebean.SqlUpdate;
import com.typesafe.config.ConfigFactory;

public class DaoUtil {

	public static String getBasePath() {
		return ConfigFactory.load().getString("fileBasePath");
	}

	public static String getSql(String targetPath) {
		Path path = Paths.get(targetPath);
		StringBuilder sql = new StringBuilder();
		try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
			stream.forEach(line -> {
				sql.append(line);
			});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return sql.toString();
	}

	public static int executeSql(SqlUpdate sql) {
		return sql.execute();
	}

	public static String getMethodName() {

		return Thread.currentThread().getStackTrace()[2].getMethodName();

	}
}
