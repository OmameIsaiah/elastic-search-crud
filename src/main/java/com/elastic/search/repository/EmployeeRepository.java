package com.elastic.search.repository;

import com.elastic.search.model.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {

}