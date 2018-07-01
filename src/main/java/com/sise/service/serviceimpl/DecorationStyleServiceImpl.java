package com.sise.service.serviceimpl;

import com.sise.dao.DecorationStyleDao;
import com.sise.po.DecorationStyle;
import com.sise.service.DecorationStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 10:10 2018/2/22
 */
@Service
public class DecorationStyleServiceImpl implements DecorationStyleService {

    @Autowired
    private DecorationStyleDao decorationStyleDao;
    public List<DecorationStyle> searchAllDecorationStyle() {
        return decorationStyleDao.findAllDecorationStyle();
    }
}
