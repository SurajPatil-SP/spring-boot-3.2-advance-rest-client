package com.main.restclient.client;

import com.main.restclient.dto.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ProductServiceClient {
    private RestClient restClient;

    public ProductServiceClient() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public Product saveNewProduct(Product product) {
        return restClient.post()        //Type of Http method
                .uri("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)          //Input you want to give (Request Payload)
                .retrieve()             //for above input retrive
                .body(Product.class);   //what input you are expecting
    }


    public List<Product> getAllProducts(){
        return restClient.get()
                .uri("/api/product")
                .retrieve()
                .body(new ParameterizedTypeReference<List<Product>>(){});
    }

    public Product getProduct(int id){
        return restClient.get()
                .uri("/api/product/{id}",id)
                .retrieve()
                .body(Product.class);
    }


    public Product updateProduct(int id,Product product){
        return restClient.put()
                .uri("/api/product/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Product.class);
    }

    public String deleteProduct(int id){
        restClient.delete()
                .uri("/api/product/{id}",id)
                .retrieve()
                .toBodilessEntity();
        return "product removed : "+id;
    }
}
