package stusyo222b.webappsspringproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import stusyo222b.webappsspringproject.entities.FFamilyState;
import stusyo222b.webappsspringproject.entities.OfficeWorker;
import stusyo222b.webappsspringproject.service.FFamilyStateService;
import stusyo222b.webappsspringproject.service.OfficeWorkerService;

@Controller
public class FFamilyStateController {

    private final OfficeWorkerService officeWorkerService;
    private final FFamilyStateService ffamilyStateService;

    public FFamilyStateController(OfficeWorkerService officeWorkerService, FFamilyStateService ffamilyStateService) {
        this.officeWorkerService = officeWorkerService;
        this.ffamilyStateService = ffamilyStateService;
    }

    //-------------------   For work with Family State   --------------------------

    // handler method to handle Info button click
    @GetMapping("/officeworkers/{idOfficeWorker}/ffamilyState")
    public String infoOfficeWorkerForm(@PathVariable Long idOfficeWorker, Model model) {
        OfficeWorker worker = officeWorkerService.getOfficeWorkerById(idOfficeWorker);
        FFamilyState ffamilyState = ffamilyStateService.getFFamilyStateByIdOfficeWorker(idOfficeWorker);
        if (ffamilyState == null) {
            ffamilyState = new FFamilyState(null, worker, null, 0, 0, false); // Initialize with default values
        }
        model.addAttribute("ffamilyState", ffamilyState);
        model.addAttribute("worker", worker);
        model.addAttribute("editmode", false);
        return "/familystates/ffamilyState_officeworker";
    }

    // handler method to handle Update and Add Family State button click
    @GetMapping("/officeworkers/{idOfficeWorker}/ffamilyState/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editFFamilyStateMode(@PathVariable Long idOfficeWorker, Model model) {
        OfficeWorker worker = officeWorkerService.getOfficeWorkerById(idOfficeWorker);
        FFamilyState ffamilyState = ffamilyStateService.getFFamilyStateByIdOfficeWorker(idOfficeWorker);
        if (ffamilyState == null) {
            ffamilyState = new FFamilyState(null, worker, null, 0, 0, false); // Initialize with default values
        }
        model.addAttribute("ffamilyState", ffamilyState);
        model.addAttribute("worker", worker);
        model.addAttribute("editmode", true);
        return "/familystates/ffamilyState_officeworker";
    }

    // handler method to handle Save button click
    @PostMapping("/officeworkers/{idOfficeWorker}/ffamilyState/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveEditFFamilyState(@PathVariable Long idOfficeWorker,
                                       @ModelAttribute("ffamilyState") FFamilyState ffamilyState,
                                       Model model) {
        OfficeWorker worker = officeWorkerService.getOfficeWorkerById(idOfficeWorker);
        if (worker != null) {
            ffamilyState.setWorker(worker);
            // Check if FamilyState exists for this OfficeWorker
            FFamilyState existingFamilyState = ffamilyStateService.getFFamilyStateByIdOfficeWorker(idOfficeWorker);
            if (existingFamilyState != null) {
                // Update existing FamilyState
                ffamilyState.setId(existingFamilyState.getId());
                ffamilyStateService.updateFFamilyState(idOfficeWorker, ffamilyState);
                model.addAttribute("message", "Family state successfully updated.");
            } else {
                // Insert new FamilyState
                ffamilyStateService.insertFFamilyState(ffamilyState);
                model.addAttribute("message", "Family state successfully added.");
            }
        } else {
            String messageUpdateError = "OfficeWorker with id=" + idOfficeWorker + " not found.";
            model.addAttribute("error_crud_message", messageUpdateError);
            model.addAttribute("ret_page", "/officeworkers");
            return "crud_error";
        }
        return "redirect:/officeworkers/{idOfficeWorker}/ffamilyState";
    }

    // handler method to handle delete Family State request
    @PostMapping("/officeworkers/{idOfficeWorker}/ffamilyState/del")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFFamilyState(@PathVariable Long idOfficeWorker, Model model) {
        FFamilyState ffamilyStateToDelete = ffamilyStateService.getFFamilyStateByIdOfficeWorker(idOfficeWorker);
        if (ffamilyStateToDelete != null) {
            ffamilyStateService.deleteFFamilyStateById(ffamilyStateToDelete.getId());
            return "redirect:/officeworkers/{idOfficeWorker}/ffamilyState";
        } else {
            String messageDeleteError = "Information about Family State absent in DB! Object: Family State, id=" + idOfficeWorker;
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "redirect:/officeworkers");
            return "crud_error";
        }
    }
}
