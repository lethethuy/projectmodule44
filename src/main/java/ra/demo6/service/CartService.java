package ra.demo6.service;

import ra.demo6.model.CartItem;

import java.util.List;

public class CartService implements IGenericService<CartItem, Integer> {
    private List<CartItem> cart;

    public CartService(List<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public List<CartItem> findAll() {
        return cart;
    }

    @Override
    public void save(CartItem cartItem) {
        if (findByID(cartItem.getId()) == null) {
            // thêm mới
            cart.add(cartItem);
        } else {
            // cập nhật
            cart.set(cart.indexOf(findByID(cartItem.getId())), cartItem);
        }
    }

    @Override
    public CartItem findByID(Integer id) {
        for (CartItem c : cart) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    public CartItem findByIDPro(int  idPro) {
        for (CartItem c : cart) {
            if (c.getProduct().getId() == idPro) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        cart.remove(findByID(id));

    }

    public int getNewId() {
        int max = 0;
        for (CartItem ci : cart
        ) {
            if (ci.getId() > max) {
                max = ci.getId();
            }
        }
        return max + 1;
    }

}
