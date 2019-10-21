package com.marat.simbersoft;

public class ModelListView {

    private String name, capital, region, subregion, demonym, flag;
    private int population, area;
    private Correncies correncies;

    public String getName() {
        return name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public void setCorrencies(Correncies correncies) {
        this.correncies = correncies;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getDemonym() {
        return demonym;
    }

    public String getFlag() {
        return flag;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }

    public Correncies getCorrencies() {
        return correncies;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Correncies{
    String code, name,symbol;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}
