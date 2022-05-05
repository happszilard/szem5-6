package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ServiceMockTest {
    private static Service service;
    @Mock
    private static StudentXMLRepository studentXmlRepositoryMock;
    @Mock
    private static HomeworkXMLRepository homeworkXMLRepositoryMock;
    @Mock
    private static GradeXMLRepository gradeXMLRepositoryMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    static void setUp() {
        studentXmlRepositoryMock = mock(StudentXMLRepository.class);
        homeworkXMLRepositoryMock = mock(HomeworkXMLRepository.class);
        gradeXMLRepositoryMock = mock(GradeXMLRepository.class);
        service = new Service(studentXmlRepositoryMock, homeworkXMLRepositoryMock, gradeXMLRepositoryMock);
    }

    @Test
    void studentShouldBeSaved() {
        when(studentXmlRepositoryMock.save(anyObject())).thenReturn(null);
        int result = service.saveStudent("Student12", "Szili", 533);
        assertEquals(1, result);
    }

}