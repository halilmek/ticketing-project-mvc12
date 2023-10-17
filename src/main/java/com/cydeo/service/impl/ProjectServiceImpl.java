package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String>
    implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO save(ProjectDTO obj) {

        //obj.setProjectStatus(Status.OPEN); => Bunu böyle
//yapamayiz !!! Yaparsak tüm projelerin status lari open olur.
        if (obj.getProjectStatus() == null) {

            obj.setProjectStatus(Status.OPEN);
        }

        return super.save(obj.getProjectCode(), obj);
    }

    @Override
    public List<ProjectDTO> findAll() {

        return super.findAll();
    }

    @Override
    public ProjectDTO findById(String id) {

        return super.findById(id);
    }

    @Override
    public void deleteById(String id) {

        super.deleteById(id);
    }

    @Override
    public void update(ProjectDTO obj) {

//Update ettigimz project in status unu Spring catch edemiyor ve null assign ediyor.
//Biz burda ona update edilecek projenin aslinda status u oldugunu ve onu kullanmasi gerektigini söyleyecegiz.
// Burada ayrica save() method u ile arasindaki farki da görüyoruz. Ikisi de HashMap i
//override edebilir. Ancak save method ile zaten status u olmayan bir project icin
//status assign ederken burada status u olan bir projeye var olan status unu pass
//ediyoruz.

        ProjectDTO updatedProject = super.findById(obj.getProjectCode());

        if (obj.getProjectStatus() == null) {

            obj.setProjectStatus(updatedProject.getProjectStatus());
        }

        super.update(obj.getProjectCode(), obj);
    }

    @Override
    public void complete(ProjectDTO projectDTO) {

        projectDTO.setProjectStatus(Status.COMPLETE);

//Yukaridaki sekilde de sorunsuz calismasina ragmen isi garantiye almak icin
//status u change edilen project i tekrar map de save() ediyoruz.

        super.save(projectDTO.getProjectCode(), projectDTO);
        /*
    T save (ID id, T obj){

        mapOfUser.put(id, obj); => Vizyonsuzluk nedeniyle user yaptik, ama bu abstract
class i extends eden her class kendi map ini create ediyor olacak. Roller icin,
Project ler icin, ve user lar icin birer HashMap create ediliyor.
        return obj;
    }
==> Burada HashMap e eleman ekliyoruz. Eger ayni key e sahip baska bir element
put() ile eklersek eski degeri override ediyoruz. => Ör =>
mapOfUser.put(1, "Apple")
mapOfUser.put(1, "Dragon Fruit")
HashMap deki yeni deger Dragon Fruit olacak. Cünkü HashMap duplication a izin vermiyor.
         */
    }

    @Override
    public List<ProjectDTO> allProjectBasedOnManager(UserDTO manager) {

        List<ProjectDTO> projectList =
                findAll()
                        .stream()
                        .filter(project -> project.getAssignedManager().equals(manager))
                        .map(project -> {

                            List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                            int completeTaskCounts = (int) taskList.stream().filter(t -> t.getProjectDTO().equals(project) && t.getTaskStatus() == Status.COMPLETE).count();

                            int unfinishedTaskCounts = (int) taskList.stream().filter(t -> t.getProjectDTO().equals(project) && t.getTaskStatus() != Status.COMPLETE).count();

                            project.setCompletedTaskCount(completeTaskCounts);
                            project.setUnfinishedTaskCount(unfinishedTaskCounts);

                            return project;

                        }).collect(Collectors.toList());


        return projectList;


        /*
        List<ProjectDTO> projectDTOList = findAll().stream()
                .filter(eachProject -> eachProject.getAssignedManager().equals(manager))
//Önce belirlenen manager a göre tüm projelerden ilgili olanlari aldim. Sonrasinda da
    //bu projelerin icindeki tasklara ve onlarin status larina bakacagim. Ve ayni projeler
    //icinde bitmemis ve tamamlanmis tasklarin sayilarini hesaplayacagim.
                .map(eachProjectBelongsToManager -> {
                    List<TaskDTO> taskListForEachProject = taskService.findTasksByManager(manager);

                    int completedTasksInSameProject = (int) taskListForEachProject.stream()
                            .filter(eachTask -> eachTask.getProjectDTO().equals(eachProjectBelongsToManager) &&
                                    eachTask.getTaskStatus() == Status.COMPLETE)
                            .count();
    //count() method long return ettigi icin (int) ile sonucu downcasting yaptik.
                    int nonCompletedeTasksInSameProject = (int) taskListForEachProject.stream()
                            .count() - completedTasksInSameProject;
                    int nonCompletedeTasksInSameProject2 = (int) taskListForEachProject.stream()
                                    .filter(eachTask -> eachTask.getProjectDTO().equals(eachProjectBelongsToManager) &&
                                            eachTask.getTaskStatus() != Status.COMPLETE)
                            .count();
                    eachProjectBelongsToManager.setCompletedTaskCount(completedTasksInSameProject);
                    eachProjectBelongsToManager.setUnfinishedTaskCount(nonCompletedeTasksInSameProject2);

                    return eachProjectBelongsToManager;
                })
                .collect(Collectors.toList());

        return projectDTOList;

         */
    }
}
