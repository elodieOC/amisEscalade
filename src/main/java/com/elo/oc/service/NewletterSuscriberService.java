package com.elo.oc.service;

import com.elo.oc.entity.NewsletterSuscriber;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface NewletterSuscriberService {

    void saveNewsletterSuscriber(NewsletterSuscriber suscriber);
    void deleteNewsletterSuscriber(Integer id);

    List < NewsletterSuscriber > getNewsletterSuscribers();
    NewsletterSuscriber findNewsletterSuscriberById(Integer id);
    Optional<NewsletterSuscriber> findUserWithThisEmail(String email);



}
