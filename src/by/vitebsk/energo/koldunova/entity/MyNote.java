package by.vitebsk.energo.koldunova.entity;

public class MyNote {
    private int num;
    private String name;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNum() {
        return String.valueOf(num);
    }
    public void setNum(int num) {
        this.num = num;
    }
}
