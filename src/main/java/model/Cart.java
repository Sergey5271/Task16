package model;

import entity.Book;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cart implements Serializable {

    private static final long serialVersionUID = -2311800710520861762L;

    private Map<Book, Integer> items = new HashMap<>();

    public void add(Book book) {
        if (items.containsKey(book)) {
            items.replace(book, items.get(book) + 1);
        } else {
            items.put(book, 1);
        }
    }

    public void remove(Book book) {
        items.remove(book);
    }

    public Map<Book, Integer> getItems() {
        return new HashMap<>(items);
    }

    private Optional<Book> findProduct(int productId) {
        return items.keySet().stream()
                .filter(e -> e.getId() == productId).findFirst();
    }


    public int countItems() {
        int sum = 0;
        for (int i : items.values()) {
            sum += i;
        }
        return sum;
    }

    public double editCount(int productId, int quantity)  {
        Optional<Book> product = findProduct(productId);
        if (product.isPresent()) {
            items.replace(product.get(), quantity);
            return product.get().getPrice() * quantity;
        }
        throw new IllegalArgumentException();
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Map.Entry<Book, Integer> item : items.entrySet()) {
            sum += item.getKey().getPrice() * item.getValue();
        }
        return sum;
    }
}
