package com.ecom.beans;

public class BankCard {

    private String card_id;
    private String bank_name;
    private int type;
    private String card_holder;
    private String reserve_phone_number;
    private int password;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCard_holder() {
        return card_holder;
    }

    public void setCard_holder(String card_holder) {
        this.card_holder = card_holder;
    }

    public String getReserve_phone_number() {
        return reserve_phone_number;
    }

    public void setReserve_phone_number(String reserve_phone_number) {
        this.reserve_phone_number = reserve_phone_number;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
