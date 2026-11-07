package com.github.mvi_sample

interface GetItemsUseCase {
    suspend operator fun invoke(): Result<List<Item>>
}

class FakeGetItemsUseCase : GetItemsUseCase {
    override suspend operator fun invoke(): Result<List<Item>> {
        return Result.success(
            listOf(
                Item("1", "Jetpack Compose MVI"),
                Item("2", "Unidirectional Data Flow"),
                Item("3", "Turbine Flow Testing"),
                Item("4", "Coroutines & Flow")
            )
        )
    }
}
