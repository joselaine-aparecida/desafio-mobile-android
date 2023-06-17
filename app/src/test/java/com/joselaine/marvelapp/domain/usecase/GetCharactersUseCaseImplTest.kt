package com.joselaine.marvelapp.domain.usecase

import androidx.paging.PagingConfig
import com.joselaine.marvelapp.data.repository.CharactersRepository
import com.joselaine.marvelapp.utils.MainCoroutineRule
import com.joselaine.marvelapp.utils.MarvelCharacterFactory
import com.joselaine.marvelapp.utils.PagingSourceFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharactersUseCaseImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @MockK
    lateinit var repository: CharactersRepository

    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private val hero = MarvelCharacterFactory().create(MarvelCharacterFactory.Hero.ThreeDMan)

    private val fakePagingSource = PagingSourceFactory().create(listOf(hero))

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getCharactersUseCase = GetCharactersUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
     runBlocking<Unit> {
            coEvery { repository.getCharacters("") }
                .returns(fakePagingSource)

            val result = getCharactersUseCase
                .invoke(GetCharactersUseCase.GetCharactersParams("", PagingConfig(20)))

            coVerify { repository.getCharacters("") }

            assertNotNull(result.first())
        }

    @Test(expected = Exception::class)
    fun `should throw exception when repository throws exception`() =  runBlocking<Unit> {
        val params = GetCharactersUseCase.GetCharactersParams("query", PagingConfig(20))
        coEvery { repository.getCharacters(any()) } throws Exception()

        getCharactersUseCase.invoke(params)
    }
}
