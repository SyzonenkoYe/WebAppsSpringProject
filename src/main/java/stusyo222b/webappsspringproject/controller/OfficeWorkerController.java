package stusyo222b.webappsspringproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import stusyo222b.webappsspringproject.entities.OfficeWorker;
import stusyo222b.webappsspringproject.service.OfficeWorkerService;

import java.util.List;

@Controller
public class OfficeWorkerController {

    private final String WORKER_TEXT_INS = "NEW OFFICE WORKER (WJ2024)";
    private final String WORKER_TEXT_EDIT = "EDIT OFFICE WORKER (WJ2024)";

    private final OfficeWorkerService officeWorkerService;

    public OfficeWorkerController(OfficeWorkerService officeWorkerService) {
        this.officeWorkerService = officeWorkerService;
    }


@GetMapping("/officeworkers")
    public String showOfficeWorkers(Model model){
    List<OfficeWorker> myList = officeWorkerService.getAllOfficeWorkers();
    myList.stream().forEach(System.out::println);
    model.addAttribute("officeworkers", myList);
    return "officeworkers/officeworkers";
}

    @PostMapping("/s")
    public String saveOfficeWorker(@ModelAttribute("officeworker") OfficeWorker officeworker) {
        return "redirect:/officeworkers";
    }


    @GetMapping("/officeworkers/new")
    public String createOfficeWorkerForm(Model model) {
        System.out.println("Go to insert new office worker");
        OfficeWorker newOfficeWorker = new OfficeWorker("");
        model.addAttribute("officeworker", newOfficeWorker);
        model.addAttribute("titleOfficeWorker", WORKER_TEXT_INS);
        model.addAttribute("errorString", null);
        return "officeworkers/officeworker";
    }


    @GetMapping("/officeworkers/edit/{idEdit}")
    public String editOfficeWorkerForm(@PathVariable Long idEdit, Model model) {
        System.out.println("Go to edit office worker with id=" + idEdit);
        OfficeWorker workerForUpdateInDB = officeWorkerService.getOfficeWorkerById(idEdit);
        model.addAttribute("officeworker", workerForUpdateInDB);
        model.addAttribute("titleOfficeWorker", WORKER_TEXT_EDIT);
        model.addAttribute("errorString", null);
        return "officeworkers/officeworker";
    }

    @PostMapping("/officeworkers/save")
    public String saveNewOfficeWorker(@ModelAttribute("officeworker") OfficeWorker workerToSave,
                                      Model model) {
        System.out.println("/officeworkers/save");
        System.out.println("" + workerToSave);
        OfficeWorker workerToSaveInDB = officeWorkerService.getOfficeWorkerByKeySet(workerToSave.getName());
        if (workerToSaveInDB == null) {
            officeWorkerService.saveOfficeWorker(workerToSave);
            //== forward() in ServletAPI
            return "redirect:/officeworkers";
        } else {
            model.addAttribute("officeworker", workerToSave);
            model.addAttribute("titleOfficeWorker", WORKER_TEXT_INS);
            model.addAttribute("errorString", "Office worker with such key (name) was found in DB!");
            return "/officeworkers/officeworker";
        }
    }

    @PostMapping("/officeworkers/save/{id}")
    public String saveUpdateOfficeWorker(@PathVariable Long id,
                                         @ModelAttribute("officeworker") OfficeWorker workerToSave,
                                         Model model) {
        System.out.println("/officeworkers/save/" + id);
        System.out.println("" + id + " " + workerToSave);
        OfficeWorker existingWorkerWithSameCod = officeWorkerService.getOfficeWorkerByWorkerCod(workerToSave.getWorkerCod());

        if (existingWorkerWithSameCod != null && !existingWorkerWithSameCod.getId().equals(workerToSave.getId())) {
            model.addAttribute("officeworker", workerToSave);
            model.addAttribute("titleOfficeWorker", WORKER_TEXT_EDIT);
            model.addAttribute("errorString", "Office Worker with such workerCod already exists in DB!");
            return "/officeworkers/officeworker"; // Перезавантажуємо сторінку редагування
        }

        // Отримуємо співробітника з бази даних для оновлення
        OfficeWorker existingOfficeWorker = officeWorkerService.getOfficeWorkerById(id);

        if (existingOfficeWorker == null) {
            // Якщо співробітник не знайдений, додаємо помилку
            model.addAttribute("officeworker", workerToSave);
            model.addAttribute("titleOfficeWorker", WORKER_TEXT_EDIT);
            model.addAttribute("errorString", "Office Worker for update was not found in DB!");
            return "/officeworkers/officeworker";
        } else {
            // Оновлюємо співробітника в базі
            officeWorkerService.updateOfficeWorker(existingOfficeWorker.getId(), workerToSave);
            return "redirect:/officeworkers"; // Перехід на список співробітників після успішного оновлення
        }
    }

    @GetMapping("/officeworkers/del/{idOfficeWorkerForDelete}")
    public String deleteOfficeWorker(@PathVariable Long idOfficeWorkerForDelete, Model model) {
        System.out.println("/officeworkers/del/{id}");
        System.out.println("" + idOfficeWorkerForDelete + " will been deleted");
        // Code for delete
        String messageDeleteError = "";
        //If id at moment of deleting was absence in DB?
        //Make check to include mistake?
        OfficeWorker delOfficeWorkerInDB = officeWorkerService.getOfficeWorkerById(idOfficeWorkerForDelete);
        if (delOfficeWorkerInDB != null) {
            officeWorkerService.deleteOfficeWorkerById(idOfficeWorkerForDelete);
        } else {
            messageDeleteError = "Object: OFFICEWORKER, id=" + idOfficeWorkerForDelete
                    + "<br><br>No such office worker in DB!";
        }
        if (!messageDeleteError.isEmpty()) {
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "/officeworkers");
            return "crud_error";
        } else {
            return "redirect:/officeworkers";
        }
    }


}
