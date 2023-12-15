package org.example.to.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientTO implements Serializable {
    private String ip;
    private int connectionId;
    private String name;
    private String userType = "PLAYER";
}
