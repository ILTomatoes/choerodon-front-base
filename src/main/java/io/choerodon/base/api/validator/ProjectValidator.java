package io.choerodon.base.api.validator;

import io.choerodon.base.infra.dto.ProjectCategoryDTO;
import io.choerodon.base.infra.mapper.ProjectCategoryMapper;
import io.choerodon.core.exception.CommonException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectValidator {
    private ProjectCategoryMapper projectCategoryMapper;

    public ProjectValidator(ProjectCategoryMapper projectCategoryMapper) {
        this.projectCategoryMapper = projectCategoryMapper;
    }

    public ProjectCategoryDTO validateProjectCategory(String category) {
        if (ObjectUtils.isEmpty(category)) {
            throw new CommonException("error.project.category.empty");
        }
        ProjectCategoryDTO projectCategoryDTO = new ProjectCategoryDTO();
        projectCategoryDTO.setCode(category);
        projectCategoryDTO = projectCategoryMapper.selectOne(projectCategoryDTO);
        if (ObjectUtils.isEmpty(projectCategoryDTO)) {
            throw new CommonException("error.project.category.not.existed", category);
        }
        return projectCategoryDTO;
    }
    public void validateProjectCategoryCode(String code) {
        List<String> categoryCodes = new ArrayList<>();
        categoryCodes.add("AGILE");
        categoryCodes.add("GENERAL");
        if (!categoryCodes.contains(code)) {
            throw new CommonException("error.params.invalid");
        }

    }
}
