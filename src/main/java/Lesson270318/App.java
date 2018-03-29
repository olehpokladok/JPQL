package Lesson270318;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Comment;
import entity.Post;
import entity.Product;
import entity.Tag;
import enums.Status;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
        
//        Post post = em.createQuery("SELECT p FROM Post p WHERE p.id = ?1", Post.class).setParameter(1, 5).getSingleResult();
//        System.out.println(post);
//        addTages(em);
//        addPost(em);
//        addComment(em);
//        List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
//        comments.forEach(c -> System.out.println(c));
        
//        Comment combyId = em.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class).setParameter("id", 25).getSingleResult();
//        System.out.println(combyId);
//        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id > :id ",Post.class).setParameter("id", 50).getResultList();
//        posts.forEach(p -> System.out.println(p));
//        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id IN(:ids)", 
//        							Post.class).setParameter("ids", Arrays.asList(2, 54, 12, 75, 98)).getResultList();
//        posts.forEach(p -> System.out.println(p));
        
//        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :title", Post.class).setParameter("title", "%8_").getResultList();
//        posts.forEach(p -> System.out.println(p));
//        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id BETWEEN :first AND :last", Post.class)
//        		.setParameter("first", 5).setParameter("last", 12).getResultList();
//        posts.forEach(p -> System.out.println(p));
//       Long count = em.createQuery("SELECT count(c.id) FROM Comment c", Long.class).getSingleResult();
//       System.out.println("Count: " + count);
        
//        Long sum = em.createQuery("SELECT sum(c.id) FROM Comment c", Long.class).getSingleResult();
//        System.out.println("Sum: " + sum);
//        
//        Double avg = em.createQuery("SELECT avg(c.id) FROM Comment c", Double.class).getSingleResult();
//        System.out.println("Average: " + avg);
        
        
        
      Post post = em.createQuery("SELECT p FROM Post p JOIN FETCH p.product pp WHERE p.id = :id", Post.class)
    		  						.setParameter("id", 9).getSingleResult();
      System.out.println(post);
      System.out.println(post.getProduct());
        
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
    
    private static void addTages(EntityManager em) {
    	List<String> tags = new ArrayList<>();
    	tags.add("Java");
    	tags.add("SQL");
    	tags.add("ORM");
    	tags.add("JPA");
    	tags.add("MySQL");
    	tags.add("STS");
    	tags.add("Eclipse");
    	
    	for(int i = 0; i < tags.size(); i++) {
    		Tag tag = new Tag();
    		tag.setTagname(tags.get(i));
    		
    		em.persist(tag);
    	}
    	
    }
    
    private static void addPost(EntityManager em) {
    	for(int i = 1; i <= 100; i++) {
    		
    		Post post = new Post();
    		post.setTitle("Post title#" + i);
    		post.setContent("Long post contetnt#" + i);
    		
    		if(i % 2 == 0) post.setStatus(Status.DRAFT);
    		if(i % 2 == 1) post.setStatus(Status.PUBLISH);
    		
    		Product product = new Product();
    		product.setName("Product name#" + i);
    		product.setDescription("Product description#" + i);
    		product.setPrice(new BigDecimal(i + 10 + ".00" + "$"));
    		product.setInStock(15 + i);
    		
    		post.setProduct(product);
    		
    		em.persist(post);
    		
    		List<Tag> tags = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    		post.setTags(tags);
    	}
    }
    
    private static void addComment(EntityManager em) {
    	for(int i = 1; i <= 100; i++) {
    		Post post = em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class).setParameter("id", i).getSingleResult();
    		
    		Comment com = new Comment();
    		com.setAuthor("Author#" + i);
    		com.setComment("The best comment#" + i);
    		com.setPost(post);
    		
    		em.persist(com);
    	
    	}
    }
    
    
}
