package tiger.sparksql.basic

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 *
 * @Author Zenghu
 * @Date 2021/5/5 16:34
 * @Description
 * @Version: 1.0
 *
 * */
object SparkSQL01 {

  def main(args: Array[String]): Unit = {


    val config = new SparkConf()
    config.setMaster("local[*]")
    config.setAppName("SparkSQL")
    // TODO 创建SparkSession
    val spark = SparkSession.builder().config(config).getOrCreate()

    // TODO 读取json格式的数据
    val df: DataFrame = spark.read.json("data/user.json")
    //df.show()

    // TODO 创建视图
    df.createOrReplaceTempView("user")

    // SQL
    val result: DataFrame = spark.sql("select avg(age) as avg_age from user")
    result.show()

    // DSL
    val res2 = df.agg("age" -> "max", "age" -> "avg")
    res2.show()








    // TODO 关闭SparkSession
    spark.close()





  }


}
