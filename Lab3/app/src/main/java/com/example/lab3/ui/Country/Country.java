package com.example.lab3.ui.Country;

public class Country {
    private String Name;
    private String Capital;
    private String Region;




    public Country(String name, String capital,String region)
    {
        Name = name;
        Capital = capital;
        Region = region;



    }

    public String GetName(){return Name;}
    public String GetCapital(){return Capital;}
    public String GetRegion(){return Region;}



    public void SetName(String s1) {Name = s1;}
    public void SetCapital(String s1) {Capital = s1;}
    public void SetRegion(String s1) {Region = s1;}

}
