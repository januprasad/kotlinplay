package com.example.mockito.sharedpref

import android.content.Context
import android.content.SharedPreferences
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class SharedPrefTest {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var repository: Repository // Replace with the actual class containing saveUserId

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        // Mock the context and shared preferences
        context = mock(Context::class.java)
        sharedPreferences = mock(SharedPreferences::class.java)
        editor = mock(SharedPreferences.Editor::class.java)

        // Define the behavior of mocked methods
        whenever(
            context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE),
        ).thenReturn(sharedPreferences)

        whenever(
            sharedPreferences.edit(),
        ).thenReturn(editor)

        whenever(
            editor.putString(anyString(), anyString()),
        ).thenReturn(editor)

        whenever(editor.commit()).thenReturn(true)

        // Initialize the class under test
        repository = Repository(context) // Adjust this line based on how you initialize your class
    }

    @Test
    fun testSaveUserId() {
        val userId = "test_user_id"

        // Call the method under test
        repository.saveUserId(userId)

        // Verify that the putString and commit methods were called with the correct parameters
        verify(editor).putString("USER_ID", userId)
        verify(editor).commit()
    }
}
