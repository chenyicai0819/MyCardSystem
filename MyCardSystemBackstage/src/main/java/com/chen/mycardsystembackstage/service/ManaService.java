package com.chen.mycardsystembackstage.service;

import com.chen.mycardsystembackstage.entity.ColumnName;
import com.chen.mycardsystembackstage.entity.Manages;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author George
 * @project MyCardSystem
 * @package com.chen.mycardsystembackstage.service
 * @date 2021/10/22 16:42
 * @since 1.0
 */
@Service
public interface ManaService {
    public List<Manages> getMana();
    public List<ColumnName> getHead(int head);
}
