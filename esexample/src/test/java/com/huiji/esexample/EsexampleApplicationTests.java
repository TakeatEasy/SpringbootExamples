package com.huiji.esexample;

import com.huiji.esexample.dao.ProductDao;
import com.huiji.esexample.entity.Product;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EsexampleApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProductDao productDao;

    @Test
    void contextLoads(){

    }

    // 根据实体类的对应关系自动进行创建
    @Test
    public void createIndex(){
        System.out.println("创建索引！");
    }

    @Test
    public void deleteIndex(){
        boolean flag = elasticsearchRestTemplate.deleteIndex(Product.class);
        System.out.println("删除索引！");
    }

    @Test
    public void save(){
        Product product = new Product();
        product.setId(2L);
        product.setTitle("小米mix");
        product.setCategory("手机");
        product.setPrice(2999.0);
        product.setImages("http://www.pic.com");
        productDao.save(product);
    }


    @Test
    public void update(){
        Product product = new Product();
        product.setId(1L);
        product.setTitle("苹果12");
        product.setCategory("手机");
        product.setPrice(8999.0);
        product.setImages("http://www.pic.com");
        productDao.save(product);
    }

    @Test
    public void findById(){
        Product product = productDao.findById(1L).get();
        System.out.println(product);
    }

    @Test
    public void findAll(){
        Iterable<Product> products = productDao.findAll();
        for(Product product: products)
            System.out.println(product);
    }

    @Test
    public void delete(){
        Product product = new Product();
        product.setId(1L);
        productDao.delete(product);
    }

    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Product product = new Product();
            product.setId(2L + i);
            product.setTitle("苹果12");
            product.setCategory("手机");
            product.setPrice(8999.0);
            product.setImages("http://www.pic.com");
            productList.add(product);
        }
        productDao.saveAll(productList);
    }

    @Test
    public void findByName(){
        List<Product> byTitle = productDao.findByTitle("12");
        for (Product p: byTitle) {
            System.out.println(p);
        }
    }

    @Test
    public void findByPageable(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int from = 0;
        int size = 5;

        PageRequest pageRequest = PageRequest.of(from, size, sort);

        Page<Product> productPage = productDao.findAll(pageRequest);
        for(Product product: productPage.getContent())
            System.out.println(product);
    }

    @Test
    public void termQuery(){
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");
        Iterable<Product> products = productDao.search(termQueryBuilder);
        for(Product product: products)
            System.out.println(product);
    }

}
