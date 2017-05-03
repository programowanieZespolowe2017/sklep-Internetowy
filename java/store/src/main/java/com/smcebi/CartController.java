package com.smcebi;

import com.smcebi.products.Product;
import com.smcebi.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 4/30/2017 7:56 PM
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final ProductRepository productRepository;

    public CartController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @RequestMapping("/set/{id}/{quantity}")
    public String set(@PathVariable("id") long id, @PathVariable("quantity") long quantity, HttpSession session) {
        Map<Long, Long> products = (Map<Long, Long>) session.getAttribute("cart");
        if (products == null) {
            products = new HashMap<>();
            session.setAttribute("cart", products);
        }
        products.put(id, quantity);
        if (quantity == 0)
            products.remove(id);
        return "redirect:/cart";
    }

    @RequestMapping("/add/{id}/{quantity}")
    @ResponseBody
    public boolean add(@PathVariable("id") long id, @PathVariable("quantity") long quantity, HttpSession session) {
        Map<Long, Long> products = (Map<Long, Long>) session.getAttribute("cart");
        if (products == null) {
            products = new HashMap<>();
            session.setAttribute("cart", products);
        }
        products.put(id, quantity + (products.containsKey(id) ? products.get(id) : 0));
        return true;
    }

    @RequestMapping("/empty")
    public String empty(HttpSession session) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    @RequestMapping("")
    public String cart(HttpSession session, Model model) {
        if (session.getAttribute("cart") == null) return "cart";
        Map<Product, Long> products = new HashMap<>();
        Map<Long, Long> prods = (Map<Long, Long>) session.getAttribute("cart");
        final double[] sum = {0};
        prods.forEach((k, v) -> {
            Product p = productRepository.findOne(k);
            if (p != null) {
                sum[0] += v * p.getPrice();
                products.put(p, v);
            }
        });
        model.addAttribute("cartProducts", products);
        model.addAttribute("sum", sum[0]);
        return "cart";
    }
}
