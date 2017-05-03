package com.smcebi;

import com.smcebi.checkout.OrderdRepository;
import com.smcebi.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 4/29/2017 8:48 PM
 */
@Controller
public class IndexController {

    private final ProductRepository productRepository;
    private final OrderdRepository orderdRepository;

    @Autowired
    public IndexController(ProductRepository productRepository, OrderdRepository orderdRepository) {
        this.productRepository = productRepository;
        this.orderdRepository = orderdRepository;
    }

    @RequestMapping("/")
    private String index(Model model) {
        productRepository.findAll().forEach(p->{
            model.addAttribute("prd", p);
        });

        model.addAttribute("topSelling", productRepository.findTop3ByOrderByIdDesc());
        model.addAttribute("newProducts", productRepository.findTop3ByOrderByIdDesc());
        model.addAttribute("popularProducts", productRepository.findTop3ByOrderByIdDesc());
        return "index";
    }

}
