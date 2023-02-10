package com.chen.mycardsystembackstage.entity;

import lombok.Data;

/**
 * Project : MyCardSystem - IndexMenu
 * Powered by GeorgeChen On 2023-02-09 22:22:02
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Data
public class IndexMenu {
    int id;
    String name;
    String link;
    String logo;
    int level;
    int parent;
}
