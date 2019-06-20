package com.elo.oc.service;

import com.elo.oc.dao.NewletterSuscriberDAO;
import com.elo.oc.entity.NewsletterSuscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsletterSuscriberServiceImpl implements NewletterSuscriberService{
    @Autowired
    private NewletterSuscriberDAO suscriberDAO;

    @Override
    public void saveNewsletterSuscriber(NewsletterSuscriber suscriber) {
        suscriberDAO.saveNewsletterSuscriber(suscriber);
    }

    @Override
    public void deleteNewsletterSuscriber(Integer id) {
        suscriberDAO.deleteNewsletterSuscriber(id);
    }

    @Override
    public List<NewsletterSuscriber> getNewsletterSuscribers() {
        return suscriberDAO.getNewsletterSuscribers();
    }

    @Override
    public NewsletterSuscriber findNewsletterSuscriberById(Integer id) {
        return suscriberDAO.findNewsletterSuscriberById(id);
    }


    @Override
    public Optional<NewsletterSuscriber> findUserWithThisEmail(String email) {
        return suscriberDAO.getNewsletterSuscribers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

}
