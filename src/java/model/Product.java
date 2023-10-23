/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Product {
   int id;
     String name;
     String image;
     double price;
     String tiltle;
     String description;
     int cateID;
     int sell_ID;
     
     
    public Product() {
    }

    public Product(int id, String name, String image, double price, String tiltle, String description, int cateID, int sell_ID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.tiltle = tiltle;
        this.description = description;
        this.cateID = cateID;
        this.sell_ID = sell_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public int getSell_ID() {
        return sell_ID;
    }

    public void setSell_ID(int sell_ID) {
        this.sell_ID = sell_ID;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", image=" + image + ", price=" + price + ", tiltle=" + tiltle + ", description=" + description + ", cateID=" + cateID + ", sell_ID=" + sell_ID + '}';
    }

    

    
    
}
