package br.com.sucram.person.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {

    MOBILE("Mobile"),
    HOME("Home"),
    COMMERCIAL("Commercial");

    private final String description;

}
