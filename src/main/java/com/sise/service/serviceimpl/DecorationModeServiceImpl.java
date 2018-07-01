package com.sise.service.serviceimpl;

import com.sise.dao.DecorationModeDao;
import com.sise.po.DecorationMode;
import com.sise.service.DecorationModeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:09 2018/2/23
 */
@Service
public class DecorationModeServiceImpl implements DecorationModeService {
    @Autowired
    private DecorationModeDao decorationModeDao;
    public List<DecorationMode> searchAllDecorationMode() {
        return decorationModeDao.findAllDecorationMode() ;
    }
}
