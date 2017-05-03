package com.smcebi;

import com.smcebi.checkout.OrderdRepository;
import com.smcebi.products.CategoryRepository;
import com.smcebi.products.Product;
import com.smcebi.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;

/**
 * 5/1/2017 3:17 PM
 */
@Controller
@RequestMapping("/backoffice")
public class BackofficeController {

    @Autowired
    private OrderdRepository orderdRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("")
    public String backoffice(Model m) {
        return "redirect:backoffice/products";
    }

    @RequestMapping("/products")
    public String backofficeProducts(Model m) {
        m.addAttribute("products", productRepository.findAll());
        m.addAttribute("activeCategory", "products");
        return "backoffice/backoffice-products";
    }

    @RequestMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable("id") long id, Model model) {
        try {
            productRepository.delete(id);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("productRemoveError", true);
        }
        return backofficeProducts(model);
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.GET)
    public String backofficeProductsNew(Model m) {
        m.addAttribute("products", productRepository.findAll());
        m.addAttribute("categories", categoryRepository.findAll());
        m.addAttribute("activeCategory", "products/new");
        return "backoffice/backoffice-products-new";
    }

    @RequestMapping(value = "/products/new", method = RequestMethod.POST)
    public String backofficeProductsNewPost(Model m, @RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("description") String description, @RequestParam("category") long category, @RequestParam("stockLevel") int stockLevel, @RequestParam("picture") MultipartFile picture) {
        Product p = new Product();
        try (FileOutputStream fos = new FileOutputStream("products/img/products/" + picture.getName())) {
            fos.write(picture.getBytes());
            fos.close();
        } catch (Exception e) {
        }

        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        p.setStockLevel(stockLevel);
        p.setCategory(categoryRepository.findOne(category));
        p.setImg(picture.getName());
        productRepository.save(p);

        m.addAttribute("products", productRepository.findAll());
        m.addAttribute("categories", categoryRepository.findAll());
        m.addAttribute("activeCategory", "products/new");
        return "backoffice/backoffice-products-new";
    }

    @RequestMapping("/orders")
    public String backofficeOrders(Model m) {
        m.addAttribute("orders", orderdRepository.findAll());
        m.addAttribute("activeCategory", "orders");
        return "backoffice/backoffice-orders";
    }

    @RequestMapping("/orders/remove/{id}")
    public String removeOrder(@PathVariable("id") long id) {
        orderdRepository.delete(id);
        return "redirect:/backoffice/orders";
    }
}
