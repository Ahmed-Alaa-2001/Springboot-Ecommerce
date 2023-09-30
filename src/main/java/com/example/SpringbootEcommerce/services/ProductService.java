package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.dto.ProductDTO;
import com.example.SpringbootEcommerce.exceptions.ProductExist;
import com.example.SpringbootEcommerce.exceptions.ProductNotFound;
import com.example.SpringbootEcommerce.model.Product;
import com.example.SpringbootEcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product>findProduct() throws ProductNotFound{
        List<Product> products=productRepository.findAll();
//        for (int i=0;i<p.size();i++){
//            System.out.println(p.get(i).getId());
//        }
//        System.out.println("------------");
        if(products.isEmpty()){
            throw new ProductNotFound("There is no product");
        }
        return productRepository.findAll();
    }
    public Product findProductByName(String name) throws ProductNotFound {
//        System.out.println("The datatype of the `id` variable is: " + name.getClass().getName());
        Optional<Product> opProduct=productRepository.findByNameIgnoreCase(name);
//        List<Product> p=productRepository.findAll();
//        for (int i=0;i<p.size();i++){
//            System.out.println(p.get(i).getId());
//            System.out.println("The datatype of the `id` variable is: " + p.get(i).getId().getClass().getName());
//        }
        if(!opProduct.isPresent()){
            throw new ProductNotFound("Product not found with given id");
        }
        return opProduct.get();
    }
    public String deleteProductByName(String name) throws ProductNotFound {
        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(name);
        if(!opProduct.isPresent()){
            throw new ProductNotFound("Product isn't exist with given name");
        }
        productRepository.delete(opProduct.get());
        return "Product deleted successfully";
    }
    public Product addProduct(ProductDTO productDTO) throws ProductExist {
        Optional<Product>opProduct = productRepository.findByNameIgnoreCase(productDTO.getName());
        if(opProduct.isPresent()){
            throw new ProductExist("Product is already exist");
        }
        Product product=new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        return productRepository.save(product);
    }
    public Product updateProduct(ProductDTO productDTO) throws ProductNotFound {
        Optional<Product>opProduct=productRepository.findByNameIgnoreCase(productDTO.getName());
        if(!opProduct.isPresent()){
            throw new ProductNotFound("Product isn't exist with given name");
        }
        Product existProduct=opProduct.get();
        existProduct.setName(productDTO.getName());
        existProduct.setDescription(productDTO.getDescription());
        existProduct.setPrice(productDTO.getPrice());
        return productRepository.save(existProduct);
    }
}
