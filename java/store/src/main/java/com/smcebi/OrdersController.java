package com.smcebi;

import com.smcebi.checkout.OrderStatus;
import com.smcebi.checkout.Orderd;
import com.smcebi.checkout.OrderdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 29.05.2017 15:31
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrderdRepository orderdRepository;

    @ResponseBody
    @RequestMapping("/get/{id}")
    Orderd getOrder(@PathVariable("id") long id) {
        return orderdRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/first")
    long firstOrder() {
        Optional<Orderd> o = Optional.ofNullable(orderdRepository.findTop1ByStatusOrderByTimestampAsc(OrderStatus.Created));
        return o.map(Orderd::getId).orElse(-1L);
    }

    @ResponseBody
    @RequestMapping(value = "/setStatus/{id}", method = RequestMethod.POST)
    String setStatus(@PathVariable("id") long id, @RequestParam("status") OrderStatus status) {
        try {
            Orderd o = orderdRepository.findOne(id);
            o.setStatus(status);
            orderdRepository.save(o);
        } catch (Exception e) {
            return "error";
        }
        return "ok";
    }
}
