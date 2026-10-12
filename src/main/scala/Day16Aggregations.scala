
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Day16Aggregations {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("Day 16 Aggregations")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

    spark.sparkContext.setLogLevel("ERROR")

    // Part 1: Employee salary statistics
    val employees = Seq(
      (1, "Ravi", "IT", 60000.0),
      (2, "Priya", "IT", 75000.0),
      (3, "Kiran", "HR", 45000.0),
      (4, "Sneha", "HR", 50000.0),
      (5, "Arjun", "Finance", 65000.0),
      (6, "Divya", "Finance", 70000.0),
      (7, "Rahul", "IT", 80000.0),
      (8, "Meena", "HR", 55000.0)
    ).toDF("id", "name", "department", "salary")

    println("\n========== EMPLOYEE DATA ==========")
    employees.show()

    // 1. Count, sum, average, minimum and maximum
    println("\n========== OVERALL SALARY STATISTICS ==========")

    employees.agg(
      count("*").as("total_employees"),
      sum("salary").as("total_salary"),
      avg("salary").as("average_salary"),
      min("salary").as("minimum_salary"),
      max("salary").as("maximum_salary")
    ).show()

    // 2. Department-wise salary statistics
    println("\n========== DEPARTMENT-WISE STATISTICS ==========")

    val departmentStats = employees
      .groupBy("department")
      .agg(
        count("*").as("employee_count"),
        sum("salary").as("total_salary"),
        round(avg("salary"), 2).as("average_salary"),
        min("salary").as("minimum_salary"),
        max("salary").as("maximum_salary")
      )
      .orderBy("department")

    departmentStats.show()

    // 3. Group by multiple columns
    val employeeGroups = Seq(
      ("IT", "Chennai", 60000.0),
      ("IT", "Hyderabad", 75000.0),
      ("IT", "Chennai", 80000.0),
      ("HR", "Hyderabad", 45000.0),
      ("HR", "Chennai", 55000.0),
      ("Finance", "Hyderabad", 70000.0)
    ).toDF("department", "city", "salary")

    println("\n========== DEPARTMENT AND CITY GROUPING ==========")

    employeeGroups
      .groupBy("department", "city")
      .agg(
        count("*").as("employee_count"),
        round(avg("salary"), 2).as("average_salary")
      )
      .orderBy("department", "city")
      .show()

    // 4. HAVING-like filtering after aggregation
    println("\n========== DEPARTMENTS WITH AVERAGE SALARY > 55000 ==========")

    departmentStats
      .filter(col("average_salary") > 55000)
      .show()

    // Part 2: Hospital department revenue metrics
    val hospital = Seq(
      (101, "Cardiology", "Consultation", 2500.0),
      (102, "Cardiology", "Treatment", 12000.0),
      (103, "Neurology", "Consultation", 3000.0),
      (104, "Neurology", "Treatment", 15000.0),
      (105, "Orthopedics", "Consultation", 2000.0),
      (106, "Orthopedics", "Surgery", 30000.0),
      (107, "Cardiology", "Surgery", 25000.0),
      (108, "Neurology", "Consultation", 3500.0),
      (109, "Orthopedics", "Treatment", 8000.0),
      (110, "Cardiology", "Consultation", 2500.0)
    ).toDF("patient_id", "department", "service", "bill_amount")

    println("\n========== HOSPITAL BILLING DATA ==========")
    hospital.show()

    // 5. Hospital department revenue metrics
    println("\n========== HOSPITAL DEPARTMENT REVENUE ==========")

    val hospitalMetrics = hospital
      .groupBy("department")
      .agg(
        count("*").as("patient_visits"),
        sum("bill_amount").as("total_revenue"),
        round(avg("bill_amount"), 2).as("average_bill"),
        min("bill_amount").as("minimum_bill"),
        max("bill_amount").as("maximum_bill")
      )
      .orderBy(desc("total_revenue"))

    hospitalMetrics.show()

    // 6. Revenue by department and service
    println("\n========== REVENUE BY DEPARTMENT AND SERVICE ==========")

    hospital
      .groupBy("department", "service")
      .agg(
        count("*").as("patient_visits"),
        sum("bill_amount").as("service_revenue")
      )
      .orderBy("department", "service")
      .show()

    // 7. HAVING-like filter: revenue greater than 25000
    println("\n========== DEPARTMENTS WITH REVENUE > 25000 ==========")

    hospitalMetrics
      .filter(col("total_revenue") > 25000)
      .show()

    spark.stop()
  }
}
