package com.tencent.aero.controller;

import com.tencent.aero.controller.exception.NoPermissionException;
import com.tencent.aero.controller.exception.ResourceNotFoundException;
import com.tencent.aero.model.Style;
import com.tencent.aero.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/styles")
public class StyleController {

    public final static String NO_PERMISSION_FORMAT = "This style %s is exist";

    @Autowired
    private StyleService styleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Style> getAll() {
        return styleService.getAllStyles();
    }

    @RequestMapping(value = "/{styleId}", method = RequestMethod.GET)
    public Style getOne(@PathVariable Long styleId) {
        Style style = styleService.getStyleById(styleId);
        if (style == null) {
            throw new ResourceNotFoundException();
        }
        return style;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Style addStyle(@RequestBody(required = true) String style) {
        Style newStyle = styleService.addStyle(style);
        if (newStyle == null) {
            throw new NoPermissionException(String.format(NO_PERMISSION_FORMAT, style));
        }
        return newStyle;
    }

    @RequestMapping(value = "/{styleId}", method = RequestMethod.PUT)
    public Style updateStyle(@RequestBody  String newName, @PathVariable Long styleId) {
        Style toChangeStyle = styleService.getStyleById(styleId);
        if (toChangeStyle == null) {
            throw new ResourceNotFoundException();
        }
        Style existStyle = styleService.getStyleByName(newName);
        if (existStyle != null) {
            throw new NoPermissionException(String.format(NO_PERMISSION_FORMAT, newName));
        }
        return styleService.updateStyle(styleId, newName);
    }

    @RequestMapping(value = "/{styleId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStyle(@PathVariable Long styleId) {
        styleService.deleteStyle(styleId);
    }



}
