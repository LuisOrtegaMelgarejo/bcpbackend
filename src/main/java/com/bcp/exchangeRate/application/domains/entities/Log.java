package com.bcp.exchangeRate.application.domains.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user", nullable = false)
    String user;
    @Column(name = "method", nullable = false)
    String method;
    @Column(name = "resource", nullable = false)
    String resource;
    @Column(name = "body")
    String body;
    @Column(name = "executed_at")
    String executedAt;

    public Log(String user, String method, String resource){
        this.user = user;
        this.method = method;
        this.resource = resource;
    }

}
