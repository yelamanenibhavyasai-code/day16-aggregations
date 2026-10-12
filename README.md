# Day 16 — Spark SQL Aggregations

## 📌 Project Overview

This project demonstrates aggregation operations using **Apache Spark SQL with Scala**. It covers employee salary analysis, grouping by multiple columns, HAVING-like filtering, and hospital department revenue analysis.

## 🎯 Objectives

- Practice `count()`, `sum()`, `avg()`, `min()`, and `max()`.
- Calculate department-wise salary statistics.
- Group data using multiple columns.
- Apply filtering after aggregation using `filter()`.
- Generate hospital department revenue metrics.

## 🛠️ Technologies Used

- Scala 2.12.18
- Apache Spark 3.5.3
- Spark SQL
- SBT 1.12.15
- Java 17
- Ubuntu on WSL2

## 📂 Project Structure

```text
day16-aggregations/
├── build.sbt
├── project/
│   └── build.properties
└── src/
    └── main/
        └── scala/
            └── Day16Aggregations.scala
```

## 📚 Concepts Covered

### 1. Aggregation Functions

| Function | Purpose |
|---|---|
| `count()` | Counts rows |
| `sum()` | Calculates the total |
| `avg()` | Calculates the average |
| `min()` | Finds the minimum value |
| `max()` | Finds the maximum value |

### 2. Department-Wise Salary Analysis

The program calculates employee count, total salary, average salary, minimum salary, and maximum salary for each department.

### 3. Grouping by Multiple Columns

The program groups employee records by department and city to calculate employee counts and average salaries.

### 4. HAVING-Like Filtering

The program filters aggregated results to identify departments with an average salary greater than ₹55,000.

### 5. Hospital Revenue Analysis

The hospital scenario calculates patient visits, total revenue, average bill, minimum bill, and maximum bill for each department. It also calculates revenue by department and service.

## 🏥 Sample Results

### Department-Wise Salary Statistics

| Department | Employees | Total Salary |
|---|---:|---:|
| Finance | 2 | ₹135,000 |
| HR | 3 | ₹150,000 |
| IT | 3 | ₹215,000 |

### Hospital Department Revenue

| Department | Patient Visits | Total Revenue |
|---|---:|---:|
| Cardiology | 4 | ₹42,000 |
| Orthopedics | 3 | ₹40,000 |
| Neurology | 3 | ₹21,500 |
| **Total** | **10** | **₹103,500** |

*Note: The results are based on sample data created within the Scala program.*

## ▶️ How to Run

### Step 1: Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/scala-spark-day-16-aggregations.git
cd scala-spark-day-16-aggregations
```

### Step 2: Compile the project

```bash
sbt compile
```

### Step 3: Run the application

```bash
sbt "runMain Day16Aggregations"
```

## 💡 Key Learnings

- Using Spark SQL aggregation functions.
- Performing group-based analysis with DataFrames.
- Filtering aggregated data.
- Analyzing department-wise salaries and hospital revenue.
- Building and running Scala applications using SBT.

## 👩‍💻 Author

**Yelamaneni Bhavyasai**

## 📅 Training Progress

Day 16 of the 30 Days Scala and Apache Spark Training.
