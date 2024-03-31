package com.miguelvela.urlshortener.links.infrastructure.Persistence;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MySqlLinksRepository")
public class MySqlLinksRepositoryImpl implements LinksRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Link> getAll() {
        return entityManager.createQuery(
                "SELECT l "
                + "FROM LinkVO l", LinkVO.class)
                .getResultList()
                .stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public Link getByUrlHash(String urlHash) {
        List<LinkVO> results = entityManager.createQuery(
                "SELECT l "
                    + "FROM LinkVO l "
                    + "WHERE l.urlHash = :urlHash", LinkVO.class)
                .setParameter("urlHash", urlHash)
                .getResultList();

        return results.isEmpty() ? null : mapToDomain(results.getFirst());
    }

    @Override
    public Link create(Link newLink) {
        LinkVO newLinkVO = new LinkVO(newLink.getUrl(), newLink.getUrlHash());
        this.entityManager.persist(newLinkVO);
        return this.mapToDomain(newLinkVO);
    }
}
