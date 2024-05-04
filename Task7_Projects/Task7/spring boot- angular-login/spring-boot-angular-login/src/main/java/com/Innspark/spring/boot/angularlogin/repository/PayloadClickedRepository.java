package com.Innspark.spring.boot.angularlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Payloadclicked;


@Repository
public interface PayloadClickedRepository extends JpaRepository<Payloadclicked,String > {

}
