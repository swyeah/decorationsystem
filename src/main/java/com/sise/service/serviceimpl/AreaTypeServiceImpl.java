package com.sise.service.serviceimpl;

import com.sise.dao.AreaTypeDao;
import com.sise.po.AreaType;
import com.sise.service.AreaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:40 2018/2/20
 */
@Service
public class AreaTypeServiceImpl implements AreaTypeService {
    @Autowired
    private AreaTypeDao areaTypeDao;
    public List<AreaType> findAllAreaType() {
        return areaTypeDao.searchAllAreaType();
    }
}
