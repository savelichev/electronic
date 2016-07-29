package ua.savelichev.electronic.domain.entity;


public class Notebook extends Product {

    /**
     * Notebook's display diagonal size.
     */
    private String displayDiagonal;

    /**
     * Notebook's processor title.
     */
    private String processor;

    /**
     * Notebook's RAM capacity.
     */
    private int ram;

    /**
     * Notebook's HDD capacity
     */
    private int hdd;


    public String getDisplayDiagonal() {
        return displayDiagonal;
    }

    public void setDisplayDiagonal(String displayDiagonal) {
        this.displayDiagonal = displayDiagonal;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }


    @Override
    public String toString() {
        return "Notebook{" + super.toString() +
                "displayDiagonal='" + displayDiagonal + '\'' +
                ", processor='" + processor + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                "} ";
    }
}
