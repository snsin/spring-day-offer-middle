package com.onedayoffer.taskdistribution.services;

import com.onedayoffer.taskdistribution.DTO.EmployeeDTO;
import com.onedayoffer.taskdistribution.DTO.TaskDTO;
import com.onedayoffer.taskdistribution.DTO.TaskStatus;
import com.onedayoffer.taskdistribution.repositories.EmployeeRepository;
import com.onedayoffer.taskdistribution.repositories.TaskRepository;
import com.onedayoffer.taskdistribution.repositories.entities.Employee;
import com.onedayoffer.taskdistribution.repositories.entities.Task;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    public static final Type taskListType = new TypeToken<List<TaskDTO>>() {
    }.getType();
    public static final Type employeeListType = new TypeToken<List<EmployeeDTO>>() {
    }.getType();
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> getEmployees(@Nullable String sortDirection) {
        Sort sort = Sort.by("fio");
        if (sortDirection != null) {
            Sort.Direction dir = Sort.Direction.fromString(sortDirection);
            sort = Sort.by(dir, "fio");
        }
        return modelMapper.map(employeeRepository.findAllAndSort(sort), employeeListType);
    }

    @Transactional
    public EmployeeDTO getOneEmployee(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("user id not specified");
        }
        Optional<Employee> employeeById = employeeRepository.findEmployeeById(id);
        return employeeById.map(e -> modelMapper
                        .map(e, EmployeeDTO.class))
                .orElseThrow(() -> new NoSuchElementException("employee with id = " + id + "not found"));
    }

    public List<TaskDTO> getTasksByEmployeeId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("employee id not specified");
        }
        List<Task> tasks = taskRepository.findAllByEmployeeId(id);
        return modelMapper.map(tasks, taskListType);
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
