package com.task.resource;


import com.task.core.TaskService;
import com.task.core.model.Task;
import com.task.api.request.TaskDTO;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @UnitOfWork
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @RolesAllowed("ADMIN")
    @POST
    @UnitOfWork
    public Response addNewTask(TaskDTO taskDTO) {
        Task createdTask =  taskService.createTask(taskDTO);
        return Response.status(Response.Status.CREATED).entity(createdTask).build();
    }
}
