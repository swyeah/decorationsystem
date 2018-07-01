package com.sise.service.serviceimpl;

import com.sise.dao.HouseTypeDao;
import com.sise.po.HouseType;
import com.sise.service.HouseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 23:41 2018/2/20
 */
@Service
public class HouseTypeServiceImpl implements HouseTypeService{
    @Autowired
    private HouseTypeDao houseTypeDao;

    public List<HouseType> findAllHousetype() {
        return houseTypeDao.searchAllHouseType();
    }
}
