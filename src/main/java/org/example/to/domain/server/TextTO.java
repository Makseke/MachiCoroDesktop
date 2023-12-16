package org.example.to.domain.server;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TextTO implements Serializable {
    private String text;
}
