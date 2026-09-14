package io.bootify.my_app.controller;

import io.bootify.my_app.model.MaintenanceLogDTO;
import io.bootify.my_app.service.MaintenanceLogService;
import io.bootify.my_app.service.UserBikeService;
import io.bootify.my_app.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/maintenanceLogs")
public class MaintenanceLogController {

    private final MaintenanceLogService maintenanceLogService;
    private final UserBikeService userBikeService;

    public MaintenanceLogController(final MaintenanceLogService maintenanceLogService,
            final UserBikeService userBikeService) {
        this.maintenanceLogService = maintenanceLogService;
        this.userBikeService = userBikeService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("userBikeValues", userBikeService.getUserBikeValues());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("maintenanceLogs", maintenanceLogService.findAll());
        return "maintenanceLog/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("maintenanceLog") final MaintenanceLogDTO maintenanceLogDTO) {
        return "maintenanceLog/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("maintenanceLog") @Valid final MaintenanceLogDTO maintenanceLogDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "maintenanceLog/add";
        }
        maintenanceLogService.create(maintenanceLogDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("maintenanceLog.create.success"));
        return "redirect:/maintenanceLogs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("maintenanceLog", maintenanceLogService.get(id));
        return "maintenanceLog/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("maintenanceLog") @Valid final MaintenanceLogDTO maintenanceLogDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "maintenanceLog/edit";
        }
        maintenanceLogService.update(id, maintenanceLogDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("maintenanceLog.update.success"));
        return "redirect:/maintenanceLogs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        maintenanceLogService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("maintenanceLog.delete.success"));
        return "redirect:/maintenanceLogs";
    }

}
