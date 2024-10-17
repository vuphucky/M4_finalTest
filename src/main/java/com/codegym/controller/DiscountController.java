package com.codegym.controller;

import com.codegym.model.Discount;
import com.codegym.service.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/discounts")
public class DiscountController {
    @Autowired
    private IDiscountService discountService;

    @GetMapping
    public ModelAndView listDiscounts(){
        ModelAndView modelAndView = new ModelAndView("/discount/list");
        modelAndView.addObject("discounts", discountService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/discount/create");
        modelAndView.addObject("discount", new Discount());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveDiscount(@ModelAttribute("discount") Discount discount){
         discountService.save(discount);
         ModelAndView modelAndView = new ModelAndView("/discount/create");
         modelAndView.addObject("discount", new Discount());
         modelAndView.addObject("message","Create new discount successfully!");
         return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Discount> discount = discountService.findById(id)  ;
        if (discount.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/discount/update");
            modelAndView.addObject("discount", discount.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update")
    public ModelAndView updateDiscount(@ModelAttribute("discount") Discount discount){
        discountService.save(discount);
        ModelAndView modelAndView = new ModelAndView("/discount/update");
        modelAndView.addObject("discount", discount);
        modelAndView.addObject("message", "Discount update successfully!");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        discountService.remove(id);
        return "redirect:/discounts";
    }

}
