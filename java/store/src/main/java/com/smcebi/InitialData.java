package com.smcebi;

import com.smcebi.products.Category;
import com.smcebi.products.CategoryRepository;
import com.smcebi.products.Product;
import com.smcebi.products.ProductRepository;
import com.smcebi.security.user.Role;
import com.smcebi.security.user.User;
import com.smcebi.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 4/30/2017 12:30 AM
 */
@Component
public class InitialData {

    @Autowired
    public InitialData(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        if(productRepository.count()>0) return;
        User u=new User();
        u.setLogin("admin");
        u.setPassword("123456");
        u.setRole(Role.ADMIN);
        userRepository.save(u);
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        categoryRepository.save(new Category("Diodes"));
        categoryRepository.save(new Category("Capacitors"));
        productRepository.save(new Product(111, 0.01d, "1200PF 50V CERAMIC DISC CAPACITOR", "abc", "Ceramic-Capacitors-small_pic_191.jpg", categoryRepository.findOneByName("Capacitors")));
        productRepository.save(new Product(745, 0.03d, "1.5NF 0.0015UF 100V 5% MYLAR FILM CAPACITORS", "abc", "MYLAR-1_74.jpg", categoryRepository.findOneByName("Capacitors")));
        productRepository.save(new Product(239, 0.10d, "15NF 0.015UF 100V 5% POLYESTER FILM BOX TYPE CAPACITOR", "abc", "box-type-cap_29.jpg", categoryRepository.findOneByName("Capacitors")));
        productRepository.save(new Product(491, 0.13d, "0.1UF 50V RADIAL TANTALUM CAPACITOR", "abc", "tantalum_cap_16.jpg", categoryRepository.findOneByName("Capacitors")));
        productRepository.save(new Product(932, 0.01d, "10PF 50V SMD CERAMIC CHIP CAPACITOR 0805", "abc", "smd_caps_1.jpg", categoryRepository.findOneByName("Capacitors")));

        productRepository.save(new Product(121, 0.42d, "BYW29-200G BYW29-200 ULTRAFAST RECTIFIER 200V 8A", "abc", "1538-1.jpg.jpg", categoryRepository.findOneByName("Diodes")));
        productRepository.save(new Product(498, 0.04d, "1N4737 ZENER DIODE 1W 7.5V", "abc", "DO-41_49.jpg", categoryRepository.findOneByName("Diodes")));
        productRepository.save(new Product(563, 0.69d, "SINGLE-PHASE BRIDGE RECTIFIER MIC 35A 1000V", "abc", "a-5044.jpg", categoryRepository.findOneByName("Diodes")));
        productRepository.save(new Product(947, 0.12d, "BAT41 SCHOTTKY BARRIER DIODE", "abc", "DO-201AD_13.jpg", categoryRepository.findOneByName("Diodes")));

    }
}
