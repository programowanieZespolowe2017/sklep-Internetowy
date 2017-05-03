package com.smcebi.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 4/30/2017 5:54 PM
 */
@Controller
public class ProductsController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductsController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activeCategory", "");
        return "products";
    }

    @RequestMapping("/products/c/{category}")
    public String products(Model model, @PathVariable("category") String category) {
        model.addAttribute("products", productRepository.findByCategoryName(category));
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activeCategory", category);
        return "products";
    }

    @RequestMapping("/products/search")
    public String productSearch(Model model, @RequestParam("term") String term) {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p -> {
            if (p.getName().toLowerCase().contains(term.toLowerCase()))
                products.add(p);
        });
        model.addAttribute("products", products);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("activeCategory", "");
        return "products";
    }
}
