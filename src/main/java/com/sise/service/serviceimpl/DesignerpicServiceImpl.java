package com.sise.service.serviceimpl;

import com.sise.dao.DesignerpicDao;
import com.sise.po.Designerpic;
import com.sise.po.DesignerpicFile;
import com.sise.po.SearchDesignerpic;
import com.sise.service.DesignerpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:23 2018/2/22
 */
@Service
public class DesignerpicServiceImpl implements DesignerpicService {

    @Autowired
    private DesignerpicDao designerpicDao;

    public Designerpic searchDesignerpicByRegistrationId(Integer registrationId) {
        return designerpicDao.findDesignerpicByRegistrationId(registrationId);
    }

    public Integer saveDesigner(Designerpic designerpic) {
        return designerpicDao.insertDesigner(designerpic);
    }


    public Integer saveDesignerpicFile(List<DesignerpicFile> designerpicFiles) {
        Integer count = 0;
        for (DesignerpicFile df:designerpicFiles){
            Integer column = designerpicDao.insertDesignerpicFile(df);
            count = count+column;
        }
        return count;
    }

    public List<DesignerpicFile> searchAllDesignerpicFileByDesignerId(Integer designerpicId) {
        return designerpicDao.findAllDesignerpicFileByDesignerId(designerpicId);
    }

    public Integer changeDescribeById(Map<String, Object> map) {
        return designerpicDao.updateDescribeById(map);
    }

    public Integer dropDesignerpicByDesignerpicId(Integer designerpicId) {
        return designerpicDao.delDesignerpicByDesignerpicId(designerpicId);
    }

    public Integer dropDesignerpicFileByDesignerpicId(Integer designerpicId) {
        return designerpicDao.delDesignerpicFileByDesignerpicId(designerpicId);
    }

    public Integer changeDesignerpicByDesignerpicId(Designerpic designerpic) {
        return designerpicDao.updateDesignerpicByDesignerpicId(designerpic);
    }

    public Integer searchVersionByDesignerpicId(Integer designerpicId) {
        return designerpicDao.getVersionByDesignerpicId(designerpicId);
    }

    public List<Designerpic> searchAllDesignerpicBySearch(SearchDesignerpic searchDesignerpic) {
        return designerpicDao.findAllDesignerpic(searchDesignerpic);
    }

    public Integer changeStatusByDesignerpicId(Integer designerpicId, Integer status) {
        return designerpicDao.updateStatusByDesignerpicId(designerpicId,status);
    }
}
