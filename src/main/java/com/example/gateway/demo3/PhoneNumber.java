package com.example.gateway.demo3;

import lombok.Data;

import java.util.Comparator;

import static java.util.Comparator.comparing;
/**
 * Created by hrhrh on 2020/7/14 17:43
 */
@Data
public class PhoneNumber {

    private static final Comparator<PhoneNumber> COMPARATOR = comparing((PhoneNumber pn) -> pn.areaCode).thenComparing(pn -> pn.prefix).thenComparing(pn -> pn.linNum);

    private String prefix;

    private String linNum;

    private String areaCode;

    public int compareTo(PhoneNumber pn) {
        return COMPARATOR.compare(this, pn);
    }


    public static void main(String[] args) {
        PhoneNumber p1 = new PhoneNumber();
        p1.setAreaCode("00678");
        p1.setLinNum("778899");
        p1.setPrefix("55667788");

        PhoneNumber p2 = new PhoneNumber();
        p2.setAreaCode("00678");
        p2.setLinNum("778899");
        p2.setPrefix("55667788");

        System.out.println(p1.compareTo(p2));
    }
}
