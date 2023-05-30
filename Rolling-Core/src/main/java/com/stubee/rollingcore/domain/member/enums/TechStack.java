package com.stubee.rollingcore.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechStack {

    //server or android
    SPRING("SPRING"), SPRING_BOOT("SPRING_BOOT"), JAVA("JAVA"), KOTLIN("KOTLIN"), GO("GO"),

    //ios
    SWIFT("SWIFT"), SWIFT_UI("SWIFT_UI"),

    //app
    DART("DART"), FLUTTER("FLUTTER"),

    //web
    REACT_JS("REACT_JS"), NEXT_JS("NEXT_JS"), VUE_JS("VUE_JS"),
    NODE_JS("NODE_JS"), NEST_JS("NEST_JS"), JAVASCRIPT("JAVASCRIPT"), TYPESCRIPT("TYPESCRIPT"),

    //ai | blockchain
    PYTHON("PYTHON"),

    //embedded or window or game
    C("C"), C_PLUSPLUS("C++"), C_SHARP("C#"), UNITY("UNITY"), UNREAL("UNREAL");

    private final String name;

}