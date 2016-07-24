package ua.savelichev.electronic.domain.entity;

public class Notebook extends Product{

    private String displayDiagonal;

    private String processor;

    private int ram;

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
        return "Notebook{" + super.toString()+
                "displayDiagonal='" + displayDiagonal + '\'' +
                ", processor='" + processor + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                "} ";
    }
}
