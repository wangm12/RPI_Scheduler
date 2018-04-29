package com.envision.scheduler.beans;

import java.io.Serializable;

/**
 * TODO
 *
 * @author mj
 * @version V 1.0
 * @date 28/3/2018 上午1:18
 */
public class FacilityBean implements Serializable {
    private static final long serialVersionUID = 3421243661958144727L;
    public int res;
    public String name;
    public int array;

    public FacilityBean(int res, String name, int array) {
        this.res = res;
        this.name = name;
        this.array = array;
    }
}
