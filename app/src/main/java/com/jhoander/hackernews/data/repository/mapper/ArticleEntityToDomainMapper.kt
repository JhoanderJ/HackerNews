package com.jhoander.hackernews.data.repository.mapper

import com.jhoander.hackernews.data.entity.ArticleEntity
import com.jhoander.hackernews.domain.model.Article
import com.jhoander.hackernews.utils.base.Mapper
import javax.inject.Inject

class ArticleEntityToDomainMapper @Inject constructor(
    private val hitEntityToDomainMapper: HitEntityToDomainMapper
) :
    Mapper<ArticleEntity, Article>() {

    override fun map(value: ArticleEntity): Article {
        return Article(
            hits = hitEntityToDomainMapper.map(value.hits)
        )
    }

    override fun reverseMap(value: Article): ArticleEntity {
        TODO("Not yet implemented")
    }

}


