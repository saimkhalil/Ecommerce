package com.example.ecommerce.ecomapp.Persistence.Repository;

import com.example.ecommerce.ecomapp.Persistence.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EcommRepoImpl implements EcommRepository
{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public EcommRepoImpl(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Product> findByProductId(String productId)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Product.Constants.PRODUCT_ID).is(productId));
        return Optional.ofNullable(mongoTemplate.findOne(query, Product.class));
    }

    @Override
    public void updateQuantity(Product product)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(Product.Constants.PRODUCT_ID).is(product.getProductId()));
        Update update = new Update();
        update.set(Product.Constants.QUANTITY, product.getQuantity());
        mongoTemplate.updateFirst(query, update, Product.class);
    }

    @Override
    public void saveAll(List<Product> products)
    {
        System.out.println(mongoTemplate.insertAll(products));
    }
}
