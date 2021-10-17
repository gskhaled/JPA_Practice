package com.example.services;

import com.example.tables.Employee;
import com.example.tables.Project;
import com.example.tables.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

public class QueryServiceTest {
    @Mock
    private EntityManager em;
    @InjectMocks
    private QueryServiceImpl queryService;

    private String jsonRepresentation(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing query 1")
    void query1Test() {
        List<Employee> dummyArr = new ArrayList<Employee>();
        dummyArr.add(new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()));
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(dummyArr);
        assertSame(jsonRepresentation(dummyArr), queryService.query1JSON());
    }

    @Test
    @DisplayName("Testing query 2")
    void query2Test() {

    }

    @Test
    @DisplayName("Testing query 3")
    void query3Test() {

    }

    @Test
    @DisplayName("Testing query 4")
    void query4Test() {

    }

    @Test
    @DisplayName("Testing query 1 paginated")
    void query1PaginatedTest() {

    }
}
