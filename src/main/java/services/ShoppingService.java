package services;

import daos.CartDao;
import daos.ProductDAO;
import models.Cart;
import models.CartDetail;
import models.Product;
import models.User;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

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

    public void createCartDetail(User user, HttpServletRequest req) {
        cartDao.createCartDetail(cartDao.findCartId(user.getId()), Integer.parseInt(req.getParameter("id")));
    }

    public int findCartIdByUserId(User user) {
        return cartDao.findCartId(user.getId());
    }

    public void deleteCartDetail(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        cartDao.deleteCartDetail(id);
    }

    public void updateCartDetails(Cart cart, HttpServletRequest req) {
        List<Integer> cDetailIDS = Arrays.stream(req.getParameterValues("cDetailID"))
                .map(Integer::parseInt).toList();
        List<Integer> quantities = Arrays.stream(req.getParameterValues("quantities"))
                .map(Integer::parseInt).toList();
        List<Integer> checkeds = Arrays.stream(req.getParameterValues("cartChecked"))
                .map(Integer::parseInt).toList();
        for (int i = 0; i < cDetailIDS.size(); i++) {
            cartDao.updateCartDetailChecked(cDetailIDS.get(i), quantities.get(i), checkeds.get(i));
        }
    }

    public void deleteCartDetails(String[] ids) {
        for (var id : ids) {
            cartDao.deleteCartDetail(Integer.parseInt(id));
        }
    }

    public boolean checkProductInCart(int cartId, int idProduct) {
        boolean check = false;
        List<CartDetail> cartDetails = cartDao.findListCartDetailByCartID(cartId);
        if (cartDetails.isEmpty()) {
            return check;
        }
        for (var cartDetail : cartDetails) {
            if (cartDetail.getProduct().getId() == idProduct) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void updateCartDetail(Cart cart, int idProduct) {
        List<CartDetail> cartDetails = cart.getCartDetails();
        for (var cartDetail : cartDetails) {
            if (cartDetail.getProduct().getId() == idProduct) {
                cartDetail.setQuantity(cartDetail.getQuantity() + 1);
                cartDao.updateCartDetail(cart.getId(), idProduct, cartDetail.getQuantity());
            }
        }
    }

    public void deleteCartDetail(int id) {
        cartDao.deleteCartDetail(id);
    }

    //    public void createCartDetail(User user, HttpServletRequest req) {
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setCart(findByUserId(user.getId()));
//        cartDetail.setProduct(new Product(Integer.parseInt(req.getParameter("id"))));
//        cartDetail.setQuantity(1);
//        cartDetail.setTotalAmount(cartDetail.getProduct().getPrice().multiply(BigDecimal.valueOf(cartDetail.getQuantity())));
//        cartDao.createCartDetail(cartDetail);
//    }
    public List<CartDetail> cartDetails(int cartId, int checked) {
        return  cartDao.findCartByCartIdChecked(cartId,checked);
    }
}
