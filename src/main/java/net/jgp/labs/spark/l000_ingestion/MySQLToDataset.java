package net.jgp.labs.spark.l000_ingestion;

import java.util.Properties;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class MySQLToDataset {

	public static void main(String[] args) {
		MySQLToDataset app = new MySQLToDataset();
		app.start();
	}

	private void start() {
		SparkSession spark = SparkSession.builder().appName("Dataset from MySQL JDBC Connection").master("local").getOrCreate();

		java.util.Properties props = new Properties();
		props.put("user", "u");
		props.put("password", "p");
		props.put("useSSL", "false");
		props.put("serverTimezone", "EST");
		Dataset<Row> df = spark.read().jdbc("jdbc:mysql://mocka:3306/nceatery", "post", props);
		df = df.orderBy(df.col("date_last_update"));
		df.show();
	}
}
