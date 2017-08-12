package com.tencent.aero.service;

import com.tencent.aero.model.Style;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author larryxing
 */
public interface StyleService {

    /**
     * 查询style
     * @return
     */
    List<Style> getAllStyles();

    /**
     * 通过name查询style
     * @param name
     * @return
     */
    Style getStyleByName(String name);

    /**
     * 通过id查询style
     * @param id
     * @return style或空（当id不存在的时候）
     */
    Style getStyleById(Long id);

    /**
     * 增加风格
     * @param name 风格名
     * @return 返回新创建的风格，若风格已存在返回null
     */
    Style addStyle(String name);

    /**
     * 通过id删除服务
     * @param id 若不存在则不删除
     */
    void deleteStyle(Long id);

    /**
     * 通过风格id更新风格
     * 会更新所有已绑定歌曲的风格
     * @param id 风格id
     * @param newName 风格的新名称
     * @return 若风格名存在或id不存在则返回null
     * @return 成功则返回新的风格对象
     */
    Style updateStyle(Long id, String newName);
}
