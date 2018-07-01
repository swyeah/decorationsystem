package com.sise.dao;

import com.sise.po.Contract;
import com.sise.po.ContractFile;
import com.sise.po.SearchContract;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 20:59 2018/2/23
 */
@Repository
public interface ContractDao {
    /*通过登记记录id查找合同资料*/
    public Contract searchContractByRegistrationId(@Param("registrationId") Integer registrationId);

    /*通过合同id查找所有合同图片*/
    public List<ContractFile> searchContractFileByContractId(@Param("contractId") Integer contractId);

    /*插入合同*/
    public Integer insertContract(Contract contract);

    /*通过合同id查找状态*/
    public Integer searchStatusById(@Param("contractId") Integer contractId);


    /*通过合同id删除合同*/
    public Integer delContractById(@Param("contractId") Integer contractId);

    /*通过合同id更新状态*/
    public Integer updateStatusById(@Param("contractId") Integer contractId,@Param("status") Integer status);

    /*插入合同图片*/
    public Integer insertContractFile(ContractFile contractFile);

    /*获得版本号*/
    public Integer getVersionById(@Param("contractId") Integer contractId);


    /*更新合同*/
    public Integer updateContract(Contract contract);

    /*删除合同文件*/
    public Integer delAllContractFileByContractId(@Param("contractId") Integer contractId);

    /*根据搜索内容查找合同*/
    public List<Contract> findAllContractBySearch(SearchContract searchContract);
}
