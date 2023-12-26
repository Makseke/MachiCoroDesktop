package org.machicoro.to.domain.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientPublicInfoTO implements Serializable{
    private String name;
    private String clientType;
}
