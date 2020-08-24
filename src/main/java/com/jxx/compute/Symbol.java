package com.jxx.compute;

/**
 *
 * @author dhs
 */
public enum Symbol {

    HEGE(1),LEVELB(2),LEVELC(3),LEVELD(4);

    private Integer id;

    Symbol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "id=" + id +
                '}';
    }
}
