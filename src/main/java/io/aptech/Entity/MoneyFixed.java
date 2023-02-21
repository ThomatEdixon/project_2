package io.aptech.Entity;

public class MoneyFixed {
    private String o_foodDrink;
    private String o_clothes;
    private String o_petroleum;
    public MoneyFixed(){}
    public MoneyFixed(String o_foodDrink, String o_clothes, String o_petroleum) {
        this.o_foodDrink = o_foodDrink;
        this.o_clothes = o_clothes;
        this.o_petroleum = o_petroleum;
    }

    public String getO_foodDrink() {
        return o_foodDrink;
    }

    public void setO_foodDrink(String o_foodDrink) {
        this.o_foodDrink = o_foodDrink;
    }

    public String getO_clothes() {
        return o_clothes;
    }

    public void setO_clothes(String o_clothes) {
        this.o_clothes = o_clothes;
    }

    public String getO_petroleum() {
        return o_petroleum;
    }
    public String getCerruntMoney(){
        int foodDrink = this.o_foodDrink == null ? 0 : Integer.parseInt(this.o_foodDrink);
        int clothes = this.o_clothes == null ? 0 : Integer.parseInt(this.o_clothes);
        int petroleum = this.o_petroleum == null ? 0 :Integer.parseInt(this.o_petroleum);
       return String.valueOf( foodDrink + clothes + petroleum);
    }

    public void setO_petroleum(String o_petroleum) {
        this.o_petroleum = o_petroleum;
    }
}
