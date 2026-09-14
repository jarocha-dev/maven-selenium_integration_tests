package io.bootify.my_app.controller;

import io.bootify.my_app.model.BikeModelDTO;
import io.bootify.my_app.service.BikeModelService;
import io.bootify.my_app.util.ReferencedException;
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
@RequestMapping("/bikeModels")
public class BikeModelController {

    private final BikeModelService bikeModelService;

    public BikeModelController(final BikeModelService bikeModelService) {
        this.bikeModelService = bikeModelService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("bikeModels", bikeModelService.findAll());
        return "bikeModel/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("bikeModel") final BikeModelDTO bikeModelDTO) {
        return "bikeModel/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("bikeModel") @Valid final BikeModelDTO bikeModelDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bikeModel/add";
        }
        bikeModelService.create(bikeModelDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bikeModel.create.success"));
        return "redirect:/bikeModels";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("bikeModel", bikeModelService.get(id));
        return "bikeModel/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("bikeModel") @Valid final BikeModelDTO bikeModelDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bikeModel/edit";
        }
        bikeModelService.update(id, bikeModelDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("bikeModel.update.success"));
        return "redirect:/bikeModels";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        try {
            bikeModelService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("bikeModel.delete.success"));
        } catch (final ReferencedException referencedException) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, WebUtils.getMessage(
                    referencedException.getKey(), referencedException.getParams().toArray()));
        }
        return "redirect:/bikeModels";
    }

}
