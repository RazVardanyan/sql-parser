# SQL Parser

## Description

SQL Parser is a Java 17 Spring Boot project designed to parse SQL queries into Java Query objects. This project includes a sample SQL query in the `Main.java` class to demonstrate the functionality of the parser.

## Features

- Parses SQL `SELECT` queries into Java `Query` objects
- Supports tokenization of various SQL components
- Demonstrates functionality with a sample query

The SELECT query, java object presentation
```java
public class Query {
    private List<SelectItem> selectItems;
    private boolean isDistinct;
    private List<Source> source;
    private Condition where;
    private List<Join> joins;
    private List<String> groupByColumns;
    private Condition having;
    private List<OrderBy> orderByColumns;
    private int limit;
    private int offset;
    private Query subQuery;

}
```

## Prerequisites

- Java 17
- Spring Boot
- Maven

## Setup

1. Clone the repository:
   ```sh
   git clone https://github.com/RazVardanyan/sql-parser.git
   cd sql-parser
