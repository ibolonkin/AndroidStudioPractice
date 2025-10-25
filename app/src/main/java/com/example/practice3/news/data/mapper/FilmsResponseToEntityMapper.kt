package com.example.practice3.news.data.mapper

import com.example.practice3.news.data.model.FilmsListResponse
import com.example.practice3.news.domain.model.FilmsEntity

class FilmsResponseToEntityMapper {
    fun mapResponse(response: FilmsListResponse): List<FilmsEntity> {
        return response.documents?.map { doc ->
            FilmsEntity(
                id = doc.id.orEmpty(),
                title = doc.title.orEmpty(),
                descr = doc.descr,
                year = doc.year.orEmpty(),
                imageUrl = doc.imageUrl,
            )
        }.orEmpty()
    }
}