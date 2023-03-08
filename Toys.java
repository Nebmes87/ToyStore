public class Toys {

    Integer id;
    String toy_name;
    Integer quantity;
    Integer weight;

    public Integer getQuant() {
        return quantity;
    }

    public void setQuant(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }    

    @Override
    public String toString() {
        return "ID: " + id + ", Название: " + toy_name + ", Количество: " + quantity + ", Вес: " + weight;
    }
}
