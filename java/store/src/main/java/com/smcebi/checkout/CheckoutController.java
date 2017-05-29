package com.smcebi.checkout;

import com.smcebi.products.Product;
import com.smcebi.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 4/30/2017 7:56 PM
 */
@Controller
@RequestMapping("/checkout")
public class CheckoutController {


    private final ProductRepository productRepository;
    private final OrderdRepository orderdRepository;
    private final OrderEntryRepository orderEntryRepository;

    @Autowired
    public CheckoutController(ProductRepository productRepository, OrderdRepository orderdRepository, OrderEntryRepository orderEntryRepository) {
        this.orderdRepository = orderdRepository;
        this.orderEntryRepository = orderEntryRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("")
    public String checkout(HttpSession session, Model model) {
        Map<Long, Long> prods = (Map<Long, Long>) session.getAttribute("cart");
        final double[] sum = {0};
        prods.forEach((k, v) -> {
            Product p = productRepository.findOne(k);
            if (p != null) {
                sum[0] += v * p.getPrice();
            }
        });
        model.addAttribute("sum", sum[0]);
        return "checkout";
    }

    @RequestMapping("/confirm")
    public String confirm(HttpSession session, Model model, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address) {
        try {
            Orderd o = new Orderd();
            o.setStatus(OrderStatus.Created);
            o.setName(name);
            o.setEmail(email);
            o.setAddress(address);
            o.setTimestamp(new Timestamp(System.currentTimeMillis()));
            List<OrderEntry> products = new ArrayList<>();

            Map<Long, Long> prods = (Map<Long, Long>) session.getAttribute("cart");
            prods.forEach((k, v) -> {
                OrderEntry oe = new OrderEntry(v, productRepository.findOne(k));
                orderEntryRepository.save(oe);
                products.add(oe);
            });
            o.setProducts(products);
            orderdRepository.save(o);
            session.removeAttribute("cart");
            return "checkout-confirm";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
}
