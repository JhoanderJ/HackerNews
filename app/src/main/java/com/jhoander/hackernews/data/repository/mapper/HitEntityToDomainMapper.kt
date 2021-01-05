package com.jhoander.hackernews.data.repository.mapper

import com.jhoander.hackernews.data.entity.HitEntity
import com.jhoander.hackernews.domain.model.Hit
import com.jhoander.hackernews.utils.base.Mapper
import javax.inject.Inject

class HitEntityToDomainMapper @Inject constructor() : Mapper<HitEntity, Hit>() {
    override fun map(value: HitEntity): Hit {
        return Hit(
            title = value.title,
            storyTitle = value.storyTitle,
            author = value.author,
            createAt = value.createdAt
        )
    }

    override fun reverseMap(value: Hit): HitEntity {
        return HitEntity(
            title = value.title,
            storyTitle = value.storyTitle,
            author = value.author,
            createdAt = value.createAt
        )
    }
}