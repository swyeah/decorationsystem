package com.sise.service;

import com.sise.dao.ProjectTimeDao;
import com.sise.po.ProgressContent;
import com.sise.po.ProjectTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 1:16 2018/3/4
 */
public interface ProjectTimeService {

    /*插入一个天数*/
    public Integer saveProjectTime(ProjectTime projectTime);

    /*根据条件查询天数*/
    public List<ProjectTime> searchProjectTimeBySearch(Map<String,Object> map);

    /*删除一个天数*/
    public Integer dropProjectTime(@Param("projectId") Integer projectId);

    /*更新一个天数*/
    public Integer changeProjectTime(ProjectTime projectTime);

    /*插入一个天数内容*/
    public Integer saveProgressContent(ProgressContent progressContent);

    /*删除一个天数内容*/
    public Integer dropProgressContentByProgressContentId(Integer progressId);

    /*删除一个天数的全部内容*/
    public Integer dropProgressContentByProjectId(Integer projectId);

    /*修改一个天数的内容*/
    public Integer changeProgressContent(ProgressContent progressContent);

    /*查找一个天数的全部内容*/
    public List<ProgressContent> searchProgressContentByProjectId(Integer projectId);


    public ProjectTime searchProjectTimeByProjectDay(ProjectTime projectTime);

    public Integer getProgressContentVersionByProgressId(Integer progressId);

    /*通过天数内容id获取内容*/
    public ProgressContent searchProgressContentByProgressId(Integer progressId);

    /*通过天数id获取全部内容title*/
    public List<ProgressContent> searchAllProgressContentTitleByProjectId( Integer projectId);

    public ProjectTime searchProjectTimeByProjectId(Integer projectId);

}
