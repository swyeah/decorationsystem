package com.sise.service;

import com.sise.po.Contract;
import com.sise.po.ContractFile;
import com.sise.po.SearchContract;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:11 2018/2/23
 */
public interface ContractService {
    /*通过登记记录id查找合同资料*/
    public Contract findContractByRegistrationId( Integer registrationId);

    /*通过合同id查找所有合同图片*/
    public List<ContractFile> findContractFileByContractId(Integer contractId);

    /*插入合同*/
    public Integer saveContract(Contract contract);

    /*通过合同id查找状态*/
    public Integer findStatusById(Integer contractId);


    /*通过合同id删除合同*/
    public Integer dropContractById(Integer contractId);

    /*通过合同id更新状态*/
    public Integer changeStatusById(Integer contractId,Integer status);

    /*插入合同图片*/
    public Integer saveContractFile(List<ContractFile> contractFiles);

    /*获得版本号*/
    public Integer searchVersionById(Integer contractId);


    /*更新合同*/
    public Integer changeContract(Contract contract);

    /*删除合同文件*/
    public Integer dropAllContractFileByContractId(Integer contractId);

    public List<Contract> searchAllBySearch(SearchContract searchContract);
}
