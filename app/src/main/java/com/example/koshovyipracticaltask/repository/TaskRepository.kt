package com.example.koshovyipracticaltask.repository

import kotlinx.coroutines.delay

class TaskRepository {

    // Внутрішній кеш, який можна змінювати лише в межах цього класу
    private val cache = mutableListOf<String>()


    // Отримання списку задач (рядків).
    suspend fun getTasks(): List<String> {
        if (cache.isEmpty()) { // Якщо кеш має дані — повертаємо їх. Якщо порожній — завантажує з мережі.
            val networkTasks = fetchFromNetwork()
            updateCache(networkTasks)
        }
        return cache.toList()
    }

    // Примусове оновлення кешу даними з мережі, незалежно від його поточного стану.
    suspend fun refresh() {
        val networkTasks = fetchFromNetwork()
        updateCache(networkTasks)
    }

    // Допоміжний метод
    // Симуляція мережевого запиту для завантаження задач.
    private suspend fun fetchFromNetwork(): List<String> {
        delay(500) // Імітація затримки
        return listOf("Задача 1", "Задача 2")
    }

    // Допоміжний метод
    // Оновлення кешу заданим списком задач (рядків).
    private fun updateCache(newTasks: List<String>) {
        cache.clear()
        cache.addAll(newTasks)
    }
}
