package com.sise.dao;

import com.sise.po.ProgressContent;
import com.sise.po.ProjectTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:26 2018/3/3
 */
@Repository
public interface ProjectTimeDao {
    /*插入一个天数*/
    public Integer insertProjectTime(ProjectTime projectTime);

    /*根据条件查询天数*/
    public List<ProjectTime> findProjectTimeBySearch(Map<String,Object> map);

    /*删除一个天数*/
    public Integer delProjectTime(@Param("projectId") Integer projectId);

    /*更新一个天数*/
    public Integer updateProjectTime(ProjectTime projectTime);

    /*插入一个天数内容*/
    public Integer insertProgressContent(ProgressContent progressContent);

    /*删除一个天数内容*/
    public Integer delProgressContentByProgressContentId(@Param("progressId") Integer progressId);

    /*删除一个天数的全部内容*/
    public Integer delProgressContentByProjectId(@Param("projectId") Integer projectId);

    /*修改一个天数的内容*/
    public Integer updateProgressContent(ProgressContent progressContent);

    /*查找一个天数的全部内容*/
    public List<ProgressContent> findProgressContentByProjectId(@Param("projectId") Integer projectId);


    /*通过天数日期查找天数*/
    public ProjectTime findProjectTimeByProjectDay(ProjectTime projectTime);

    /*得到天数内容版本号*/
    public Integer getProgressContentVersionByProgressId(@Param("progressId") Integer progressId);

    /*通过天数内容id获取内容*/
    public ProgressContent findProgressContentByProgressId(@Param("progressId") Integer progressId);

    /*通过天数id获取全部内容title*/
    public List<ProgressContent> findAllProgressContentTitleByProjectId(@Param("projectId") Integer projectId);

    /*通过天数id获取天数*/
    public ProjectTime findProjectTimeByProjectId(@Param("projectId")Integer projectId);


}
