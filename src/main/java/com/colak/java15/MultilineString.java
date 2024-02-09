package com.colak.java15;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultilineString {

    public static void main(String[] args) {
        String tableName = "all_unicode_types_table";
        String createTableSql = """
                CREATE TABLE %s (
                    nvarcharColumn NVARCHAR(100),
                    ncharColumn NCHAR(100),
                    ntextColumn NTEXT
                );
                """.formatted(tableName);

        log.info("Sql : {}", createTableSql);

        String TEST_DATABASE_REF = "testDatabaseRef";
        String createMappingSql = """
                CREATE MAPPING %s (
                  nvarcharColumn NVARCHAR,
                  ncharColumn NVARCHAR,
                  ntextColumn NVARCHAR
                  ) DATA CONNECTION %s"
                """.formatted(tableName, TEST_DATABASE_REF);
        log.info("Sql : {}", createMappingSql);

    }
}
