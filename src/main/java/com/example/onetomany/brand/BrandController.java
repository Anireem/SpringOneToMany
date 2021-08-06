package com.example.onetomany.brand;

import com.example.onetomany.category.Category;
import com.example.onetomany.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("brands/new")
    public String showCreateNewBrandForm(Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("brand", new Brand());
        model.addAttribute("listCategories", listCategories);
        return "brand_form";
    }

    @PostMapping("brands/save")
    public String saveBrand(Brand brand) {
        brandRepository.save(brand);
        return "redirect:/brands";
    }

    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<Brand> listBrands = brandRepository.findAll();
        model.addAttribute("listBrands", listBrands);
        return "brands";
    }

    @GetMapping("/brands/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Integer id, Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        Brand brand = brandRepository.findById(id).get();

        model.addAttribute("brand", brand);
        model.addAttribute("listCategories", listCategories);

        return "brand_form";
    }

}
