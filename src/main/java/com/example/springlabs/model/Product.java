package com.example.springlabs.model;

public class Product {
        private int id;
        private String name;
        private double price;
        private Category category;

        public Product() {

        }

        @Override
        public String toString() {
                return "Product{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", price=" + price +
                        ", category=" + category +
                        '}';
        }

        public Product(int id, String name, double price, Category category) {
                this.id = id;
                this.name = name;
                this.price = price;
                this.category = category;
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

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }
}


