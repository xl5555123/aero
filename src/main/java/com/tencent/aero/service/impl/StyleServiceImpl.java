package com.tencent.aero.service.impl;

import com.tencent.aero.model.Style;
import com.tencent.aero.repository.StyleRepository;
import com.tencent.aero.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * {@link StyleService}
 */
@Service
@Transactional
public class StyleServiceImpl implements StyleService {

    @Autowired
    private StyleRepository styleRepository;

    @Override
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }

    @Override
    public Style getStyleByName(String name) {
        return styleRepository.findByName(name);
    }

    @Override
    public Style getStyleById(Long id) {
        return styleRepository.findById(id);
    }

    @Override
    public Style addStyle(String name) {
        Style existStyle = styleRepository.findByName(name);
        if (existStyle != null) {
            return null;
        }
        Style newStyle = new Style(name);
        return styleRepository.save(newStyle);
    }

    @Override
    public void deleteStyle(Long id) {
        styleRepository.removeById(id);
    }

    @Override
    public Style updateStyle(Long id, String newName) {
        Style existStyle = styleRepository.findByName(newName);
        Style toUpdateStyle = styleRepository.findById(id);
        if (existStyle != null || toUpdateStyle == null) {
            return null;
        }
        toUpdateStyle.setName(newName);
        return styleRepository.save(toUpdateStyle);
    }
}