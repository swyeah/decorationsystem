package com.sise.service.serviceimpl;

import com.sise.dao.HousepicDao;
import com.sise.po.Housepic;
import com.sise.po.HousepicFile;
import com.sise.po.SearchHousepic;
import com.sise.service.HousepicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:44 2018/2/20
 */
@Service
public class HousepicServiceImpl implements HousepicService {

    @Autowired
    private HousepicDao housepicDao;

    public Housepic findHousepicByRegistrationId(Integer registrationId) {
        return housepicDao.searchHousepicByRegistrationId(registrationId);
    }

    public List<HousepicFile> findHousepicFileByHousepicId(Integer housepicId) {
        return housepicDao.searchHousepicFileByHousepicId(housepicId);
    }

    public Integer addHousepic(Housepic housepic) {
        return housepicDao.insertHousepic(housepic);
    }

    public Integer findStatusById(Integer housepicId) {
        return housepicDao.searchStatusById(housepicId);
    }

    public Integer dripHousepicById(Integer housepicId) {
        return housepicDao.delHousepicById(housepicId);
    }

    public Integer changeStatusById(Integer housepicId, Integer status) {
        return housepicDao.updateStatusById(housepicId,status);
    }

    public Integer saveHousepicFile(List<HousepicFile> housepicFiles) {
        Integer cout = 0;
        for (HousepicFile h:housepicFiles){
            if (!h.getFileUrl().equals("")){
                Integer column = housepicDao.insertHousepicFile(h);
                cout= cout+column;
            }
        }
        return cout;
    }

    public Integer searchVersionById(Integer housepicId) {
        return housepicDao.getVersionById(housepicId);
    }

    public Integer changeHousepic(Housepic housepic) {
        return housepicDao.updateHousepic(housepic);
    }

    public Integer dropAllHousepicFileByHouseId(Integer housepicId) {
        return housepicDao.delAllHousepicFileByHousepicId(housepicId);
    }

    public List<Housepic> searchAllHousepic(SearchHousepic searchHousepic) {
        return housepicDao.findAllHousepic(searchHousepic);
    }

}
