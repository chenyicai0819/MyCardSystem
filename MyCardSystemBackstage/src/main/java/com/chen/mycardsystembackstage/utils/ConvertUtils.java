package com.chen.mycardsystembackstage.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对象|集合转换工具类
 */
public class ConvertUtils {

    /**
     * 相同属性对象集合复制
     *
     * @param sourceList 复制对象集合
     * @param target     输出集合类型
     * @param <T>        指定输出泛型
     * @return
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> target) {
        List<T> list = new ArrayList<T>();
        try {
            for (Object bean : sourceList) {
                T t = target.newInstance();
                BeanUtils.copyProperties(bean, t);
                list.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * //字母转数字,并且在字母后面添加英文逗号  A-Z ：1-26
     * @param letter A-Z
     * @return 1-26
     */
    public static String letterToNumber(String letter) {
        String reg = "[a-zA-Z]";
        StringBuffer strBuf = new StringBuffer();
        letter = letter.toLowerCase();
        if (StringUtils.isNotBlank(letter)) {
            for (char c : letter.toCharArray()) {
                if (String.valueOf(c).matches(reg)) {
                    strBuf.append(c - 96).append(",");
                } else {
                    strBuf.append(c);
                }
            }
            return strBuf.toString();
        } else {
            return letter;
        }
    }

    /**
     * 将单个集合切割为多个集合
     *
     * @param list      传入的list集合
     * @param splitSize 表示每splitSize个对象分割成一组
     * @return {@link List}<{@link List}<{@link T}>>
     */
    public static <T> List<List<T>> splitList(List<T> list,int splitSize){
        //判断集合是否为空
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        //计算分割后的大小
        int maxSize = (list.size() + splitSize - 1) / splitSize;
        //开始分割
        return Stream.iterate(0, n -> n + 1)
                .limit(maxSize)
                .parallel()
                .map(a -> list.parallelStream().skip((long) a * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }

}
