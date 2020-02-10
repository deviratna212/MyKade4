package id.ac.uty.mykade.testing

import id.ac.uty.mykade.network.ApiRepository
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class ApiRepositoryTestTest {

    @Test
    fun testDoRequest() {
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?1=4328"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}