package com.valdemare.spring5webapp.bootstrap;

import com.valdemare.spring5webapp.model.Author;
import com.valdemare.spring5webapp.model.Book;
import com.valdemare.spring5webapp.model.Publisher;
import com.valdemare.spring5webapp.repositories.AuthorRepository;
import com.valdemare.spring5webapp.repositories.BookRepository;
import com.valdemare.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener <ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){
        Publisher publisher = new Publisher();
        publisher.setName("foo");

        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book add = new Book("Domain Driven Design", "1234",publisher );
        eric.getBooks().add(add);
        add.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(add);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE smth", "2344", publisher);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
