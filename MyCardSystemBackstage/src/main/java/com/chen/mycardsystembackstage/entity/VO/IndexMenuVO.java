package com.chen.mycardsystembackstage.entity.VO;

import com.chen.mycardsystembackstage.entity.IndexMenu;
import lombok.Data;

import java.util.List;

/**
 * Project : MyCardSystem - IndexMenuVO
 * Powered by GeorgeChen On 2023-02-09 22:23:12
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class IndexMenuVO {
    String id;
    String name;
    String link;
    String logo;
    int level;
    String parent;
    List<IndexMenu> children;
}
