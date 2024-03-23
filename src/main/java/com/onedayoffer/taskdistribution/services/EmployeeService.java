package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.repositories.EmployeeRepository;
import com.onedayoffer.taskdistribution.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> getEmployees(@Nullable String sortDirection) {
        Sort sort = Sort.by("fio");
        if (sortDirection != null) {
            Sort.Direction dir = Sort.Direction.fromString(sortDirection);
            sort = Sort.by(dir, "fio");
        }
        Type listType = new TypeToken<List<EmployeeDTO>>() {}.getType();
        return modelMapper.map(employeeRepository.findAllAndSort(sort), listType);
    }

    @Transactional
    public EmployeeDTO getOneEmployee(Integer id) {
        throw new java.lang.UnsupportedOperationException("implement getOneEmployee");
    }

    public List<TaskDTO> getTasksByEmployeeId(Integer id) {
        throw new java.lang.UnsupportedOperationException("implement getTasksByEmployeeId");
    }

    @Transactional
    public void changeTaskStatus(Integer taskId, TaskStatus status) {
        throw new java.lang.UnsupportedOperationException("implement changeTaskStatus");
    }

    @Transactional
    public void postNewTask(Integer employeeId, TaskDTO newTask) {
        throw new java.lang.UnsupportedOperationException("implement postNewTask");
    }
}
