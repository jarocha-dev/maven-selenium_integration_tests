package io.bootify.my_app.controller;

import io.bootify.my_app.model.UserBikeDTO;
import io.bootify.my_app.service.BikeModelService;
import io.bootify.my_app.service.UserBikeService;
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
@RequestMapping("/userBikes")
public class UserBikeController {

    private final UserBikeService userBikeService;
    private final BikeModelService bikeModelService;

    public UserBikeController(final UserBikeService userBikeService,
            final BikeModelService bikeModelService) {
        this.userBikeService = userBikeService;
        this.bikeModelService = bikeModelService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("bikeModelValues", bikeModelService.getBikeModelValues());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("userBikes", userBikeService.findAll());
        return "userBike/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("userBike") final UserBikeDTO userBikeDTO) {
        return "userBike/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("userBike") @Valid final UserBikeDTO userBikeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "userBike/add";
        }
        userBikeService.create(userBikeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("userBike.create.success"));
        return "redirect:/userBikes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("userBike", userBikeService.get(id));
        return "userBike/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("userBike") @Valid final UserBikeDTO userBikeDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "userBike/edit";
        }
        userBikeService.update(id, userBikeDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("userBike.update.success"));
        return "redirect:/userBikes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        try {
            userBikeService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("userBike.delete.success"));
        } catch (final ReferencedException referencedException) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, WebUtils.getMessage(
                    referencedException.getKey(), referencedException.getParams().toArray()));
        }
        return "redirect:/userBikes";
    }

}
