package services;

import daos.CartDao;
import daos.ProductDAO;
import models.Cart;
import models.CartDetail;
import models.Product;
import models.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class ShoppingService {
    private final ProductDAO productDAO;
    private final CartDao cartDao;

    public ShoppingService() {
        productDAO = new ProductDAO();
        cartDao = new CartDao();
    }

    public Cart findByUserId(int id) {
        return cartDao.findByUserId(id);
    }

    public void createCartByUserId(int id) {
        cartDao.createCart(id);
    }

//    public void createCartDetail(User user, HttpServletRequest req) {
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setCart(findByUserId(user.getId()));
//        cartDetail.setProduct(new Product(Integer.parseInt(req.getParameter("id"))));
//        cartDetail.setQuantity(1);
//        cartDetail.setTotalAmount(cartDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
//        cartDao.createCartDetail(cartDetail);
//    }
}
