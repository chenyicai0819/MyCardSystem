/*
 */
package com.chen.mycardsystembackstage.common.responsecode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回的错误代码, 返回前端用 int, 后端用枚举, 可以减少数据传输
 * 可以基于 code 的 name 进行国际化
 * 这里只列出系统默认的 code, 其他的 code, 应该按 CodePerson 这样进行区分
 * 建议划分方法, 使用 9 位数字 (一个 int 可以表示完全), 第一位固定是 1, 接下来 4 位划分模块, 最后四位划分具体的 errorCode;
 * 如: 100010001, 表示系统错误
 * @author MoSence
 */
@AllArgsConstructor
@Getter
public enum CodeDefault implements CodeEnum{
    /**
     *
     */

    OK(0, "成功"),
    PARTIAL_SUCCESS(100010001L, "部分成功"),
    INTERNAL_SERVER_ERROR(100010002L, "未处理异常"),
    ILLEGAL_ARGUMENT(100010003L, "客户端输入参数错误"),
    PRIMARY_ID_ERROR(100010004L, "主键生成错误"),
    ILLEGAL_DATE_FORMAT(100010005L, "日期格式错误"),
    NULL_POINT_ERROR(100010006L, "空值异常"),
    ID_NULL_ERROR(100010007L,"该主键不存在"),
    ACCOUNT_EXIST(100010008L,"操作失败！该账号已经存在,不能重复添加"),
    ACCOUNT_NOT_EXIST(100010009L,"用户账号不存在"),
    PASSWORD_ERROR(100010010L,"用户密码不正确"),
    PASSWORD_DECRYPT_ERROR(100010011L,"密码解密异常"),
    DOCTOR_ADVISE_TOO_LONG(100010012L,"医生建议过长"),
    EXCEL_DOWNLOAD_FAILURE(100010012L,"Excel下载失败"),
    EXCEL_UPLOAD_FAILURE(100010013L,"Excel上传失败"),
    VERSION_IS_NOT_NUMBER(100010014L,"版本号必须为数字"),
    USER_NOT_DATA(100010015L,"该用户暂无数据"),
    IDCARD_EXIST(100010016L,"该身份证已存在"),
    INVALID_IDCARD(100010017L,"身份证校验失败"),
    FILE_EXISTS(100010018L,"该文件已存在"),
    UPDATE_FAILURE(100010018L,"更新失败"),
    PHONE_EXISTS(100010019L,"电话号码已经存在"),
    PHONE_FORMAT_ERROR(100010019L,"电话号码格式错误"),

    STILL_EXIST_SURVEY_IN_CLASSIFICATION(100020001L,"当前问卷分类下仍存在问卷，不允许删除该问卷分类"),
    STILL_EXIST_SUB_CLASSIFICATION(100020002L,"当前问卷分类下仍存在子分类，不允许删除该问卷分类"),
    DUPLICATED_SURVEY_NAME(100020003L,"存在同名的问卷"),
    DUPLICATED_PROGRAM_VERSION(100020004L,"该方案下已存在该版本号"),
    UPDATE_SURVEY_CLASSIFICATION_FAIL(100020005L,"更新分类层级失败，请勿超过两层"),
    EXIST_CLASSIFICATION_NAME(100020006L,"已经存在同名的问卷分类"),
    EXIST_VOLUNTEER_IN_QUEUE(100020007L,"该队列下仍存在志愿者，不允许删除"),
    EXIST_VOLUNTEER_IN_PROGRAM(100020008L,"该方案下仍存在志愿者，不允许删除"),
    EXIST_QUEUE_NAME(100020009L,"该分类下已经存在同名的队列"),
    SURVEY_HAS_BEEN_USED(100020010L,"问卷仍然被使用，不允许删除"),
    CAN_NOT_DISABLE_PROGRAM(100020011L,"该方案下仍存在志愿者，不允许下线"),
    NOT_SIGNE_INFORMED_CONSENT(100020012L,"未签署知情同意书，审核失败！"),
    VOLUNTEER_HAS_BEEN_AUDIT(100020013L,"该志愿者已被审核！"),
    AREA_NOT_DATA(100020014L,"该地区暂无统计数据"),
    CANT_GET_TRIGGER_TIME(100020015L,"无法获取阶段的触发时间!"),
    CANT_FIND_VOLUNTEER_REGION(100020016L,"该志愿者所属的地区不在儿早现况统计范围内!"),
    EXCEED_AGE_LIMIT(100020017L,"志愿者超过建档年龄限制!"),
    AUDIO_FORMAT_ERROR(100020018L,"音频文件格式错误"),
    GUARDIAN_ADD_FILURE(100020019L,"监护人新增失败"),
    ADDRESS_ADD_FILURE(100020020L,"地址信息新增失败"),
    DATA_IS_EMPTY(100020021L,"数据为空"),
    WRONG_SEX(100020022L,"性别输入错误！"),
    WRONG_CHILDREN_INFO(100020023L,"儿童导入失败！"),
    WRONG_SCHOOL_GRADE(100020024L,"错误的学校级别！"),
    WRONG_CREDENTIAL_TYPE(100020025L,"证件类型输入错误！"),
    WRONG_EXIST_SCHOOL_STATUS(100020026L,"错误的在园状态！"),
    WRONG_CHILDREN_AUDIT_STATUS(100020027L,"错误的儿童审核状态！"),
    WRONG_CREDENTIAL_NUMBER(100020028L,"错误的证件号码！"),
    WRONG_NATION(100020029L,"错误的民族！"),
    CREDENTIAL_NUMBER_EXIST(100020030L,"该证件号码已存在！"),
    SAVE_QRCODE_FAIL(100020031L,"生成小程序二维码失败！"),
    EMPTY_ACCESS_TOKEN(100020032L,"获取AccessToken失败！"),
    LIST_CONTAIN_AUDITED_CHILDREN(100020033L,"列表中包含已经被审核的儿童！请重新选择！"),
    FILE_NO_FOUND(100020034L,"文件没有找到！"),
    WRONG_SURVEY_CODE(100020035L,"问卷码错误！"),
    EMPTY_NAME(100020036L,"姓名不能为空！"),
    EMPTY_NATIONALITY(100020037L,"国籍不能为空！"),
    EMPTY_CENSUS_REGISTER(100020038L,"户籍不能为空！"),
    EMPTY_GRADE(100020039L,"年级不能为空！"),
    EMPTY_CLASS_NAME(100020040L,"班级名称不能为空！"),
    EMPTY_CLASS_NICK_NAME(100020041L,"班级昵称不能为空！"),
    INVALID_SURVEY_CODE(100020042L,"问卷码无效!"),

    QUART_ADD_ERROR(100120001L,"定时任务添加异常！"),
    QUART_UPDATE_ERROR(100120002L,"定时任务修改异常"),

    ;


    private final long code;

    private final String defaultMessage;

    /**
     * 根据code获取去codeDefault
     * @param code
     * @return
     */
    public static CodeDefault getCodeDefaultByCode(Long code){
        for(CodeDefault codeDefault:CodeDefault.values()){
            if(code.equals(codeDefault.getCode())){
                return codeDefault;
            }
        }
        return  null;
    }

}