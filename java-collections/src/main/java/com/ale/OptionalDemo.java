package com.ale;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalDemo {
    public static void main(String[] args) {
        String name = "root";
        Insurance insurance = getInsurance();
        if (insurance != null) {
            name = insurance.getName();
        }

        System.out.println(name);
        insurance = null;
        String optionalInsurance = Optional.ofNullable(insurance).map(Insurance::getName).orElse("root");
        System.out.println("optionalInsurance: "+optionalInsurance);
    }

    private static Insurance getInsurance() {
        Insurance insurance = new Insurance();
        insurance.setName("jack");
        return insurance;
    }

}
