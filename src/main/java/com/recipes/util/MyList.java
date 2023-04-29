package com.recipes.util;


import lombok.Data;

import java.util.List;

@Data
public class MyList<T> {

    List<T> list;

}
