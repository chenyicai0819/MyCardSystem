package com.chen.mycardsystembackstage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Project : MyCardSystem - TestStringUpdate
 * Powered by GeorgeChen On 2023-04-26 14:49:16
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 * 字符串滚动生成
 **/
@SpringBootTest
public class TestCreateTask {

    // 项目ID
    // private static String proId = "186921555620001";
    private static String proId = "188215423510001";
    // 工作流ID（过时）
    private static String taskId = "188220441550201";
    // 循环生成工作流ID前缀
    private static String taskIdsHead = "1922202415500";
    private static String taskIdsName = "river_1k";
    private static String taskIdsCnName = "1k节点工作流";
    // 来源表ID（mysql）
    private static String sourceResourceId = "188221564170008";
    // 目标表ID（mysql）
    private static String targetResourceId = "188221035430011";
    // 脚本任务数据源ID（hive）
    private static String dataSourceId = "188215714130017";
    // 文件存储位置
    private static String downloadUrl = "C:\\Users\\george\\Desktop\\1000节点\\";
    // 加锁人员
    private static String lockUserAccount = "wsq";
    // 循环生成集成任务前缀
    private static String integtationHead = "1482214820";
    private static String integtationName = "sjjc_1k";
    private static String integtationCnName = "1k数据集成";
    // 循环生成脚本任务前缀
    private static String scriptHead = "1482220453";
    private static String scriptName = "script_1k";
    private static String scriptCnName = "script_1k";
    // 工作流数量(+1)
    private static int treeNumber = 2;
    // 集成任务数量(+1)
    private static int integrationNumber = 501;
    // 脚本任务数量(+1)
    private static int scriptNumber = 501;

    @Test
    public void createTask(){
        tskDataLayout();
        tskTree();
        tskScript();
        integrationTask();
        nodeSchedule();
        nodeScheduleVersion();
        dAGScheduler();
        dAGScheduleVersion();

    }


    @Test
    public void integrationTask() {
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            for (int i = 1; i <= integrationNumber; i++) {
                String jcNum;
                if (i < 10) {
                    jcNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jcNum = "0" + i;
                } else {
                    jcNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"integrationTask\").insert( {\n" +
                        "    _id: NumberLong(\""+integtationHead+taskNum+ jcNum+"\"),\n" +
                        "    projectId: NumberLong(\""+proId+"\"),\n" +
                        "    taskId: NumberLong(\""+taskIds+"\"),\n" +
                        "    name: \""+integtationName+"_" + taskNum+ jcNum + "\",\n" +
                        "    cnName: \""+integtationCnName+"_" + taskNum+ jcNum + "\",\n" +
                        "    sourceResourceId: NumberLong(\""+sourceResourceId+"\"),\n" +
                        "    sourceTable: \"children\",\n" +
                        "    sourceTables: [ ],\n" +
                        "    targetResourceId: NumberLong(\""+targetResourceId+"\"),\n" +
                        "    targetTable: \"children\",\n" +
                        "    mode: \"FLINKX_JOB_VIEW\",\n" +
                        "    status: \"STAY_ONLINE\",\n" +
                        "    tableMappings: [\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"id\",\n" +
                        "            sourceFieldType: \"INT\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"id\",\n" +
                        "            targetFieldType: \"INT\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"name\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"name\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"school\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"school\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"sex\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"sex\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"age\",\n" +
                        "            sourceFieldType: \"INT\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"age\",\n" +
                        "            targetFieldType: \"INT\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"height\",\n" +
                        "            sourceFieldType: \"DOUBLE\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"height\",\n" +
                        "            targetFieldType: \"DOUBLE\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"weight\",\n" +
                        "            sourceFieldType: \"DOUBLE\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"weight\",\n" +
                        "            targetFieldType: \"DOUBLE\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    extras: {\n" +
                        "        lines: [\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"0\"),\n" +
                        "                targetOrder: NumberInt(\"0\"),\n" +
                        "                id: \"0_0\",\n" +
                        "                extras: {\n" +
                        "                    source: \"id_left\",\n" +
                        "                    target: \"id_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"1\"),\n" +
                        "                targetOrder: NumberInt(\"1\"),\n" +
                        "                id: \"1_1\",\n" +
                        "                extras: {\n" +
                        "                    source: \"name_left\",\n" +
                        "                    target: \"name_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"2\"),\n" +
                        "                targetOrder: NumberInt(\"2\"),\n" +
                        "                id: \"2_2\",\n" +
                        "                extras: {\n" +
                        "                    source: \"school_left\",\n" +
                        "                    target: \"school_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"3\"),\n" +
                        "                targetOrder: NumberInt(\"3\"),\n" +
                        "                id: \"3_3\",\n" +
                        "                extras: {\n" +
                        "                    source: \"sex_left\",\n" +
                        "                    target: \"sex_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"4\"),\n" +
                        "                targetOrder: NumberInt(\"4\"),\n" +
                        "                id: \"4_4\",\n" +
                        "                extras: {\n" +
                        "                    source: \"age_left\",\n" +
                        "                    target: \"age_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"5\"),\n" +
                        "                targetOrder: NumberInt(\"5\"),\n" +
                        "                id: \"5_5\",\n" +
                        "                extras: {\n" +
                        "                    source: \"height_left\",\n" +
                        "                    target: \"height_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"6\"),\n" +
                        "                targetOrder: NumberInt(\"6\"),\n" +
                        "                id: \"6_6\",\n" +
                        "                extras: {\n" +
                        "                    source: \"weight_left\",\n" +
                        "                    target: \"weight_right\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        sourceSchema: \"\",\n" +
                        "        targetSchema: \"\",\n" +
                        "        partitionSpec: \"\",\n" +
                        "        filter: \"\",\n" +
                        "        timeout: NumberInt(\"5000\"),\n" +
                        "        splitKey: \"\",\n" +
                        "        maxRowSplit: NumberInt(\"100000\"),\n" +
                        "        maxSizeSplit: NumberInt(\"50\"),\n" +
                        "        isTruncate: false,\n" +
                        "        mode: \"insert\",\n" +
                        "        preSql: \"\",\n" +
                        "        index: \"\",\n" +
                        "        postSql: \"\",\n" +
                        "        concurrentNum: \"1\",\n" +
                        "        readerChannel: \"1\",\n" +
                        "        memory: \"1\",\n" +
                        "        isFixedPartition: \"\",\n" +
                        "        isPartition: false,\n" +
                        "        record: NumberInt(\"1000\"),\n" +
                        "        partitionDefined: [ ],\n" +
                        "        catalog: \"\",\n" +
                        "        \"yarn-queue-1\": \"\",\n" +
                        "        \"yarn-queue-2\": \"\",\n" +
                        "        \"yarn-queue-3\": \"\",\n" +
                        "        errorLimitEnable: true,\n" +
                        "        errorPercentageLimit: NumberInt(\"1\"),\n" +
                        "        errorRecordLimit: NumberInt(\"10000\"),\n" +
                        "        \"source_fieldDelimiter\": \",\",\n" +
                        "        \"source_isFirstLineHeader\": false,\n" +
                        "        \"source_encoding\": \"UTF-8\",\n" +
                        "        sourceType: \"MYSQL\",\n" +
                        "        targetType: \"MYSQL\"\n" +
                        "    },\n" +
                        "    profiles: { },\n" +
                        "    runtimeEnvironment: { },\n" +
                        "    createTime: ISODate(\"2023-04-26T03:56:20.265Z\"),\n" +
                        "    createUser: \"big\",\n" +
                        "    updateTime: ISODate(\"2023-04-26T03:56:44.128Z\"),\n" +
                        "    updateUser: \"wsq\",\n" +
                        "    lockTime: ISODate(\"2023-04-26T03:56:20.265Z\"),\n" +
                        "    lockUserAccount: \""+lockUserAccount+"\"\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"integrationTask.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tskScript() {
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            for (int i = 1; i < scriptNumber; i++) {
                String jbNum = null;
                if (i < 10) {
                    jbNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jbNum = "0" + i;
                } else {
                    jbNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"tskScript\").insert( {\n" +
                        "    _id: NumberLong(\"" + scriptHead+taskNum+jbNum + "\"),\n" +
                        "    cnName: \""+scriptCnName+"_" + taskNum+jbNum + "\",\n" +
                        "    content: \"--\\n-- @Title : "+scriptName+"_" + taskNum+jbNum + "\\n-- @Kind  : hiveSQL\\n--\\n-- @Author: 王书棋\\n--\\n-- @Tips  : This is a UNCHOSEN TEMPLATE\\n--          AND you could write down your code below comment\\n--          OR getting rid of the comment before that\\n--\\n\\nSELECT 1;\",\n" +
                        "    createTime: ISODate(\"2023-04-26T04:02:39.585Z\"),\n" +
                        "    createUser: \"big\",\n" +
                        "    dataSourceId: NumberLong(\""+dataSourceId+"\"),\n" +
                        "    deleteTime: null,\n" +
                        "    deleteUser: null,\n" +
                        "    describe: null,\n" +
                        "    globalParams: [\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140001\"),\n" +
                        "            key: \"system.biz.date\",\n" +
                        "            value: \"$[yyyyMMdd-1]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间前一天，格式为 yyyyMMdd\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140002\"),\n" +
                        "            key: \"system.biz.curdate\",\n" +
                        "            value: \"$[yyyyMMdd]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间，格式为 yyyyMMdd\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140003\"),\n" +
                        "            key: \"system.datetime\",\n" +
                        "            value: \"$[yyyyMMddHHmmss]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间，格式为 yyyyMMddHHmmss\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false,\n" +
                        "            undeploy: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140010\"),\n" +
                        "            key: \"ENV_TYPE\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"当前环境标识，不同环境将自动切换，开发态：DEV；测试态：TEST；生产态：PROD\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140012\"),\n" +
                        "            key: \"HIVE_DATABASE\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"项目资源对应的hive数据库名称，不同环境将自动切换\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140013\"),\n" +
                        "            key: \"HIVE_TABLE_LOCATION_DIR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"Hive外部表HDFS存储路径，不同环境将自动切换\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270101\"),\n" +
                        "            key: \"DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"业务日期，格式：yyyyMMdd，例如： 20200816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270102\"),\n" +
                        "            key: \"DATE_YMD\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"业务日期，格式：yyyy-MM-dd，例如： 2020-08-16\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270104\"),\n" +
                        "            key: \"LAST1_DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一天，格式：yyyyMMdd，例如： 20200815\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270105\"),\n" +
                        "            key: \"AFTER1_DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一天，格式：yyyyMMdd，例如： 20200817\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +

                        "        {\n" +
                        "            _id: NumberLong(\"174439234270106\"),\n" +
                        "            key: \"LAST1_MONTH\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一月，格式：yyyyMMdd，例如： 20200716\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270107\"),\n" +
                        "            key: \"AFTER1_MONTH\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一月，格式：yyyyMMdd，例如： 20200916\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270108\"),\n" +
                        "            key: \"LAST1_YEAR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一年，格式：yyyyMMdd，例如： 20190816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270109\"),\n" +
                        "            key: \"AFTER1_YEAR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一年，格式：yyyyMMdd，例如： 20210816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    isDelete: false,\n" +
                        "    isLock: true,\n" +
                        "    labelIds: null,\n" +
                        "    localParams: null,\n" +
                        "    lockTime: ISODate(\"2023-04-26T04:02:39.585Z\"),\n" +
                        "    lockUser: \""+lockUserAccount+"\",\n" +
                        "    name: \""+scriptName+"_" + i + "\",\n" +
                        "    paramPairs: null,\n" +
                        "    proId: NumberLong(\""+proId+"\"),\n" +
                        "    schema: \"\",\n" +
                        "    sparkOnYarnParams: null,\n" +
                        "    targetTable: null,\n" +
                        "    taskId: NumberLong(\""+taskIds+"\"),\n" +
                        "    type: \"2\",\n" +
                        "    updateTime: ISODate(\"2023-04-26T04:05:08.751Z\"),\n" +
                        "    updateUser: \"wsq\"\n" +
                        "} );";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"tskScript.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tskScriptVersion() {
        StringBuffer sb = new StringBuffer();
        for (int j = 6; j < scriptNumber; j++) {
            String b = null;
            if (j < 10) {
                b = "00" + j;
            } else if (j >= 10 && j < 100) {
                b = "0" + j;
            } else {
                b = String.valueOf(j);
            }
            for (int i = 1; i < scriptNumber; i++) {
                String a = null;
                if (i < 10) {
                    a = "00" + i;
                } else if (i >= 10 && i < 100) {
                    a = "0" + i;
                } else {
                    a = String.valueOf(i);
                }
                String test = "db.getCollection(\"tskScriptVersion\").insert( {\n" +
                        "    _id: {\n" +
                        "        _id: NumberLong(\"198222045350"+ a + "\"),\n" +
                        "        version: \"V"+j+"\"\n" +
                        "    },\n" +
                        "    submitTime: ISODate(\"2023-04-26T07:11:34.747Z\"),\n" +
                        "    submitUser: \"wsq\",\n" +
                        "    comment: \"版本"+j+"号\",\n" +
                        "    isTestEnvironment: false,\n" +
                        "    isProEnvironment: false,\n" +
                        "    proId: NumberLong(\""+proId+"\"),\n" +
                        "    taskId: NumberLong(\""+taskId+"\"),\n" +
                        "    name: \""+scriptName+"_" + i + "\",\n" +
                        "    cnName: \""+scriptCnName+"_" + i + "\",\n" +
                        "    type: \"2\",\n" +
                        "    dataSourceId: NumberLong(\""+dataSourceId+"\"),\n" +
                        "    schema: \"\",\n" +
                        "    content: \"--\\n-- @Title : "+scriptName+"_" + i + "\\n-- @Kind  : hiveSQL\\n--\\n-- @Author: 王书棋\\n--\\n-- @Tips  : This is a UNCHOSEN TEMPLATE\\n--          AND you could write down your code below comment\\n--          OR getting rid of the comment before that\\n--\\n\\nSELECT 1;\",\n" +
                        "    versionStatus: \"STAY_TEST\",\n" +
                        "    globalParams: [\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140001\"),\n" +
                        "            key: \"system.biz.date\",\n" +
                        "            value: \"$[yyyyMMdd-1]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间前一天，格式为 yyyyMMdd\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140002\"),\n" +
                        "            key: \"system.biz.curdate\",\n" +
                        "            value: \"$[yyyyMMdd]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间，格式为 yyyyMMdd\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140003\"),\n" +
                        "            key: \"system.datetime\",\n" +
                        "            value: \"$[yyyyMMddHHmmss]\",\n" +
                        "            desc: \"日常调度实例定时的定时时间，格式为 yyyyMMddHHmmss\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false,\n" +
                        "            undeploy: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140010\"),\n" +
                        "            key: \"ENV_TYPE\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"当前环境标识，不同环境将自动切换，开发态：DEV；测试态：TEST；生产态：PROD\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140012\"),\n" +
                        "            key: \"HIVE_DATABASE\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"项目资源对应的hive数据库名称，不同环境将自动切换\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439465140013\"),\n" +
                        "            key: \"HIVE_TABLE_LOCATION_DIR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"Hive外部表HDFS存储路径，不同环境将自动切换\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: false\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270101\"),\n" +
                        "            key: \"DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"业务日期，格式：yyyyMMdd，例如： 20200816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270102\"),\n" +
                        "            key: \"DATE_YMD\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"业务日期，格式：yyyy-MM-dd，例如： 2020-08-16\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270104\"),\n" +
                        "            key: \"LAST1_DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一天，格式：yyyyMMdd，例如： 20200815\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270105\"),\n" +
                        "            key: \"AFTER1_DATE_NO\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一天，格式：yyyyMMdd，例如： 20200817\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270106\"),\n" +
                        "            key: \"LAST1_MONTH\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一月，格式：yyyyMMdd，例如： 20200716\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270107\"),\n" +
                        "            key: \"AFTER1_MONTH\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一月，格式：yyyyMMdd，例如： 20200916\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270108\"),\n" +
                        "            key: \"LAST1_YEAR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"前一年，格式：yyyyMMdd，例如： 20190816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        },\n" +
                        "        {\n" +
                        "            _id: NumberLong(\"174439234270109\"),\n" +
                        "            key: \"AFTER1_YEAR\",\n" +
                        "            value: \"\",\n" +
                        "            desc: \"后一年，格式：yyyyMMdd，例如： 20210816\",\n" +
                        "            type: \"1\",\n" +
                        "            hide: true\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    createUser: \"big\",\n" +
                        "    createTime: ISODate(\"2023-04-26T04:02:39.585Z\")\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"tskScriptVersion_2.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void integrationVersion(){
        StringBuffer sb =new StringBuffer();
        for (int j = 7; j < scriptNumber; j++) {
            String b = null;
            if (j < 10) {
                b = "00" + j;
            } else if (j >= 10 && j < 100) {
                b = "0" + j;
            } else {
                b = String.valueOf(j);
            }
            for (int i = 1; i < integrationNumber; i++) {
                String a = null;
                if (i < 10) {
                    a = "00" + i;
                } else if (i >= 10 && i < 100) {
                    a = "0" + i;
                } else {
                    a = String.valueOf(i);
                }
                String test = "db.getCollection(\"integrationVersion\").insert( {\n" +
                        "    _id: {\n" +
                        "        _id: NumberLong(\"198221482070"+a+"\"),\n" +
                        "        version: \"V"+j+"\"\n" +
                        "    },\n" +
                        "    version: \"V"+j+"\",\n" +
                        "    submitTime: ISODate(\"2023-04-26T07:12:15.25Z\"),\n" +
                        "    submitUser: \"wsq\",\n" +
                        "    comment: \"版本"+j+"号\",\n" +
                        "    isTestEnvironment: false,\n" +
                        "    isProEnvironment: false,\n" +
                        "    id: NumberLong(\"198221482070"+a+"\"),\n" +
                        "    projectId: NumberLong(\""+proId+"\"),\n" +
                        "    taskId: NumberLong(\""+taskId+"\"),\n" +
                        "    name: \""+integtationName+"_"+i+"\",\n" +
                        "    cnName: \""+integtationCnName+"_"+i+"\",\n" +
                        "    sourceResourceId: NumberLong(\""+sourceResourceId+"\"),\n" +
                        "    sourceTable: \"children\",\n" +
                        "    sourceTables: [ ],\n" +
                        "    targetResourceId: NumberLong(\""+targetResourceId+"\"),\n" +
                        "    targetTable: \"children\",\n" +
                        "    mode: \"FLINKX_JOB_VIEW\",\n" +
                        "    tableMappings: [\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"id\",\n" +
                        "            sourceFieldType: \"INT\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"id\",\n" +
                        "            targetFieldType: \"INT\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"name\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"name\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"school\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"school\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"sex\",\n" +
                        "            sourceFieldType: \"VARCHAR\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"sex\",\n" +
                        "            targetFieldType: \"VARCHAR\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"age\",\n" +
                        "            sourceFieldType: \"INT\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"age\",\n" +
                        "            targetFieldType: \"INT\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"height\",\n" +
                        "            sourceFieldType: \"DOUBLE\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"height\",\n" +
                        "            targetFieldType: \"DOUBLE\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            sourceFieldId: \"\",\n" +
                        "            sourceFieldName: \"weight\",\n" +
                        "            sourceFieldType: \"DOUBLE\",\n" +
                        "            targetFieldId: \"\",\n" +
                        "            targetFieldName: \"weight\",\n" +
                        "            targetFieldType: \"DOUBLE\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    extras: {\n" +
                        "        lines: [\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"0\"),\n" +
                        "                targetOrder: NumberInt(\"0\"),\n" +
                        "                id: \"0_0\",\n" +
                        "                extras: {\n" +
                        "                    source: \"id_left\",\n" +
                        "                    target: \"id_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"1\"),\n" +
                        "                targetOrder: NumberInt(\"1\"),\n" +
                        "                id: \"1_1\",\n" +
                        "                extras: {\n" +
                        "                    source: \"name_left\",\n" +
                        "                    target: \"name_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"2\"),\n" +
                        "                targetOrder: NumberInt(\"2\"),\n" +
                        "                id: \"2_2\",\n" +
                        "                extras: {\n" +
                        "                    source: \"school_left\",\n" +
                        "                    target: \"school_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"3\"),\n" +
                        "                targetOrder: NumberInt(\"3\"),\n" +
                        "                id: \"3_3\",\n" +
                        "                extras: {\n" +
                        "                    source: \"sex_left\",\n" +
                        "                    target: \"sex_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"4\"),\n" +
                        "                targetOrder: NumberInt(\"4\"),\n" +
                        "                id: \"4_4\",\n" +
                        "                extras: {\n" +
                        "                    source: \"age_left\",\n" +
                        "                    target: \"age_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"5\"),\n" +
                        "                targetOrder: NumberInt(\"5\"),\n" +
                        "                id: \"5_5\",\n" +
                        "                extras: {\n" +
                        "                    source: \"height_left\",\n" +
                        "                    target: \"height_right\"\n" +
                        "                }\n" +
                        "            },\n" +
                        "            {\n" +
                        "                sourceOrder: NumberInt(\"6\"),\n" +
                        "                targetOrder: NumberInt(\"6\"),\n" +
                        "                id: \"6_6\",\n" +
                        "                extras: {\n" +
                        "                    source: \"weight_left\",\n" +
                        "                    target: \"weight_right\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        sourceSchema: \"\",\n" +
                        "        targetSchema: \"\",\n" +
                        "        partitionSpec: \"\",\n" +
                        "        filter: \"\",\n" +
                        "        timeout: NumberInt(\"5000\"),\n" +
                        "        splitKey: \"\",\n" +
                        "        maxRowSplit: NumberInt(\"100000\"),\n" +
                        "        maxSizeSplit: NumberInt(\"50\"),\n" +
                        "        isTruncate: false,\n" +
                        "        mode: \"insert\",\n" +
                        "        preSql: \"\",\n" +
                        "        index: \"\",\n" +
                        "        postSql: \"\",\n" +
                        "        concurrentNum: \"1\",\n" +
                        "        readerChannel: \"1\",\n" +
                        "        memory: \"1\",\n" +
                        "        isFixedPartition: \"\",\n" +
                        "        isPartition: \"\",\n" +
                        "        record: NumberInt(\"1000\"),\n" +
                        "        partitionDefined: [ ],\n" +
                        "        catalog: \"\",\n" +
                        "        \"yarn-queue-1\": \"\",\n" +
                        "        \"yarn-queue-2\": \"\",\n" +
                        "        \"yarn-queue-3\": \"\",\n" +
                        "        errorLimitEnable: true,\n" +
                        "        errorPercentageLimit: NumberInt(\"1\"),\n" +
                        "        errorRecordLimit: NumberInt(\"10000\"),\n" +
                        "        \"source_fieldDelimiter\": \",\",\n" +
                        "        \"source_isFirstLineHeader\": false,\n" +
                        "        \"source_encoding\": \"UTF-8\",\n" +
                        "        sourceType: \"MYSQL\",\n" +
                        "        targetType: \"MYSQL\"\n" +
                        "    },\n" +
                        "    profiles: { },\n" +
                        "    runtimeEnvironment: { },\n" +
                        "    versionStatus: \"STAY_TEST\",\n" +
                        "    createTime: ISODate(\"2023-04-26T03:56:20.265Z\"),\n" +
                        "    createUser: \"big\",\n" +
                        "    updateTime: ISODate(\"2023-04-26T07:12:15.046Z\"),\n" +
                        "    updateUser: \"wsq\",\n" +
                        "    lockTime: ISODate(\"2023-04-26T03:56:20.265Z\"),\n" +
                        "    lockUserAccount: \""+lockUserAccount+"\"\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"integrationVersion_2.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tskTree(){
        StringBuffer sb =new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            sb.append("db.getCollection(\"tskTree\").insert( {\n" +
                    "    _id: NumberLong(\""+taskIds+"\"),\n" +
                    "    proId: NumberLong(\""+proId+"\"),\n" +
                    "    name: \""+taskIdsName+"_"+taskNum+"\",\n" +
                    "    cnName: \""+taskIdsCnName+""+taskNum+"\",\n" +
                    "    description: \"\",\n" +
                    "    type: \"BATCH\",\n" +
                    "    createUser: \"big\",\n" +
                    "    createTime: ISODate(\"2023-04-26T03:38:36.662Z\"),\n" +
                    "    isDelete: false,\n" +
                    "    integrations: [").append("\n");
            for (int i = 1; i < integrationNumber; i++) {
                String jcNum;
                if (i < 10) {
                    jcNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jcNum = "0" + i;
                } else {
                    jcNum = String.valueOf(i);
                }
                String text_1 = "{\n" +
                        "            _id: NumberLong(\""+integtationHead+taskNum+jcNum+"\"),\n" +
                        "            name: \""+integtationName+"_"+taskNum+jcNum+"\",\n" +
                        "            cnName: \""+integtationCnName+"_"+taskNum+jcNum+"\",\n" +
                        "            dir: false,\n" +
                        "            extras: {\n" +
                        "                y: NumberInt(\"50\")\n" +
                        "            }\n" +
                        "        },";
                sb.append(text_1).append("\n");

            }
            sb.append("],\n" +
                    "    updateTime: ISODate(\"2023-04-26T06:18:15.922Z\"),\n" +
                    "    updateUser: \"wsq\",\n" +
                    "    developments: [");
            for (int i = 1; i < scriptNumber; i++) {
                String jbNum = null;
                if (i < 10) {
                    jbNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jbNum = "0" + i;
                } else {
                    jbNum = String.valueOf(i);
                }
                String test_2 = "{\n" +
                        "            _id: NumberLong(\""+scriptHead+taskNum+jbNum+"\"),\n" +
                        "            name: \""+scriptName+"_"+taskNum+jbNum+"\",\n" +
                        "            cnName: \""+scriptCnName+"_"+taskNum+jbNum+"\",\n" +
                        "            dir: false,\n" +
                        "            type: \"2\",\n" +
                        "            extras: {\n" +
                        "                y: NumberInt(\"50\")\n" +
                        "            }\n" +
                        "        },";
                sb.append(test_2).append("\n");
            }
            sb.append("]\n" +
                    "} );");
            sb.append("\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"tskTree.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tskDataLayout(){
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;

            sb.append("db.getCollection(\"tskDataLayout\").insert( {\n" +
                    "    _id: NumberLong(\""+taskIds+"\"),\n" +
                    "    taskId: NumberLong(\""+taskIds+"\"),\n" +
                    "    flowGraph: {\n" +
                    "        nodes: [").append("\n");
            for (int i = 1; i < integrationNumber; i++) {
                String jcNum = null;
                if (i < 10) {
                    jcNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jcNum = "0" + i;
                } else {
                    jcNum = String.valueOf(i);
                }
                String test_1 = "{\n" +
                        "                _id: \""+integtationHead+taskNum+jcNum+"\",\n" +
                        "                name: \""+integtationName+"_"+taskNum+jcNum+"\",\n" +
                        "                extras: {\n" +
                        "                    x: NumberInt(\"0\"),\n" +
                        "                    y: NumberInt(\"-60\"),\n" +
                        "                    icon: \"icon-dataSyn\",\n" +
                        "                    itemType: \"dataIntegrationsItem\"\n" +
                        "                }\n" +
                        "            },";
                sb.append(test_1).append("\n");
            }
            for (int i = 1; i < scriptNumber; i++) {
                String jbNum = null;
                if (i < 10) {
                    jbNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jbNum = "0" + i;
                } else {
                    jbNum = String.valueOf(i);
                }
                String test_1 = "{\n" +
                        "                _id: \""+scriptHead+taskNum+jbNum+"\",\n" +
                        "                name: \""+scriptName+"_"+taskNum+jbNum+"\",\n" +
                        "                extras: {\n" +
                        "                    x: NumberInt(\"0\"),\n" +
                        "                    y: NumberInt(\"-120\"),\n" +
                        "                    icon: \"icon-hivesql\",\n" +
                        "                    itemType: \"dataDevelopments\"\n" +
                        "                }\n" +
                        "            },";
                sb.append(test_1).append("\n");
            }
            sb.append("],\n" +
                    "        edges: [\n" +
                    "        ],\n" +
                    "        extras: {\n" +
                    "            layout: \"horizontal\"\n" +
                    "        }\n" +
                    "    },\n" +
                    "    registerStatus: \"UNREGISTERED\",\n" +
                    "    registered: false,\n" +
                    "    createUser: \"big\",\n" +
                    "    createTime: ISODate(\"2023-04-26T03:38:36.943Z\"),\n" +
                    "    isDelete: false,\n" +
                    "    lockTime: ISODate(\"2023-04-26T03:38:36.944Z\"),\n" +
                    "    lockUser: \""+lockUserAccount+"\",\n" +
                    "    ignoreScheduler: null,\n" +
                    "    updateTime: ISODate(\"2023-04-26T06:44:38.222Z\"),\n" +
                    "    updateUser: \"wsq\"\n" +
                    "} );");
            sb.append("\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"tskDataLayout.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tskDataLayoutVersion(){
        StringBuffer sb = new StringBuffer();
        sb.append("db.getCollection(\"tskDataLayoutVersion\").insert( {\n" +
                "    _id: {\n" +
                "        _id: NumberLong(\"188220442150004\"),\n" +
                "        version: \"V1\"\n" +
                "    },\n" +
                "    submitTime: ISODate(\"2023-04-26T07:27:23.401Z\"),\n" +
                "    submitUser: \"wsq\",\n" +
                "    comment: \"版本一号\",\n" +
                "    taskId: NumberLong(\""+taskId+"\"),\n" +
                "    task: {\n" +
                "        _id: NumberLong(\""+taskId+"\"),\n" +
                "        proId: NumberLong(\""+proId+"\"),\n" +
                "        name: \"test_river\",\n" +
                "        cnName: \"测试数据工作流\",\n" +
                "        description: \"\",\n" +
                "        type: \"BATCH\",\n" +
                "        integrations: [").append("\n");
        for (int i = 1; i < integrationNumber; i++) {
            String a = null;
            if (i < 10) {
                a = "00" + i;
            } else if (i >= 10 && i < 100) {
                a = "0" + i;
            } else {
                a = String.valueOf(i);
            }
            String test_1 = "{\n" +
                    "                _id: NumberLong(\"198221482070"+a+"\"),\n" +
                    "                name: \""+integtationName+"_"+i+"\",\n" +
                    "                cnName: \""+integtationCnName+"_"+i+"\",\n" +
                    "                dir: false,\n" +
                    "                extras: {\n" +
                    "                    y: NumberInt(\"50\")\n" +
                    "                }\n" +
                    "            },";
            sb.append(test_1).append("\n");
        }
        sb.append("],\n" +
                "        developments: [").append("\n");
        for (int i = 1; i < scriptNumber; i++) {
            String a = null;
            if (i < 10) {
                a = "00" + i;
            } else if (i >= 10 && i < 100) {
                a = "0" + i;
            } else {
                a = String.valueOf(i);
            }
            String test_1 = "{\n" +
                    "                _id: NumberLong(\"198222045350"+a+"\"),\n" +
                    "                name: \""+scriptName+"_"+i+"\",\n" +
                    "                cnName: \""+scriptCnName+"_"+i+"\",\n" +
                    "                dir: false,\n" +
                    "                type: \"2\",\n" +
                    "                extras: {\n" +
                    "                    y: NumberInt(\"50\")\n" +
                    "                }\n" +
                    "            },";
            sb.append(test_1).append("\n");
        }
        sb.append("],\n" +
                "        createUser: \"big\",\n" +
                "        createTime: ISODate(\"2023-04-26T03:38:36.662Z\"),\n" +
                "        updateUser: \"wsq\",\n" +
                "        updateTime: ISODate(\"2023-04-26T07:11:03.28Z\"),\n" +
                "        isDelete: false\n" +
                "    },\n" +
                "    versionStatus: \"STAY_TEST\",\n" +
                "    flowGraph: {\n" +
                "        nodes: [");
        for (int i = 1; i < integrationNumber; i++) {
            String a = null;
            if (i < 10) {
                a = "00" + i;
            } else if (i >= 10 && i < 100) {
                a = "0" + i;
            } else {
                a = String.valueOf(i);
            }
            String test_1 = "{\n" +
                    "                _id: \"198221482070"+a+"\",\n" +
                    "                name: \""+integtationName+"_"+i+"\",\n" +
                    "                extras: {\n" +
                    "                    x: NumberInt(\"0\"),\n" +
                    "                    y: NumberInt(\"-60\"),\n" +
                    "                    icon: \"icon-dataSyn\",\n" +
                    "                    itemType: \"dataIntegrationsItem\"\n" +
                    "                }\n" +
                    "            },";
            sb.append(test_1).append("\n");
        }
        for (int i = 1; i < scriptNumber; i++) {
            String a = null;
            if (i < 10) {
                a = "00" + i;
            } else if (i >= 10 && i < 100) {
                a = "0" + i;
            } else {
                a = String.valueOf(i);
            }
            String test_1 = "{\n" +
                    "                _id: \"198222045350"+a+"\",\n" +
                    "                name: \""+scriptName+"_"+i+"\",\n" +
                    "                extras: {\n" +
                    "                    x: NumberInt(\"0\"),\n" +
                    "                    y: NumberInt(\"-120\"),\n" +
                    "                    icon: \"icon-hivesql\",\n" +
                    "                    itemType: \"dataDevelopments\"\n" +
                    "                }\n" +
                    "            },";
            sb.append(test_1).append("\n");
        }
        sb.append("],\n" +
                "        edges: [ ],\n" +
                "        extras: {\n" +
                "            layout: \"horizontal\"\n" +
                "        }\n" +
                "    },\n" +
                "    createUser: \"big\",\n" +
                "    createTime: ISODate(\"2023-04-26T03:38:36.943Z\")\n" +
                "} );");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"tskDataLayoutVersion_1.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void nodeSchedule(){
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            for (int i = 1; i < integrationNumber; i++) {
                String jcNum;
                if (i < 10) {
                    jcNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jcNum = "0" + i;
                } else {
                    jcNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"nodeSchedule\").insert( {\n" +
                        "    _id: NumberLong(\""+integtationHead+taskNum+jcNum+"\"),\n" +
                        "    resType: \"1\",\n" +
                        "    resId: NumberLong(\""+integtationHead+taskNum+jcNum+"\"),\n" +
                        "    priority: \"MEDIUM\",\n" +
                        "    maxRetryTimes: NumberInt(\"0\"),\n" +
                        "    timeoutEnable: false,\n" +
                        "    timeoutInterval: NumberInt(\"1\"),\n" +
                        "    crossEnvConfig: {\n" +
                        "        enable: false,\n" +
                        "        forceDownStreamRun: false\n" +
                        "    },\n" +
                        "    createUser: \"big\",\n" +
                        "    createTime: ISODate(\"2023-04-26T03:56:20.355Z\"),\n" +
                        "    isDelete: false\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
            for (int i = 1; i < scriptNumber; i++) {
                String jbNum;
                if (i < 10) {
                    jbNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jbNum = "0" + i;
                } else {
                    jbNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"nodeSchedule\").insert( {\n" +
                        "    _id: NumberLong(\""+scriptHead+taskNum+jbNum+"\"),\n" +
                        "    resType: \"2\",\n" +
                        "    resId: NumberLong(\""+scriptHead+taskNum+jbNum+"\"),\n" +
                        "    priority: \"MEDIUM\",\n" +
                        "    maxRetryTimes: NumberInt(\"0\"),\n" +
                        "    timeoutEnable: false,\n" +
                        "    timeoutInterval: NumberInt(\"1\"),\n" +
                        "    crossEnvConfig: {\n" +
                        "        enable: false,\n" +
                        "        forceDownStreamRun: false\n" +
                        "    },\n" +
                        "    createUser: \"big\",\n" +
                        "    createTime: ISODate(\"2023-04-26T04:02:39.741Z\"),\n" +
                        "    isDelete: false\n" +
                        "} );";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"nodeSchedule.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void nodeScheduleVersion(){
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            for (int i = 1; i < integrationNumber; i++) {
                String jcNum;
                if (i < 10) {
                    jcNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jcNum = "0" + i;
                } else {
                    jcNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"nodeScheduleVersion\").insert( {\n" +
                        "    _id: {\n" +
                        "        _id: NumberLong(\""+integtationHead+taskNum+jcNum+"\"),\n" +
                        "        version: \"V1\"\n" +
                        "    },\n" +
                        "    submitTime: ISODate(\"2023-04-26T07:27:23.45Z\"),\n" +
                        "    submitUser: \"wsq\",\n" +
                        "    resType: \"1\",\n" +
                        "    resId: NumberLong(\""+integtationHead+taskNum+jcNum+"\"),\n" +
                        "    priority: \"MEDIUM\",\n" +
                        "    maxRetryTimes: NumberInt(\"0\"),\n" +
                        "    timeoutEnable: false,\n" +
                        "    timeoutInterval: NumberInt(\"1\"),\n" +
                        "    crossEnvConfig: {\n" +
                        "        enable: false,\n" +
                        "        forceDownStreamRun: false\n" +
                        "    },\n" +
                        "    createUser: \"big\",\n" +
                        "    createTime: ISODate(\"2023-04-26T03:56:20.355Z\"),\n" +
                        "    isDelete: false\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
            for (int i = 1; i < scriptNumber; i++) {
                String jbNum;
                if (i < 10) {
                    jbNum = "00" + i;
                } else if (i >= 10 && i < 100) {
                    jbNum = "0" + i;
                } else {
                    jbNum = String.valueOf(i);
                }
                String test = "db.getCollection(\"nodeScheduleVersion\").insert( {\n" +
                        "    _id: {\n" +
                        "        _id: NumberLong(\""+scriptHead+taskNum+jbNum+"\"),\n" +
                        "        version: \"V1\"\n" +
                        "    },\n" +
                        "    submitTime: ISODate(\"2023-04-26T07:27:23.467Z\"),\n" +
                        "    submitUser: \"wsq\",\n" +
                        "    resType: \"2\",\n" +
                        "    resId: NumberLong(\""+scriptHead+taskNum+jbNum+"\"),\n" +
                        "    priority: \"MEDIUM\",\n" +
                        "    maxRetryTimes: NumberInt(\"0\"),\n" +
                        "    timeoutEnable: false,\n" +
                        "    timeoutInterval: NumberInt(\"1\"),\n" +
                        "    crossEnvConfig: {\n" +
                        "        enable: false,\n" +
                        "        forceDownStreamRun: false\n" +
                        "    },\n" +
                        "    createUser: \"big\",\n" +
                        "    createTime: ISODate(\"2023-04-26T04:02:39.741Z\"),\n" +
                        "    isDelete: false\n" +
                        "} );\n";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"nodeScheduleVersion.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dAGScheduler(){
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            String test = "db.getCollection(\"dAGSchedule\").insert( {\n" +
                    "    _id: NumberLong(\""+taskIds+"\"),\n" +
                    "    resId: NumberLong(\""+taskIds+"\"),\n" +
                    "    type: \"2\",\n" +
                    "    testType: \"2\",\n" +
                    "    failureStrategy: \"END\",\n" +
                    "    processInstancePriority: \"MEDIUM\",\n" +
                    "    warningType: \"NONE\",\n" +
                    "    createUser: \"wsq\",\n" +
                    "    createTime: ISODate(\"2023-04-26T03:38:36.952Z\"),\n" +
                    "    isDelete: false,\n" +
                    "    notifiers: [ ],\n" +
                    "    receivers: \"\",\n" +
                    "    receiversCc: \"\",\n" +
                    "    updateTime: ISODate(\"2023-04-26T10:08:56.46Z\"),\n" +
                    "    updateUser: \"wsq\"\n" +
                    "} );";
            sb.append(test).append("\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"dAGScheduler.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void dAGScheduleVersion(){
        StringBuffer sb = new StringBuffer();
        String taskIds;
        for (int j = 1; j < treeNumber; j++) {
            String taskNum;
            if (j < 10){
                taskNum = "0"+j;
            }else {
                taskNum = String.valueOf(j);
            }
            taskIds = taskIdsHead+taskNum;
            String test = "db.getCollection(\"dAGScheduleVersion\").insert( {\n" +
                    "    _id: {\n" +
                    "        _id: NumberLong(\""+taskIds+"\"),\n" +
                    "        version: \"V1\"\n" +
                    "    },\n" +
                    "    submitTime: ISODate(\"2023-04-26T07:27:23.421Z\"),\n" +
                    "    submitUser: \"wsq\",\n" +
                    "    resId: NumberLong(\""+taskIds+"\"),\n" +
                    "    type: \"2\",\n" +
                    "    testType: \"2\",\n" +
                    "    failureStrategy: \"END\",\n" +
                    "    processInstancePriority: \"MEDIUM\",\n" +
                    "    warningType: \"NONE\",\n" +
                    "    createUser: \"wsq\",\n" +
                    "    createTime: ISODate(\"2023-04-26T03:38:36.952Z\"),\n" +
                    "    isDelete: false\n" +
                    "} );\n";
            sb.append(test).append("\n");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"dAGScheduleVersion.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteTskScriptVersion() {
        StringBuffer sb = new StringBuffer();
        for (int j = 6; j < scriptNumber; j++) {
            String b = null;
            if (j < 10) {
                b = "00" + j;
            } else if (j >= 10 && j < 100) {
                b = "0" + j;
            } else {
                b = String.valueOf(j);
            }
            for (int i = 1; i < scriptNumber; i++) {
                String a = null;
                if (i < 10) {
                    a = "00" + i;
                } else if (i >= 10 && i < 100) {
                    a = "0" + i;
                } else {
                    a = String.valueOf(i);
                }
                String test = "db.getCollection(\"tskScriptVersion\").remove({ _id: { _id: NumberLong(\"198222045"+b +a+"\"), version: \"V"+j+"\" } }, { justOne: true })";
                sb.append(test.toString()).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"delete_script_1.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteIntegrationVersion(){
        StringBuffer sb =new StringBuffer();
        for (int j = 6; j < scriptNumber; j++) {
            String b = null;
            if (j < 10) {
                b = "00" + j;
            } else if (j >= 10 && j < 100) {
                b = "0" + j;
            } else {
                b = String.valueOf(j);
            }
            for (int i = 1; i < integrationNumber; i++) {
                String a = null;
                if (i < 10) {
                    a = "00" + i;
                } else if (i >= 10 && i < 100) {
                    a = "0" + i;
                } else {
                    a = String.valueOf(i);
                }
                String test = "db.getCollection(\"integrationVersion\").remove( { _id: { _id: NumberLong(\"19822148"+b+"0"+a+"\"), version: \"V"+j+"\" } }, { justOne: true } )";
                sb.append(test).append("\n");
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(downloadUrl+"delete_integration_1.js", false));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
