package com.sise.service.serviceimpl;

import com.sise.dao.ContractDao;
import com.sise.po.Contract;
import com.sise.po.ContractFile;
import com.sise.po.SearchContract;
import com.sise.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:14 2018/2/23
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;


    public Contract findContractByRegistrationId(Integer registrationId) {
        return contractDao.searchContractByRegistrationId(registrationId);
    }

    public List<ContractFile> findContractFileByContractId(Integer contractId) {
        return contractDao.searchContractFileByContractId(contractId);
    }

    public Integer saveContract(Contract contract) {
        return contractDao.insertContract(contract);
    }

    public Integer findStatusById(Integer contractId) {
        return contractDao.searchStatusById(contractId);
    }

    public Integer dropContractById(Integer contractId) {
        return contractDao.delContractById(contractId);
    }

    public Integer changeStatusById(Integer contractId, Integer status) {
        return contractDao.updateStatusById(contractId,status);
    }

    public Integer saveContractFile(List<ContractFile> contractFiles) {
        Integer cout = 0;
        for (ContractFile h:contractFiles){
            if (!h.getFileUrl().equals("")){
                Integer column = contractDao.insertContractFile(h);
                cout= cout+column;
            }
        }
        return cout;
    }

    public Integer searchVersionById(Integer contractId) {
        return contractDao.getVersionById(contractId);
    }

    public Integer changeContract(Contract contract) {
        return contractDao.updateContract(contract);
    }

    public Integer dropAllContractFileByContractId(Integer contractId) {
        return contractDao.delAllContractFileByContractId(contractId);
    }

    public List<Contract> searchAllBySearch(SearchContract searchContract) {
        return contractDao.findAllContractBySearch(searchContract);
    }
}
