package com.test.shopapp.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("banners")
    @Expose
    private List<Banner> banners = null;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("fresh_products")
    @Expose
    private List<FreshProduct> freshProducts = null;
    @SerializedName("ProductsForYou")
    @Expose
    private List<Object> productsForYou = null;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<FreshProduct> getFreshProducts() {
        return freshProducts;
    }

    public void setFreshProducts(List<FreshProduct> freshProducts) {
        this.freshProducts = freshProducts;
    }

    public List<Object> getProductsForYou() {
        return productsForYou;
    }

    public void setProductsForYou(List<Object> productsForYou) {
        this.productsForYou = productsForYou;
    }

}