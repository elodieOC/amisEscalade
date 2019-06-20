package com.elo.oc.dao;

import com.elo.oc.entity.NewsletterSuscriber;

import java.util.List;

public interface NewletterSuscriberDAO {

    void saveNewsletterSuscriber(NewsletterSuscriber suscriber);
    void deleteNewsletterSuscriber(Integer id);

    List < NewsletterSuscriber > getNewsletterSuscribers();
    NewsletterSuscriber findNewsletterSuscriberById(Integer id);



}
