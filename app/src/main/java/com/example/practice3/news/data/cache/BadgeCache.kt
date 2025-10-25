package com.example.practice3.news.data.cache

class BadgeCache {
    private var hasActiveFilters: Boolean = false

    fun setFiltersActive(active: Boolean) {
        hasActiveFilters = active
    }

    fun hasActiveFilters(): Boolean = hasActiveFilters
}