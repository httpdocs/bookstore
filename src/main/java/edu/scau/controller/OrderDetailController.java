package edu.scau.controller;

import edu.scau.model.*;
import edu.scau.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shoulder on 7/6/2017.
 */
@Controller
public class OrderDetailController {
@Autowired
    OrderService orderService;
@Autowired
    UserService userService;
@Autowired
    OrderHasBookService oderHasBookService;
@Autowired
    BooksService bookService;
@Autowired
AddressService addressService;

    @RequestMapping(path = {"/order_detail"})
    public String orderDetail(Model model,
                             @RequestParam("userid") String userid) {
        List<ViewObject> vos = new ArrayList<>();

        ViewObject vo_user = new ViewObject();
        User user = userService.getUser(userid);
        Address address = addressService.getAddress(user.getDefaddr());
        vo_user.set("user", user);

        ViewObject vo_addr = new ViewObject();
        vo_addr.set("address", address);

        model.addAttribute("vo_user",vo_user);
        model.addAttribute("vo_addr",vo_addr);

        Order order = orderService.selectLatestOrder(userid, 0, 1);

        ViewObject vo_order = new ViewObject();
        vo_order.set("order", order);
        model.addAttribute("vo_order",vo_order);

        List<OrderHasBook> orderHasBooks = oderHasBookService.selectById(order.getOrderid());
        System.out.println(orderHasBooks.size());
        for(OrderHasBook ob: orderHasBooks){
            System.out.println(ob);
            String isbn = ob.getIsbn();
            Book book = bookService.selectById(isbn);
            ViewObject vo = new ViewObject();
            vo.set("book", book);
            vos.add(vo);
        }

        model.addAttribute("vos",vos);

        return "order_detail";

    }
}
