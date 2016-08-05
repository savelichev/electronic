package ua.savelichev.electronic.domain.entity;

public class StoragePosition {

    public StoragePosition() {
    }

    public StoragePosition(int article, int amount) {
        this.article = article;
        this.amount = amount;
    }

    private int id;

    private int article;

    private int amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoragePosition that = (StoragePosition) o;

        return article == that.article;

    }

    @Override
    public int hashCode() {
        return article;
    }
}
