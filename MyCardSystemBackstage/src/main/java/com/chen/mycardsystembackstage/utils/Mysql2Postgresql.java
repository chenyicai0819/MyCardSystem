package com.chen.mycardsystembackstage.utils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project : MyCardSystem - Mysql2Postgresql
 * Powered by GeorgeChen On 2023-02-20 18:29:49
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public class Mysql2Postgresql {
    public static void main(String[] args) throws IOException, JSQLParserException {
        // 你的MySQL DDL路径
        String mysqlDDLPath = "D:\\sz\\交付创新部\\儿早队列系统\\数据库\\仅结构\\t_report_chsq.sql";
        String dDLs = FileUtils.readFileToString(new File(mysqlDDLPath));

        System.out.println(dDLs);
        System.out.println("++++++++++开始转换SQL语句+++++++++++++");

        Statements statements = CCJSqlParserUtil.parseStatements(dDLs);

        statements.getStatements()
                .stream()
                .map(statement -> (CreateTable) statement).forEach(ct -> {
                    Table table = ct.getTable();
                    List<ColumnDefinition> columnDefinitions = ct.getColumnDefinitions();
                    List<String> comments = new ArrayList<>();
                    List<ColumnDefinition> collect = columnDefinitions.stream()
                            .peek(columnDefinition -> {
                                List<String> columnSpecStrings = columnDefinition.getColumnSpecStrings();

                                int commentIndex = getCommentIndex(columnSpecStrings);

                                if (commentIndex != -1) {
                                    int commentStringIndex = commentIndex + 1;
                                    String commentString = columnSpecStrings.get(commentStringIndex);

                                    String commentSql = genCommentSql(table.toString(), columnDefinition.getColumnName(), commentString);
                                    comments.add(commentSql);
                                    columnSpecStrings.remove(commentStringIndex);
                                    columnSpecStrings.remove(commentIndex);
                                }
                                columnDefinition.setColumnSpecStrings(columnSpecStrings);
                            }).collect(Collectors.toList());
                    ct.setColumnDefinitions(collect);
                    String createSQL = ct.toString()
                            .replaceAll("bigint \\([0-9]+\\)", "bigint")
//                    .replaceAll("varchar \\(255\\)", "varchar\\(255\\)")
                            .replaceAll("AUTO_INCREMENT", "")
                            .replaceAll("USING BTREE", "")
                            .replaceAll("`", "")
                            .replaceAll(" ENGINE = InnoDB = 191 DEFAULT CHARSET = utf8 ROW_FORMAT = COMPACT ", "")
                            .replaceAll("BIGINT UNIQUE NOT NULL AUTO_INCREMENT", "BIGSERIAL PRIMARY KEY")
                            .replaceAll("BIGINT NULL AUTO_INCREMENT", "BIGSERIAL PRIMARY KEY")
                            .replaceAll("BIGINT NOT NULL AUTO_INCREMENT", "BIGSERIAL PRIMARY KEY")
                            .replaceAll("INT NOT NULL AUTO_INCREMENT", "BIGSERIAL PRIMARY KEY")
                            .replaceAll("INT NULL AUTO_INCREMENT", "BIGSERIAL PRIMARY KEY")
                            .replaceAll("IF NOT EXISTS", "")
                            .replaceAll("TINYINT", "SMALLINT")
                            .replaceAll("DATETIME", "TIMESTAMP")
                            .replaceAll(", PRIMARY KEY \\(\"id\"\\)", "")
                            .replaceAll("DEFAULT NULL", "NULL")
                            .replaceAll(", KEY [a-z_A-Z0-9]+ \\(.*\\)", ")")
                            .replaceAll(" ENGINE .* COMPACT ", "")
                            .replaceAll("datetime", "timestamp")
                            .replaceAll("int \\([0-9]+\\)", "int")
                            .replaceAll(", UNIQUE KEY [a-z_A-Z0-9]+ \\(.*\\) ", "")
                            .replaceAll("ENGINE .* = utf8", "")
                            .replaceAll(" user ", " \"user\" ");

                    // 如果存在表注释
                    if (createSQL.contains("COMMENT")) {
                        createSQL = createSQL.substring(0, createSQL.indexOf("COMMENT"));
                    }
                    System.out.println(createSQL + ";");

                    comments.forEach(t -> System.out.println(t.replaceAll("`", "") + ";"));
                });
    }

    /**
     * 获得注释的下标
     *
     * @param columnSpecStrings columnSpecStrings
     * @return 下标
     */
    private static int getCommentIndex(List<String> columnSpecStrings) {
        for (int i = 0; i < columnSpecStrings.size(); i++) {
            if ("COMMENT".equalsIgnoreCase(columnSpecStrings.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 生成COMMENT语句
     *
     * @param table        表名
     * @param column       字段名
     * @param commentValue 描述文字
     * @return COMMENT语句
     */
    private static String genCommentSql(String table, String column, String commentValue) {
        return String.format("COMMENT ON COLUMN %s.%s IS %s", table, column, commentValue);
    }
}