package com.smcebi;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 12/21/2016 7:36 PM
 */
@ControllerAdvice()
public class CAdvice {

    @ModelAttribute
    public void globalAttributes(Model model, HttpSession session) {
        Map<Long, Long> products = (Map<Long, Long>) session.getAttribute("cart");
        model.addAttribute("itemsInCart", products == null ? 0 : products.values().stream().mapToLong(Long::longValue).sum());
    }
}