package Lesson270318;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Comment;
import entity.Post;
import entity.Tag;
import enums.Status;

public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
        EntityManager em = factory.createEntityManager();
        
        em.getTransaction().begin();
//        addTages(em);
//        addPost(em);
//        addComment(em);
//        List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
//        comments.forEach(c -> System.out.println(c));
        
//        Comment combyId = em.createQuery("SELECT c FROM Comment c WHERE c.id = :id", Comment.class).setParameter("id", 25).getSingleResult();
//        System.out.println(combyId);
        	
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
