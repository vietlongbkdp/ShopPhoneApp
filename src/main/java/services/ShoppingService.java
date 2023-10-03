package services;

import daos.CartDao;
import daos.ProductDAO;

public class ShoppingService {
    private final ProductDAO productDAO;
    public ShoppingService(){
        productDAO= new ProductDAO();
    }

}
