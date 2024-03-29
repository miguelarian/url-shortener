package com.miguelvela.urlshortener.links.infrastructure.Persistence;

import com.miguelvela.urlshortener.links.domain.Link;
import com.miguelvela.urlshortener.links.domain.LinksRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
    }

    @Override
    public Link getByUrlHash(String urlHash) {
        List<LinkVO> results = entityManager.createQuery(
                "SELECT l "
                    + "FROM LinkVO l "
                    + "WHERE l.urlHash = :urlHash", LinkVO.class)
                .setParameter("urlHash", urlHash)
                .getResultList();
        return results.isEmpty() ? null : mapToDomain(results.get(0));
    }

    @Override
    @Transactional
    public Link create(Link newLink) {
        LinkVO newLinkVO = new LinkVO(newLink.getUrl(), newLink.getUrlHash());
        this.entityManager.persist(newLinkVO);
        return this.mapToDomain(newLinkVO);
    }
}