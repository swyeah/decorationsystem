package com.sise.service.serviceimpl;

import com.sise.dao.ProjectTimeDao;
import com.sise.po.ProgressContent;
import com.sise.po.ProjectTime;
import com.sise.service.ProjectTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 1:19 2018/3/4
 */
@Service
public class ProjectTimeServiceImpl implements ProjectTimeService {
    @Autowired
    private ProjectTimeDao projectTimeDao;
    public Integer saveProjectTime(ProjectTime projectTime) {
        return projectTimeDao.insertProjectTime(projectTime);
    }

    public List<ProjectTime> searchProjectTimeBySearch(Map<String, Object> map) {
        return projectTimeDao.findProjectTimeBySearch(map);
    }

    public Integer dropProjectTime(Integer projectId) {
        return projectTimeDao.delProjectTime(projectId);
    }

    public Integer changeProjectTime(ProjectTime projectTime) {
        return projectTimeDao.updateProjectTime(projectTime);
    }

    public Integer saveProgressContent(ProgressContent progressContent) {
        return projectTimeDao.insertProgressContent(progressContent);
    }

    public Integer dropProgressContentByProgressContentId(Integer progressId) {
        return projectTimeDao.delProgressContentByProgressContentId(progressId);
    }

    public Integer dropProgressContentByProjectId(Integer projectId) {
        return projectTimeDao.delProgressContentByProjectId(projectId);
    }

    public Integer changeProgressContent(ProgressContent progressContent) {
        return projectTimeDao.updateProgressContent(progressContent);
    }

    public List<ProgressContent> searchProgressContentByProjectId(Integer projectId) {
        return projectTimeDao.findProgressContentByProjectId(projectId);
    }

    public ProjectTime searchProjectTimeByProjectDay(ProjectTime projectTime) {
        return projectTimeDao.findProjectTimeByProjectDay(projectTime);
    }

    public Integer getProgressContentVersionByProgressId(Integer progressId) {
        return projectTimeDao.getProgressContentVersionByProgressId(progressId);
    }

    public ProgressContent searchProgressContentByProgressId(Integer progressId) {
        return projectTimeDao.findProgressContentByProgressId(progressId);
    }

    public List<ProgressContent> searchAllProgressContentTitleByProjectId(Integer projectId) {
        return projectTimeDao.findAllProgressContentTitleByProjectId(projectId);
    }

    public ProjectTime searchProjectTimeByProjectId(Integer projectId) {
        return projectTimeDao.findProjectTimeByProjectId(projectId);
    }
}
