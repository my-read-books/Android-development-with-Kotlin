package android.hromovych.com.marvelgallery


import android.hromovych.com.marvelgallery.data.MarvelRepository
import android.hromovych.com.marvelgallery.helpers.BaseMainView
import android.hromovych.com.marvelgallery.helpers.BaseMarvelRepository
import android.hromovych.com.marvelgallery.helpers.Example
import android.hromovych.com.marvelgallery.model.MarvelCharacter
import android.hromovych.com.marvelgallery.presenter.MainPresenter
import android.hromovych.com.marvelgallery.view.main.main.MainView
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MainPresenterTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `After view was created, list of characters is loaded and displayed`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test
    fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test
    fun `When API returns error, it is displayed on view`() {
        // Given
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
            onShow = { _ -> fail() },
            onShowError = { errorDisplayed = it }
        )
        val marvelRepository = BaseMarvelRepository { Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        // Then
        assertEquals(someError, errorDisplayed)
    }

    @Test
    fun `When presenter is waiting for response, refresh is displayed`() {
        // Given
        val view = BaseMainView(refresh = false)
        val marvelRepository = BaseMarvelRepository(
            onGetCharacters = {
                Single.fromCallable {
                    // Then
                    assertTrue(view.refresh) // 1
                    Example.exampleCharacterList
                }
            }
        )
        val mainPresenter = MainPresenter(view, marvelRepository)
        view.onShow = { _ ->
            // Then
            assertTrue(view.refresh) // 1
        }
        // When
        mainPresenter.onViewCreated()
        // Then
        assertFalse(view.refresh) // 1
    }
}

private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

    fun thereIsSameListDisplayed() {

        var displayedList: List<MarvelCharacter>? = null

        val view = BaseMainView(
            onShow = { items -> displayedList = items },
            onShowError = { fail() }
        )

        val marvelRepository = BaseMarvelRepository(
            onGetCharacters =
            { Single.just(Example.exampleCharacterList) }
        )

        val mainPresenter = MainPresenter(view, marvelRepository)

        mainPresenter.actionOnPresenter()
        assertEquals(Example.exampleCharacterList, displayedList)

    }
}
